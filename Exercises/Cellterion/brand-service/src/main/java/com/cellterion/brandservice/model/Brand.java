package com.cellterion.brandservice.model;

import com.cellterion.brandservice.sharedModel.Smartphone;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer brandId;

    String brandName;

    @Transient
    Smartphone[] smartphones;
}
