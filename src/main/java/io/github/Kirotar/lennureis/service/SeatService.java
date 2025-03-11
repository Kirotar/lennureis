package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.dto.SeatsRequest;
import io.github.Kirotar.lennureis.model.Seat;
import io.github.Kirotar.lennureis.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<String> assignSeats(SeatsRequest request) {
        assignRandomTakenSeats();
        List<Seat> reccommendedSeats = seatRepository.reccommendSeatIds(request.getFlightId(), request.getLegroom(), request.getSeatType(), request.getExitRow()
        );
        List<String> assignedSeats = new ArrayList<>();
        int passengersToAssign = request.getNrOfPassengers();
        for (Seat seat : reccommendedSeats) {
            if (passengersToAssign == 0) {
                break;
            }
            assignedSeats.add(seat.getSeatNr());
            passengersToAssign--;
        }
        return assignedSeats;
    }
}

