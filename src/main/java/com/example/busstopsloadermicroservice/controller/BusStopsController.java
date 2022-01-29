package com.example.busstopsloadermicroservice.controller;

import com.example.busstopsloadermicroservice.model.BusStops;
import com.example.busstopsloadermicroservice.service.BusStopsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/busStops")
public class BusStopsController {
    private final BusStopsService busStopsService;

    public BusStopsController(BusStopsService busStopsService) {
        this.busStopsService = busStopsService;
    }

    @GetMapping
    public ResponseEntity<List<BusStops>> getBusses(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "9") Integer pageSize,
            @RequestParam(defaultValue = "number") String sortBy) {
        List<BusStops> list = busStopsService.getBusses(query, page, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
