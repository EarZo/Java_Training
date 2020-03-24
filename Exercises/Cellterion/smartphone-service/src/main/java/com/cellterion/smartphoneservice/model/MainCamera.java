package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class MainCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer mainCameraId;

    String pixels;
    String aperture;
    String focalLength;
    String sensorSize;

    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;
}
