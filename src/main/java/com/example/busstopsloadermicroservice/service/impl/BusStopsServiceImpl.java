package com.example.busstopsloadermicroservice.service.impl;

import com.example.busstopsloadermicroservice.model.BusStops;
import com.example.busstopsloadermicroservice.repository.BusStopsRepository;
import com.example.busstopsloadermicroservice.service.BusStopsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusStopsServiceImpl implements BusStopsService {
    private final BusStopsRepository repository;

    public BusStopsServiceImpl(BusStopsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BusStops> getBusses(String query, Integer page, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<BusStops> pagedResult;
        if (query != null && !query.isEmpty()) {
            pagedResult = repository.findAllByQuery(query, paging);
        } else {
            pagedResult = repository.findAll(paging);
        }

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
