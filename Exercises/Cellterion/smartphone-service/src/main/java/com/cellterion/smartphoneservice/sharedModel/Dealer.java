package com.cellterion.smartphoneservice.sharedModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public @Data class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer dealerId;

    String dealerName;

}
