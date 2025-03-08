package io.github.Kirotar.lennureis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "flights", schema = "flight_booking")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_id_gen")
    @SequenceGenerator(name = "flights_id_gen", sequenceName = "flights_flight_id_seq", allocationSize = 1)
    @Column(name = "flight_id", nullable = false)
    private Integer id;

    @Column(name = "origin", length = 50)
    private String origin;

    @Column(name = "destination", length = 50)
    private String destination;

    @Column(name = "departure")
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "company", length = 50)
    private String company;

}