package io.github.Kirotar.lennureis.controller;

import io.github.Kirotar.lennureis.dto.SeatsRequest;
import io.github.Kirotar.lennureis.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/get-all-seats/{id}")
    public List<SeatsRequest> getAllSeatsForFlight(@PathVariable("id") int id) {
         return seatService.getAllSeatsForFlight(id);
    }

    @GetMapping("/assign-seats")
    public List<String> assignSeats(@ModelAttribute SeatsRequest seat) {
        return seatService.assignSeats(seat);
    }

    @PostMapping("/assign-taken-seats/{id}")
    public void assignRandomSeatsById(@PathVariable("id") int id) {
        seatService.assignRandomTakenSeats(id);
    }
}
