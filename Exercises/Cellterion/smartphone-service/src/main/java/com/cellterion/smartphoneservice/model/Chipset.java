package com.cellterion.smartphoneservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class Chipset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer chipsetId;

    String brandName;
    String modelNumber;
    String modelName;
    String architecture;
    String cpu;
    String gpu;
}
