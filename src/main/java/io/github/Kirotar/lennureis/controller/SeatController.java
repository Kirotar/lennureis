package io.github.Kirotar.lennureis.controller;

import io.github.Kirotar.lennureis.dto.SeatsRequest;
import io.github.Kirotar.lennureis.model.Seat;
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

    @GetMapping("/assign-seats")
    public List<String> assignSeats(@ModelAttribute SeatsRequest seat) {
        return seatService.assignSeats(seat);
    }
}
