package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public @Data class SmartphoneDealer {

    @Id
    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;

    @Id
    Integer dealerId;

    double price;
}
