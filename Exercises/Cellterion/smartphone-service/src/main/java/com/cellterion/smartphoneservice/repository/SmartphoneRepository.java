package com.cellterion.smartphoneservice.repository;

import com.cellterion.smartphoneservice.model.SmartphoneDealers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cellterion.smartphoneservice.model.Smartphone;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {
}
