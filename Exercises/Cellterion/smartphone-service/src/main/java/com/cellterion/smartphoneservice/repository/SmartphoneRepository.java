package com.cellterion.smartphoneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cellterion.smartphoneservice.model.Smartphone;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {
}
