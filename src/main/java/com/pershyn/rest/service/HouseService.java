package com.pershyn.rest.service;


import com.pershyn.rest.model.House;
import com.pershyn.rest.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    public House saveHouse(House house){
        return houseRepository.save(house);
    }

    public House getHouse(int id){
        return houseRepository.findById(id).get();
    }

    public void delete(House house){

        houseRepository.delete(house);
    }


}
