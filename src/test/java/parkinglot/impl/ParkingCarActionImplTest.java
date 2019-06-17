package parkinglot.impl;

import org.junit.jupiter.api.Test;
import parkinglot.ParkingCarAction;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import static org.junit.jupiter.api.Assertions.*;
import static parkinglot.model.ParkingLot.getEmptyParkingLot;
import static util.ParkingLotUtil.getFullParkingLot;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
class ParkingCarActionImplTest {
    @Test
    void should_succeed_and_get_a_ticket_when_parking_given_a_car_and_a_not_full_parking_lot() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car car = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();

        Ticket ticket = parkingCarAction.park(car, parkingLot);

        assertNotNull(ticket);
        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_parking_given_a_car_and_a_full_parking_lot() {
        ParkingLot parkingLot = getFullParkingLot(10);
        Car car = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingCarAction.park(car, parkingLot));

        assertEquals(10, parkingLot.getOccupiedAmount().get());
    }

}