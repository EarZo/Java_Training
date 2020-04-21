package com.cellterion.brandservice.repository;

import com.cellterion.brandservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findBrandByBrandName(String brandName);

}
