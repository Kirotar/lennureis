ALTER TABLE flight_booking.seats
ADD seat_row INT;

ALTER TABLE flight_booking.seats
ADD seat_column CHAR;

ALTER TABLE flight_booking.seats
DROP COLUMN seat_nr;