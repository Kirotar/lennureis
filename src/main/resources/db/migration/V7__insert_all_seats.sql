DELETE FROM flight_booking.seats WHERE flight_id BETWEEN 1 AND 20;

/*Siin kasutatud tehisintellekti abi:
*/
DO $$
DECLARE
flight_id INT;
    seat_row INT;
    seat_column CHAR;
    seat_type VARCHAR(20);
    legroom BOOLEAN;
    exit_row BOOLEAN;
    seat_columns CHAR[] := ARRAY['A', 'B', 'C', 'D', 'E', 'F'];
BEGIN
FOR flight_id IN 1..20 LOOP
        FOR seat_row IN 1..25 LOOP
            FOREACH seat_column IN ARRAY seat_columns LOOP
                seat_type := CASE seat_column
                    WHEN 'A' THEN 'window'
                    WHEN 'B' THEN 'middle'
                    WHEN 'C' THEN 'aisle'
                    WHEN 'D' THEN 'aisle'
                    WHEN 'E' THEN 'middle'
                    WHEN 'F' THEN 'window'
END;

                legroom := seat_row IN (1, 2, 3);
                exit_row := seat_row IN (1, 12, 25);

INSERT INTO flight_booking.seats (
    flight_id, seat_column, seat_row, legroom, seat_type, exit_row, booked
) VALUES (
             flight_id, seat_column, seat_row, legroom, seat_type, exit_row, false
         );
END LOOP;
END LOOP;
END LOOP;
END $$;
