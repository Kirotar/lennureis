package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.dto.FlightSearch;
import io.github.Kirotar.lennureis.model.Flight;
import io.github.Kirotar.lennureis.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Flight> searchFlights(FlightSearch search) {
        return flightRepository.findAvailableFlightsWithFilters(search.getOrigin(), search.getDestination(),
                search.getDeparture(), search.getArrival(), search.getCompany());
    }

    public Flight searchFlightById (int id){
        return flightRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Flight with id " + id + " not found"));

    }
}
