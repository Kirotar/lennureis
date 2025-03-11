package io.github.Kirotar.lennureis.repository;

import io.github.Kirotar.lennureis.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("SELECT s FROM Seat s WHERE " +
            "s.flight = :flightId " +
            "AND (:legroom IS NULL OR s.legroom = :legroom) " +
            "AND (:seatType IS NULL OR s.seatType = :seatType) " +
            "AND (:exitRow IS NULL OR s.exitRow = :exitRow) " +
            "AND s.booked = false ")
    List<Seat> reccommendSeatIds(
            @Param("flightId") int flightId,
            @Param("legroom") Boolean legroom,
            @Param("seatType") String seatType,
            @Param("exitRow") Boolean exitRow
    );
}