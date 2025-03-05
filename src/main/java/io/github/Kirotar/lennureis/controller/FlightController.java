package io.github.Kirotar.lennureis.controller;

import io.github.Kirotar.lennureis.model.Flight;
import io.github.Kirotar.lennureis.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lend")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

@GetMapping("/lennud")
    public List<Flight> getAllFlights(){
    return flightService.getAllFlights();
}
}
