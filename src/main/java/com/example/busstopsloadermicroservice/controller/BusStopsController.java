package com.example.busstopsloadermicroservice.controller;

import com.example.busstopsloadermicroservice.model.BusStops;
import com.example.busstopsloadermicroservice.repository.BusStopsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/busStops")
public class BusStopsController {
    private final BusStopsRepository busStopsRepository;

    public BusStopsController(BusStopsRepository busStopsRepository) {
        this.busStopsRepository = busStopsRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getBusses(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "9") Integer pageSize,
            @RequestParam(defaultValue = "number") String sortBy) {
        try {
            List<BusStops> busses;
            Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));

            Page<BusStops> busStopsPage;
            if (query == null)
                busStopsPage = busStopsRepository.findAll(paging);
            else
                busStopsPage = busStopsRepository.findAllByQuery(query, paging);

            busses = busStopsPage.getContent();
            boolean hasNext = busStopsPage.getNumber() < busStopsPage.getTotalPages() - 1;

            Map<String, Object> response = new HashMap<>();
            response.put("busses", busses);
            response.put("currentPage", busStopsPage.getNumber());
            response.put("totalItems", busStopsPage.getTotalElements());
            response.put("totalPages", busStopsPage.getTotalPages());
            response.put("hasNext", hasNext);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

