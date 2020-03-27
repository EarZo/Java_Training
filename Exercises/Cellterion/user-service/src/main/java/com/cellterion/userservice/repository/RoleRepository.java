package com.cellterion.userservice.repository;

import com.cellterion.userservice.model.Role;
import com.cellterion.userservice.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType roleType);

}
