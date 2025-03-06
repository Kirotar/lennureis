package io.github.Kirotar.lennureis.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class FlightSearch {

    private String origin;
    private String destination;
    private Instant departure;
    private Instant arrival;
    private BigDecimal price;
    private String company;
}
