package io.github.Kirotar.lennureis.controller;

import io.github.Kirotar.lennureis.model.Seat;
import io.github.Kirotar.lennureis.service.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flight")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/assign-seats/{id}")
    public List<Seat> assignSeats(@PathVariable("id") int id) {
        return seatService.assignSeats(id);
    }
}
