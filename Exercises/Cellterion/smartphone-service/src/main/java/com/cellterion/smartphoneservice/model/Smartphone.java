package com.cellterion.smartphoneservice.model;

import java.util.List;

import javax.persistence.*;

import com.cellterion.smartphoneservice.sharedModel.Dealer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Entity
public @Data class Smartphone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer smartphoneId;

	String brand;
	String model;
	String cardImage;
	String fullImage;
	String androidVersion;
	Integer manufactureYear;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Review> reviews;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Variant> variants;

	@Transient
	Dealer[] dealers;
}
