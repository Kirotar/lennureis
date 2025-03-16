package io.github.Kirotar.lennureis.service;

import io.github.Kirotar.lennureis.dto.SeatsRequest;
import io.github.Kirotar.lennureis.model.Seat;
import io.github.Kirotar.lennureis.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeatsForFlightFromSeats(int id) {
        return seatRepository.findAllByFlightId(id);
    }

    public List<SeatsRequest> getAllSeatsForFlight(int id) {
        List<Seat> seats = seatRepository.findAllByFlightId(id);
        List<SeatsRequest> seatDTOs = seats.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return seatDTOs;
    }

    private SeatsRequest convertToDTO(Seat seat) {
        SeatsRequest dto = new SeatsRequest();
        dto.setFlightId(seat.getFlight().getId());
        dto.setSeatColumn(seat.getSeatColumn());
        dto.setSeatRow(seat.getSeatRow());
        dto.setLegroom(seat.getLegroom());
        dto.setSeatType(seat.getSeatType());
        dto.setExitRow(seat.getExitRow());
        dto.setBooked(seat.getBooked());
        dto.setNrOfPassengers(0);
        return dto;
    }

    public void assignRandomTakenSeats(int id) {
        List<Seat> takenSeats = getAllSeatsForFlightFromSeats(id);

        takenSeats.forEach(seat -> {
            boolean randomStatus = Math.random() < 0.3;
            seat.setBooked(randomStatus);
        });

        seatRepository.saveAll(takenSeats);
    }


    public List<String> assignSeats(SeatsRequest request) {
        List<Seat> recommendedSeats = seatRepository.reccommendSeatIds(request.getFlightId(), request.getLegroom(), request.getSeatType(), request.getExitRow()
        );
        List<String> assignedSeats = new ArrayList<>();
        int passengersToAssign = request.getNrOfPassengers();
        for (Seat seat : recommendedSeats) {
            if (passengersToAssign == 0) {
                break;
            }
            String seatIdentifier = String.valueOf(seat.getSeatRow()) + seat.getSeatColumn();
            assignedSeats.add(seatIdentifier);
            passengersToAssign--;
        }
        return assignedSeats;
    }
}

