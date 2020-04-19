package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer imageId;

    String fullImageUrl;

    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;
}
