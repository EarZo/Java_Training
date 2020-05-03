package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public @Data class SmartphoneDealer implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;

    @Id
    String dealerName;

    BigDecimal price;
}
