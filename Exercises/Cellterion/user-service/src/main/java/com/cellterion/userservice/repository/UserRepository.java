package com.cellterion.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cellterion.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
