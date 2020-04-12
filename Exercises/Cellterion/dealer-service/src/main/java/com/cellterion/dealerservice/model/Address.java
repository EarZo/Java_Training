package com.cellterion.dealerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer addressId;

    String residenceNumber;
    String street;
    String city;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @JsonIgnore
    Dealer dealer;
}
