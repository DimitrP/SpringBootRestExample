package com.pershyn.rest.service;

import com.pershyn.rest.model.User;
import com.pershyn.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User getUserByName(String userName){

        return userRepository.findByUsername(userName).get();

    }

    public Optional<User> getUserByName2(String userName){

        return userRepository.findByUsername(userName);

    }


    public boolean emailExists(String email){

       return userRepository.existsByEmail(email);

    }

    public  boolean userNameExists(String name){

       return userRepository.existsByUsername(name);
    }


    public  User save (User user){
        return userRepository.save(user);
    }
}
