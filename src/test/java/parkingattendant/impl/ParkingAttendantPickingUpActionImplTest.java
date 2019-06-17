package parkingattendant.impl;

import org.junit.jupiter.api.Test;
import parkingattendant.model.ParkingAttendant;
import parkingattendant.model.ParkingAttendantJunior;
import parkinglot.exception.*;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static parkinglot.model.Ticket.getNewTicket;
import static util.ParkingLotUtil.getEmptyParkingLots;
import static util.ParkingLotUtil.getFirstHalfFullParkingLots;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
class ParkingAttendantPickingUpActionImplTest {
    @Test
    void should_get_the_car_and_set_ticket_invalid_when_picking_up_from_the_correct_parking_lot_given_a_certain_parking_lot_with_a_parked_car_and_a_related_valid_ticket() throws NoParkingLotException, FullyOccupiedParkingLotException, NoCarToParkException, InvalidTicketException, CarPickingUpWithoutTicketException, TicketAndParkingLotNotMatch {
        List<ParkingLot> parkingLots = getFirstHalfFullParkingLots(2, 2, 2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);
        Ticket ticket = attendant.park(carToPark);
        ParkingLot parkedParkingLot = parkingLots.get(2);

        Car carPickedUp = attendant.pickingUpCar(ticket, parkedParkingLot);

        assertNotNull(carPickedUp);
        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLots.get(2).getOccupiedAmount().get());
        assertFalse(ticket.isValid());
    }

    @Test
    void should_not_get_the_car_and_keep_ticket_valid_when_picking_up_from_the_wrong_parking_lot_given_a_certain_parking_lot_with_a_parked_car_and_a_related_valid_ticket() throws NoParkingLotException, FullyOccupiedParkingLotException, NoCarToParkException {
        List<ParkingLot> parkingLots = getFirstHalfFullParkingLots(2, 2, 2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);
        Ticket ticket = attendant.park(carToPark);
        ParkingLot notParkedParkingLot = parkingLots.get(3);

        assertThrows(TicketAndParkingLotNotMatch.class, () -> attendant.pickingUpCar(ticket, notParkedParkingLot));
        assertTrue(ticket.isValid());
    }

    @Test
    void should_not_to_get_a_car_when_picking_up_given_an_invalid_ticket() throws NoParkingLotException, FullyOccupiedParkingLotException, NoCarToParkException {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantJunior(parkingLots);
        attendant.park(carToPark);
        ParkingLot parkedParkingLot = parkingLots.get(0);
        Ticket ticket = getNewTicket(parkedParkingLot);

        assertThrows(InvalidTicketException.class, () -> attendant.pickingUpCar(ticket, parkedParkingLot));
    }
}