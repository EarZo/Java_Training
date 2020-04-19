package com.cellterion.smartphoneservice.model;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
public @Data class Smartphone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer smartphoneId;

	String brandName;
	String model;
	String cardImageUrl;
	String androidVersion;
	Integer manufactureYear;
	String manufactureMonth;
	String ram;
	String internalMemory;
	String externalMemory;
	String damageProtection;
	String cameraSetup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chipset_id")
    Chipset chipset;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "display_id")
	Display display;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "battery_id")
	Battery battery;

    @OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
    List<Image> images;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<MainCamera> mainCameras;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException, i.e. we can have two or more eager loadings at once
//	@Fetch(FetchMode.SELECT) // can use this also, to eliminate MultipleBagFetchException
	List<Review> reviews;

	@OneToMany(mappedBy = "smartphone", cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE) // still eagerly loads the collection, but eliminates the MultipleBagFetchException
	List<SmartphoneDealer> smartphoneDealers;

}
