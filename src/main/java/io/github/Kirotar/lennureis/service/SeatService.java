package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.model.Seat;
import io.github.Kirotar.lennureis.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void assignRandomTakenSeats() {
    List<Seat> takenSeats = seatRepository.findAll();

    takenSeats.forEach(seat -> {
        boolean randomStatus = Math.random() < 0.5;
        seat.setBooked(randomStatus);
    });

    seatRepository.saveAll(takenSeats);
    }


    public List<String> assignSeats(int id) {
    List<String> assignedSeats = seatRepository.reccommendSeatIds(

    )
    }

}
