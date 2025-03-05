package io.github.Kirotar.lennureis.repository;

import io.github.Kirotar.lennureis.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
