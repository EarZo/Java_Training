package com.cellterion.dealerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public @Data class DealerSmartphone implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Integer smartphoneDealersId;

    @Id
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Dealer dealer;

    @Id
    Integer smartphoneId;

    double price;
}
