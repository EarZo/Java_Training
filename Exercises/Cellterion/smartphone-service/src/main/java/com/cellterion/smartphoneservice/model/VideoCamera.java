package com.cellterion.smartphoneservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class VideoCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer videoCameraId;

    Integer resolution;
    String fps;

    @ManyToOne
    @JoinColumn(name = "smartphone_id")
    @JsonIgnore
    Smartphone smartphone;
}
