package com.example.busstopsloadermicroservice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "bus_stops", schema = "public")
public class BusStops {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    public BusStops(Long id, Integer number, String name, Double lat, Double lon) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public BusStops() {

    }
}
