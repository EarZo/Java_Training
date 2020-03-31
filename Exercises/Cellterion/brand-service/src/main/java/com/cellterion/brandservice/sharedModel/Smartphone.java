package com.cellterion.brandservice.sharedModel;

import lombok.Data;

import javax.persistence.*;

public @Data class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer smartphoneId;

    Integer brandId;

    String model;
    String cardImage;
}
