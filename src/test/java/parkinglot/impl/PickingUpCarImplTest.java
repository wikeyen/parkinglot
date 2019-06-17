package parkinglot.impl;

import org.junit.jupiter.api.Test;
import parkinglot.ParkingCar;
import parkinglot.PickingUpCar;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.NoCarToParkException;
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
class PickingUpCarImplTest {
    @Test
    void should_succeed_and_get_the_car_when_picking_up_given_a_parked_car_and_the_related_valid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException, CarPickingUpWithoutTicketException, InvalidTicketException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCar parkingCar = new ParkingCarImpl();
        PickingUpCar pickingUpCar = new PickingUpCarImpl();
        Ticket ticket = parkingCar.park(carToPark, parkingLot);

        Car carPickedUp = pickingUpCar.pickUpCar(ticket, parkingLot);

        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_invalid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCar parkingCar = new ParkingCarImpl();
        PickingUpCar pickingUpCar = new PickingUpCarImpl();
        parkingCar.park(carToPark, parkingLot);
        Ticket ticket = Ticket.getNewTicket();

        assertThrows(InvalidTicketException.class, () -> pickingUpCar.pickUpCar(ticket, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_used_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCar parkingCar = new ParkingCarImpl();
        PickingUpCar pickingUpCar = new PickingUpCarImpl();
        Ticket ticket = parkingCar.park(carToPark, parkingLot);
        Ticket.destroyTicket(ticket);

        assertThrows(InvalidTicketException.class, () -> pickingUpCar.pickUpCar(ticket, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());
    }

    @Test
    void should_fail_when_picking_up_given_any_parked_car_and_no_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        ParkingCar parkingCar = new ParkingCarImpl();
        PickingUpCar pickingUpCar = new PickingUpCarImpl();
        parkingCar.park(carToPark, parkingLot);

        assertThrows(CarPickingUpWithoutTicketException.class, () -> pickingUpCar.pickUpCar(null, parkingLot));

        assertEquals(1, parkingLot.getOccupiedAmount().get());

    }
}