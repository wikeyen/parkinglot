package parkinglot.impl;

import org.junit.jupiter.api.Test;
import parkinglot.ParkingLotFacade;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.InvalidTicketException;
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
class ParkingLotFacadeImplTest {
    @Test
    void should_succeed_and_get_a_ticket_when_parking_given_a_car_and_a_not_full_parking_lot() throws Throwable {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car car = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();

        Ticket ticket = parkingLotFacade.park(car, parkingLot);

        assertNotNull(ticket);
        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_parking_given_a_car_and_a_full_parking_lot() {
        ParkingLot parkingLot = getFullParkingLot(10);
        Car car = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingLotFacade.park(car, parkingLot));

        assertEquals(10, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_succeed_and_get_the_car_when_picking_up_given_a_parked_car_and_the_related_valid_ticket() throws Throwable {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();
        Ticket ticket = parkingLotFacade.park(carToPark, parkingLot);

        Car carPickedUp = parkingLotFacade.pickUp(ticket);

        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_invalid_ticket() throws Throwable {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();
        parkingLotFacade.park(carToPark, parkingLot);
        Ticket ticket = Ticket.getNewTicket(parkingLot);

        assertThrows(InvalidTicketException.class, () -> parkingLotFacade.pickUp(ticket));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_used_ticket() throws Throwable {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();
        Ticket ticket = parkingLotFacade.park(carToPark, parkingLot);
        Ticket.destroyTicket(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingLotFacade.pickUp(ticket));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_any_parked_car_and_no_ticket() throws Throwable {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingLotFacade parkingLotFacade = new ParkingLotFacadeImpl();
        parkingLotFacade.park(carToPark, parkingLot);

        assertThrows(CarPickingUpWithoutTicketException.class, () -> parkingLotFacade.pickUp(null));

        assertEquals(1, parkingLot.getOccupiedAmount().get());

    }
}