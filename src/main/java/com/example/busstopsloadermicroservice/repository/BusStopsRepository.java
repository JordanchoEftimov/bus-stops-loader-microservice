package com.example.busstopsloadermicroservice.repository;

import com.example.busstopsloadermicroservice.model.BusStops;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStopsRepository extends JpaRepository<BusStops, Long> {
    @Query(value = "select b from BusStops b where lower(b.name) like lower(concat('%', :query, '%') ) " +
            "or concat(b.number, '') like %:query%")
    Page<BusStops> findAllByQuery(String query, Pageable pageable);
}
