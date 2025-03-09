package io.github.Kirotar.lennureis.controller;

import io.github.Kirotar.lennureis.dto.FlightSearch;
import io.github.Kirotar.lennureis.model.Flight;
import io.github.Kirotar.lennureis.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

@GetMapping("/flights")
    public List<Flight> getAllFlights(){
    return flightService.getAllFlights();
}

@GetMapping("/search")
    public List<Flight> getSearchedFlights(@ModelAttribute FlightSearch search){
        return flightService.searchFlights(search);
}

    @GetMapping("/search_by_id/{id}")
    public Flight searchFlightById(@PathVariable ("id") int id){
        return flightService.searchFlightById(id);
    }
}
