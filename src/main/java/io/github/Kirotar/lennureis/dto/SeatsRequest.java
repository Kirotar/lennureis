package io.github.Kirotar.lennureis.dto;

import io.github.Kirotar.lennureis.model.Flight;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SeatsRequest {


    private int flightId;
    private Boolean legroom;
    private String seatType;
    private Boolean exitRow;
    private int nrOfPassengers;
}
