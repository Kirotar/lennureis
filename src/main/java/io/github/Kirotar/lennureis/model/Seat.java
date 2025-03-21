package io.github.Kirotar.lennureis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats", schema = "flight_booking")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seats_id_gen")
    @SequenceGenerator(name = "seats_id_gen", sequenceName = "seats_seat_id_seq", allocationSize = 1)
    @Column(name = "seat_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "seat_column")
    private Character seatColumn;

    @Column(name = "seat_row")
    private Integer seatRow;

    @Column(name = "legroom")
    private Boolean legroom;

    @Column(name = "seat_type", length = 20)
    private String seatType;

    @Column(name = "exit_row")
    private Boolean exitRow;

    @Column(name = "booked")
    private Boolean booked;

}