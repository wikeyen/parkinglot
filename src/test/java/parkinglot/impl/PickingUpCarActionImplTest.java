package parkinglot.impl;

import org.junit.jupiter.api.Test;
import parkinglot.ParkingCarAction;
import parkinglot.PickingUpCarAction;
import parkinglot.exception.*;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.model.ParkingLot.getEmptyParkingLot;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
class PickingUpCarActionImplTest {
    @Test
    void should_succeed_and_get_the_car_when_picking_up_given_a_parked_car_and_the_related_valid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException, CarPickingUpWithoutTicketException, InvalidTicketException, TicketAndParkingLotNotMatch {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();
        PickingUpCarAction pickingUpCarAction = new PickingUpCarActionImpl();
        Ticket ticket = parkingCarAction.park(carToPark, parkingLot);

        Car carPickedUp = pickingUpCarAction.pickUpCar(ticket, parkingLot);

        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_invalid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();
        PickingUpCarAction pickingUpCarAction = new PickingUpCarActionImpl();
        parkingCarAction.park(carToPark, parkingLot);
        Ticket ticket = Ticket.getNewTicket(parkingLot);

        assertThrows(InvalidTicketException.class, () -> pickingUpCarAction.pickUpCar(ticket, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_used_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();
        PickingUpCarAction pickingUpCarAction = new PickingUpCarActionImpl();
        Ticket ticket = parkingCarAction.park(carToPark, parkingLot);
        Ticket.destroyTicket(ticket);

        assertThrows(InvalidTicketException.class, () -> pickingUpCarAction.pickUpCar(ticket, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_any_parked_car_and_no_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCarAction parkingCarAction = new ParkingCarActionImpl();
        PickingUpCarAction pickingUpCarAction = new PickingUpCarActionImpl();
        parkingCarAction.park(carToPark, parkingLot);

        assertThrows(CarPickingUpWithoutTicketException.class, () -> pickingUpCarAction.pickUpCar(null, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());

    }
}