package io.github.Kirotar.lennureis.repository;

import io.github.Kirotar.lennureis.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE " +
            "(:origin IS NULL OR :origin = '' OR f.origin = :origin) " +
            "AND (:destination IS NULL OR :destination = '' OR f.destination = :destination) " +
            "AND (:departureDate IS NULL OR :departureDate = '' OR f.departureDate =:departureDate) " +
            "AND (:arrivalDate IS NULL OR :arrivalDate = '' OR f.arrivalDate =:arrivalDate) " +
            "AND (:company IS NULL OR :company = '' OR f.company = :company)")
    List<Flight> findAvailableFlightsWithFilters(
            @Param("origin") String origin,
            @Param("destination") String destination,
            @Param("departureDate") String departureDate,
            @Param("arrivalDate") String arrivalDate,
            @Param("company") String company
    );
}
