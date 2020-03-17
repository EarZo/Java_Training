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
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer smartphoneId;
	
	String brand;
	String model;
	String touchscreenTechnology;
	String cameraShutter;
	String processor;
	String battery;
	String image;
	
	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Review> reviews;

	@Transient
	Dealer[] dealers;
}
