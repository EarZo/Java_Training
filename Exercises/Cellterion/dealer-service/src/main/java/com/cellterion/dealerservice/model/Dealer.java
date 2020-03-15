package com.cellterion.dealerservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer dealerId;

    String dealerName;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException
    List<DealerSmartphone> dealerSmartphones;
}
