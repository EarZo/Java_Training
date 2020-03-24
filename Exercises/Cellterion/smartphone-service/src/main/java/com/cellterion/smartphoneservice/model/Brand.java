package com.cellterion.smartphoneservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer brandId;

    String brandName;

    @OneToMany(mappedBy = "brand")
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Smartphone> smartphones;
}
