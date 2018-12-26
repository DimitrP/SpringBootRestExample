package com.pershyn.rest.service;

import com.pershyn.rest.model.Profile;
import com.pershyn.rest.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile saveProfile(Profile profile){
        return profileRepository.save(profile);
    }

    public Profile getProfile(Long id){
        return profileRepository.findById(id).get();
    }

}
