package com.cellterion.dealerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer telephoneId;

    String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @JsonIgnore
    Dealer dealer;
}
