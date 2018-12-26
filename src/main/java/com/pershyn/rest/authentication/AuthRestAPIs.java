package com.pershyn.rest.authentication;

import com.pershyn.rest.forms.LoginForm;
import com.pershyn.rest.forms.SignUpForm;
import com.pershyn.rest.model.Profile;
import com.pershyn.rest.model.Role;
import com.pershyn.rest.model.RoleName;
import com.pershyn.rest.model.User;
import com.pershyn.rest.security.token.JwtProvider;
import com.pershyn.rest.service.ProfileService;
import com.pershyn.rest.service.RoleService;
import com.pershyn.rest.service.UserService;
import com.pershyn.rest.tokenresponce.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;
//    UserRepository userRepository;

    @Autowired
    RoleService roleService;
//    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ProfileService profileService;

//    ProfileRepository profileRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userService.userNameExists(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.emailExists(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	switch(role) {
	    		case "admin":
	    			Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		case "pm":
	            	Role pmRole = roleService.findByName(RoleName.ROLE_PM)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	            	roles.add(pmRole);
	            	
	    			break;
	    		default:
	        		Role userRole = roleService.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole);        			
        	}
        });


        user.setRoles(roles);

        Profile profile = new Profile();
        user.setProfile(profile);
        profile.setUser(user);


//        profileRepository.save(profile);

        User user1 = userService.save(user);
//        profileRepository.save(profile);
//
//
//        String name = user.getName();


//        System.out.println(name);
//        User user1 =(userRepository.findByUsername("Dimitry_user")).get();
//        System.out.println(user1.toString());

//        profile.setUser((userRepository.findByUsername("Dimitry_user")).get());
//        System.out.println(profile.toString());
//        profileRepository.save(profile);

        return ResponseEntity.ok().body("User registered successfully!   "+user1.getProfile().toString());
    }



}