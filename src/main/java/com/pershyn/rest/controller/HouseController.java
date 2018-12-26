package com.pershyn.rest.controller;

import com.pershyn.rest.authentication.AuthenticationFacade;
import com.pershyn.rest.forms.AddHouseForm;
import com.pershyn.rest.model.House;
import com.pershyn.rest.model.Profile;
import com.pershyn.rest.model.User;
import com.pershyn.rest.service.HouseService;
import com.pershyn.rest.service.ProfileService;
import com.pershyn.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/profile/houses")
@CrossOrigin
public class HouseController {

    @Autowired
    HouseService houseService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @GetMapping("/")
    public ResponseEntity list(){
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User userOptional = userService.getUserByName(username);

//        user = userOptional;
        List<House> houses = new ArrayList<House>() ;



        houses.addAll(userOptional.getProfile().getHouses());
        if(houses.size()>0)
            return ResponseEntity.ok().body(houses);

        else

        {return ResponseEntity.ok().body("NO HOUSES !");}
    }




    @PostMapping("/add")
    public ResponseEntity addHouse (@RequestBody AddHouseForm addHouseForm){
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User user = userService.getUserByName(username);

        House house = new House();
        house.setBedrooms_number(addHouseForm.getBedrooms());
        house.setSquare_footage_number(addHouseForm.getSqFeet());
        house.setYear_build(addHouseForm.getYearBuild());
        house.setZipcode(addHouseForm.getZipcode());

        Profile profile = user.getProfile();

        house.setProfile_id(profile.getId());

        House newhouse = houseService.saveHouse(house);


        return ResponseEntity.status(HttpStatus.CREATED).body(newhouse);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHouse (@PathVariable int id){

        House house = houseService.getHouse(id);
        if(house!=null) {
            houseService.delete(house);
            return ResponseEntity.status(HttpStatus.OK).body(house);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateHouse (@PathVariable int id, @RequestBody House house){

        House houseToBeUpdated = houseService.getHouse(id);

        if(houseToBeUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        houseToBeUpdated.setBedrooms_number(house.getBedrooms_number());
        houseToBeUpdated.setSquare_footage_number(house.getSquare_footage_number());
        houseToBeUpdated.setYear_build(house.getYear_build());
        houseToBeUpdated.setZipcode(house.getZipcode());

        houseService.saveHouse(houseToBeUpdated);
        return ResponseEntity.ok(houseToBeUpdated);
    }

  }

