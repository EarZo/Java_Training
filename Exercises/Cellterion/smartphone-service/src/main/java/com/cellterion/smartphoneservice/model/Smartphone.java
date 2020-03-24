package com.cellterion.smartphoneservice.model;

import java.util.List;

import javax.persistence.*;

import com.cellterion.smartphoneservice.sharedModel.Dealer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Entity
public @Data class Smartphone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer smartphoneId;

	String model;
	String cardImage;
	String fullImage;
	String androidVersion;
	Integer manufactureYear;
	String ram;
	String chipset;
	String gpu;
	String internalMemory;
	String externalMemory;
	String damageProtection;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brand_id")
	@JsonIgnore
	Brand brand;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "display_id")
	Display display;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "battery_id")
	Battery battery;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<MainCamera> mainCameras;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Review> reviews;

	@Transient
	Dealer[] dealers;
}
