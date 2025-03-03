CREATE SCHEMA IF NOT EXISTS flight_booking;
CREATE TABLE flight_booking.flights
(
    flight_id   SERIAL PRIMARY KEY,
    origin      VARCHAR(50),
    destination VARCHAR(50),
    departure   TIMESTAMP,
    arrival     TIMESTAMP,
    price       DECIMAL(10, 2),
    company     VARCHAR(50)
);