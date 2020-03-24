package com.cellterion.smartphoneservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer batteryId;

    String capacity;
    String electrolyte;
    String removability;
    String charging;

}
