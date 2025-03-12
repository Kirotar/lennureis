package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.dto.SeatsRequest;
import io.github.Kirotar.lennureis.model.Seat;
import io.github.Kirotar.lennureis.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeatsForFlight(int id) {
    return seatRepository.findAllByFlightId(id);
    }

    public void assignRandomTakenSeats(int id) {
        List<Seat> takenSeats = getAllSeatsForFlight(id);

        takenSeats.forEach(seat -> {
            boolean randomStatus = Math.random() < 0.5;
            seat.setBooked(randomStatus);
        });

        seatRepository.saveAll(takenSeats);
    }


    public List<String> assignSeats(SeatsRequest request) {
        assignRandomTakenSeats(request.getFlightId());
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

