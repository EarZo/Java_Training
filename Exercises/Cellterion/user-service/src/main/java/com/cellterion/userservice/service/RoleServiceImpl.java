package com.cellterion.userservice.service;

import com.cellterion.userservice.model.Role;
import com.cellterion.userservice.model.RoleType;
import com.cellterion.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Integer roleID){
        Optional<Role> roleOptional = roleRepository.findById(roleID);

        if(roleOptional.isPresent())
            return roleOptional.get();
        return null;
    }

    @Override
    public List<Role> findAllUsers(){
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleType(RoleType roleType){
        return roleRepository.findByRoleType(roleType)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
