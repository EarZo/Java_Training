package com.cellterion.userservice.repository;

import com.cellterion.userservice.model.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends JpaRepository<WebsiteUser, Integer> {
    WebsiteUser findByUsername(String username);
}
