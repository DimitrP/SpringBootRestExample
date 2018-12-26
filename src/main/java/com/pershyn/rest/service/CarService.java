package com.pershyn.rest.service;

import com.pershyn.rest.model.Car;
import com.pershyn.rest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    @Autowired
    CarRepository carRepository;

//    public Car getCar(){
//      return repository.findOne();
//
//    }
    public Car saveCar(Car car){
    return carRepository.save(car);

    }

    public Car findById(int id){

        return carRepository.findById(id).get();

    }


    public void delete(Car car){

        carRepository.delete(car);
    }


    public List<Car> carList (){
        return (List<Car>) carRepository.findAll();
    }

//public Car deleteCar(int id){
//        Car car = repository.findOne(id);
//        if(pet != null){
//            repository.delete(id);
//        }
//        return car;
//
//}

//public Car getCarbyId(int id){
//       return repository.findOne(id);
//}
//
//public Car updatePet(Car car){
//    Car carUpdated = repository.findOne(pet.getId());
//    car.setName(car.getName() == null?petUpdated.getName():pet.getName());
//    pet.setAge(pet.getAge() == 0?petUpdated.getAge():pet.getAge());
////    petUpdated.setAge(pet.getAge());
////    petUpdated.setName(pet.getName());
//        return repository.save(pet);
//}


}
