package com.cellterion.smartphoneservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class Display {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer displayId;

    String type;
    String colorGamut;
    String size;
    String resolution;
    String pixelDensity;
    String ratio;
    String glassType;
}
