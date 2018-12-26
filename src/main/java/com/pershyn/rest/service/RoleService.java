package com.pershyn.rest.service;

import com.pershyn.rest.model.Role;
import com.pershyn.rest.model.RoleName;
import com.pershyn.rest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByName(RoleName roleName){

    return roleRepository.findByName(roleName);
}
}
