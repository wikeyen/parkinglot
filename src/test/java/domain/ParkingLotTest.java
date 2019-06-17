package domain;

import exception.CarPickingUpWithNoTicketException;
import exception.FullyOccupiedParkingLotException;
import exception.InvalidTicketException;
import exception.NoCarToParkException;
import org.junit.jupiter.api.Test;

import static domain.ParkingLot.getEmptyParkingLot;
import static domain.ParkingLot.getFullParkingLot;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
class ParkingLotTest {
    @Test
    void should_succeed_and_get_a_ticket_when_parking_given_a_car_and_a_not_full_parking_lot() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car car = new Car();

        Tickets ticket = parkingLot.park(car);

        assertNotNull(ticket);
        assertEquals(1, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_fail_when_parking_given_a_car_and_a_full_parking_lot() {
        ParkingLot parkingLot = getFullParkingLot(10);
        Car car = new Car();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingLot.park(car));

        assertEquals(10, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_succeed_and_get_the_car_when_picking_up_given_a_parked_car_and_the_referenced_valid_ticket() throws FullyOccupiedParkingLotException, NoCarToParkException, CarPickingUpWithNoTicketException, InvalidTicketException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);

        Car carPickedUp = parkingLot.pickUpCar(ticket);

        assertNotNull(carPickedUp);
        assertEquals(0, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_no_ticket() throws FullyOccupiedParkingLotException, NoCarToParkException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        parkingLot.park(carToPark);

        assertThrows(CarPickingUpWithNoTicketException.class, () -> parkingLot.pickUpCar(null));

        assertEquals(1, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_succeed_and_get_the_car_when_picking_up_given_a_parked_car_and_the_related_valid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException, CarPickingUpWithNoTicketException, InvalidTicketException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);

        Car carPickedUp = parkingLot.pickUpCar(ticket);

        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_invalid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        parkingLot.park(carToPark);
        Tickets ticket = Tickets.getTicket();

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(ticket));

        assertEquals(1, parkingLot.occupiedSlotsNumber());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_used_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);
        Tickets.destroyTicket(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(ticket));

        assertEquals(1, parkingLot.occupiedSlotsNumber());
    }

}