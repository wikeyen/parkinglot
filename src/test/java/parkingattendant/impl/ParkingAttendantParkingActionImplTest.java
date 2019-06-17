package parkingattendant.impl;

import org.junit.jupiter.api.Test;
import parkingattendant.model.ParkingAttendant;
import parkingattendant.model.ParkingAttendantJunior;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.exception.NoParkingLotException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static util.ParkingLotUtil.*;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
class ParkingAttendantParkingActionImplTest {
    @Test
    void should_parking_into_the_first_parking_lot_and_get_a_ticket_when_parking_given_one_car_and_two_not_full_parking_lot() throws Exception {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);

        Ticket ticket = attendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(carToPark, parkingLots.get(0).getPool().get(ticket));
    }

    @Test
    void should_parking_into_the_second_parking_lot_and_get_a_ticket_when_parking_given_one_car_and_two_parking_lots_with_the_first_one_fully_occupied() throws Exception {
        List<ParkingLot> oneFullOneEmptyParkingLots = getFirstHalfFullParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(oneFullOneEmptyParkingLots);

        Ticket ticket = attendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(2, oneFullOneEmptyParkingLots.get(0).getOccupiedAmount().get());
        assertEquals(1, oneFullOneEmptyParkingLots.get(1).getOccupiedAmount().get());
        assertEquals(carToPark, oneFullOneEmptyParkingLots.get(1).getPool().get(ticket));
    }

    @Test
    void should_fail_when_parking_given_one_car_and_two_full_parking_lots() {
        List<ParkingLot> parkingLots = getFullParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);

        assertThrows(FullyOccupiedParkingLotException.class, () -> attendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_given_one_car_and_no_parking_lot() {
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(new ArrayList<>());

        assertThrows(NoParkingLotException.class, () -> attendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_without_car() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2, 2, 2);
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);

        assertThrows(NoCarToParkException.class, () -> attendant.park(null));
    }
}