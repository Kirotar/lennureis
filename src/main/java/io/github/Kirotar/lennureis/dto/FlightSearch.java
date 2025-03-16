package io.github.Kirotar.lennureis.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class FlightSearch {

    //Tehisintellekti lahendus:
    @Override
    public String toString() {
        return "FlightSearch{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure='" + departureDate + '\'' +
                ", arrival='" + arrivalDate + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String company;
}
