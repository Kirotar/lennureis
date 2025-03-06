package io.github.Kirotar.lennureis.repository;

import io.github.Kirotar.lennureis.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flights f WHERE (:origin IS NULL OR f.origin = :origin) " +
            "AND (:destination IS NULL OR f.destination = :destination) " +
            "AND (:departure IS NULL OR f.departure = :departure) " +
            "AND (:arrival IS NULL OR f.arrival = :arrival) " +
            "AND (:company IS NULL OR f.company = :company) ")
    List<Flight> findAvailableRoomsWithFilters(
            @Param("origin,") String origin,
            @Param("destination") String destination,
            @Param("departure") Instant departure,
            @Param("arrival,") Instant arrival,
            @Param("price,") BigDecimal price,
            @Param("company,") String company
            );
}
