package io.github.Kirotar.lennureis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatsRequest {

    private int flightId;
    private Boolean legroom;
    private String seatType;
    private Boolean exitRow;
    private int nrOfPassengers;
}
