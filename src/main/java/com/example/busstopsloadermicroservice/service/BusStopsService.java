package com.example.busstopsloadermicroservice.service;

import com.example.busstopsloadermicroservice.model.BusStops;

import java.util.List;

public interface BusStopsService {
    List<BusStops> getBusses(String query, Integer page, Integer pageSize, String sortBy);
}
