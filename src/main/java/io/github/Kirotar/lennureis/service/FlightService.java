package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.model.Flight;
import io.github.Kirotar.lennureis.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
