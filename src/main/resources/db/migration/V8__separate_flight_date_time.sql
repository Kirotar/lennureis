ALTER TABLE flight_booking.flights
    ADD COLUMN departure_date VARCHAR(50),
ADD COLUMN departure_time VARCHAR(50),
ADD COLUMN arrival_date VARCHAR(50),
ADD COLUMN arrival_time VARCHAR(50);

UPDATE flight_booking.flights
SET departure_date = SPLIT_PART(departure, ' ', 1),
    departure_time = SPLIT_PART(departure, ' ', 2),
    arrival_date   = SPLIT_PART(arrival, ' ', 1),
    arrival_time   = SPLIT_PART(arrival, ' ', 2);

ALTER TABLE flight_booking.flights
DROP
COLUMN departure,
DROP
COLUMN arrival;