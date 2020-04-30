package com.cellterion.userservice.service;

import com.cellterion.userservice.model.Role;
import com.cellterion.userservice.model.RoleType;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    Role findRoleById(Integer roleID);

    List<Role> findAllUsers();

    Role findByRoleType(RoleType roleType);
}
