package com.cellterion.brandservice.sharedModel;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Smartphone {
    @Id
    Integer smartphoneId;

    String brandName;
    String model;
    String cardImage;
}
