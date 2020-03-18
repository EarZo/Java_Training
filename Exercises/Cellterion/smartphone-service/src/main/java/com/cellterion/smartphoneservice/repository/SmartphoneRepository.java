package com.cellterion.smartphoneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cellterion.smartphoneservice.model.Smartphone;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {

    List<Smartphone> findSmartphonesByManufactureYear(Integer manufactureYear);
}
