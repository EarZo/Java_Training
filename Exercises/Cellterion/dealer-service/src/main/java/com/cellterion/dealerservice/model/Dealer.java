package com.cellterion.dealerservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer dealerId;

    String dealerName;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Address> addresses;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Telephone> telephoneList;
}
