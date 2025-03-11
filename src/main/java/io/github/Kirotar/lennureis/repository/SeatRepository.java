package io.github.Kirotar.lennureis.repository;

import io.github.Kirotar.lennureis.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query()
    List<Seat> reccommendSeatIds(
            @Param("flightId") int flightId,
            @Param("legroomPreference") Boolean legroom,
            @Param("seatTypePreference") String departure,
            @Param("exitRowPreference") String arrival,
            @Param("numberOfPassengers") int numberOfPassengers
    );
}