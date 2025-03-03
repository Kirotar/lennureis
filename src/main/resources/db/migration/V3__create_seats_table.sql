CREATE TABLE flight_booking.seats
(
    seat_id   SERIAL PRIMARY KEY,
    flight_id BIGINT,
    seat_nr   VARCHAR(10),
    legroom   BOOLEAN,
    seat_type    VARCHAR(20),
    exit_row      BOOLEAN,
    booked    BOOLEAN,
    FOREIGN KEY (flight_id) REFERENCES flight_booking.flights(flight_id)
);