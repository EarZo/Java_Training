package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class MainCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer mainCameraId;

    double pixels;
    String aperture;
    Integer focalLength;
    String fieldOfView;
    String sensorSize;
    String specialFeature;

    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;
}
