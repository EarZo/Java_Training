package com.cellterion.brandservice.repository;

import com.cellterion.brandservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
