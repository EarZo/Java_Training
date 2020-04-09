package com.cellterion.dealerservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer dealerId;

    String dealerName;
}
