package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer variantId;

    String ram;
    String chipset;
    String gpu;
    String memory;
    String displayType;
    String displaySize;
    String displayResolution;
    String displayProtection;
    String cameraShutter;
    String battery;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    @JsonIgnore
    Smartphone smartphone;
}
