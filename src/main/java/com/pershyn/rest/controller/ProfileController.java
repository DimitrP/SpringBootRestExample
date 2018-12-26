package com.pershyn.rest.controller;

import com.pershyn.rest.authentication.IAuthenticationFacade;
import com.pershyn.rest.forms.AddCarForm;
import com.pershyn.rest.model.Car;
import com.pershyn.rest.model.Profile;
import com.pershyn.rest.model.User;
import com.pershyn.rest.service.CarService;
import com.pershyn.rest.service.ProfileService;
import com.pershyn.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@SuppressWarnings("Duplicates")

@RequestMapping(value = "/profile")
@CrossOrigin
public class ProfileController {

    @Autowired
    CarService carService;
//    CarRepository carRepository;

    @Autowired
     IAuthenticationFacade authenticationFacade;

    @Autowired
    UserService userService;
//    UserRepository userRepository;

    @Autowired
    ProfileService profileService;
//    ProfileRepository profileRepository;

    @GetMapping("/mycars")
    public ResponseEntity list (){
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User userOptional = userService.getUserByName(username);

//        user = userOptional;
        List<Car> cars = new ArrayList<Car>() ;



            cars.addAll(userOptional.getProfile().getCars());
        if(cars.size()>0)
            return ResponseEntity.ok().body(cars);

        else

            {return ResponseEntity.ok().body("NO CARS !");}
    }

    @PostMapping("/mycars/add")
    public ResponseEntity addCar (@RequestBody AddCarForm addCarForm){
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User user = userService.getUserByName(username);

        Car car = new Car();
        car.setName(addCarForm.getName());
        car.setYear(addCarForm.getYear());

        Profile profile = user.getProfile();

        car.setProfile_id(profile.getId());

        Car newcar = carService.saveCar(car);


        return ResponseEntity.status(HttpStatus.CREATED).body(newcar);
    }



    @DeleteMapping("mycars/delete/{id}")
    public ResponseEntity deleteCar (@PathVariable int id){

        Car car = carService.findById(id);
        if(car!=null) {
            carService.delete(car);
            return ResponseEntity.status(HttpStatus.OK).body(car);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("mycars/update/{id}")
    public ResponseEntity updateCar (@PathVariable int id, @RequestBody Car car){
        Car carToBeUpdated = carService.findById(id);

        if(carToBeUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        carToBeUpdated.setName(car.getName());
        carToBeUpdated.setYear(car.getYear());

        carService.saveCar(carToBeUpdated);
        return ResponseEntity.ok(carToBeUpdated);
    }








   StringBuilder generateList(List<Car> cars){

        StringBuilder str = new StringBuilder();
       for (Car car:cars
            ) { str.append(car.getName()).append("  ").append(car.getYear()).append("\n");

       }
        return str;
    }

}
