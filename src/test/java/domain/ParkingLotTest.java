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
    void should_succeed_when_parking_given_a_car_and_a_not_full_parking_lot() throws NoCarToParkException, FullyOccupiedParkingLotException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car car = new Car();

        //when
        Tickets ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
        assertEquals(1, parkingLot.occupiedSlotNumbers());
    }

    @Test
    void should_fail_when_parking_given_a_car_and_a_full_parking_lot() {
        //given
        ParkingLot parkingLot = getFullParkingLot(10);
        Car car = new Car();

        //when
        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingLot.park(car));

        //then
        assertEquals(10, parkingLot.occupiedSlotNumbers());
    }

    @Test
    void should_succeed_when_picking_up_given_a_parked_car_and_a_valid_ticket() throws FullyOccupiedParkingLotException, NoCarToParkException, CarPickingUpWithNoTicketException, InvalidTicketException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);

        //when
        Car carPickedUp = parkingLot.pickUpCar(ticket);

        //then
        assertNotNull(carPickedUp);
        assertEquals(0, parkingLot.occupiedSlotNumbers());
    }


    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_no_ticket() throws FullyOccupiedParkingLotException, NoCarToParkException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        parkingLot.park(carToPark);

        //when
        assertThrows(CarPickingUpWithNoTicketException.class, () -> parkingLot.pickUpCar(null));

        //then
        assertEquals(1, parkingLot.occupiedSlotNumbers());
    }

    @Test
    void should_succeed_when_picking_up_given_a_parked_car_and_the_related_valid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException, CarPickingUpWithNoTicketException, InvalidTicketException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);

        //when
        Car carPickedUp = parkingLot.pickUpCar(ticket);

        //then
        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLot.occupiedSlotNumbers());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_invalid_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        parkingLot.park(carToPark);
        Tickets ticket = Tickets.getTicket();

        //when
        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(ticket));

        //then
        assertEquals(1, parkingLot.occupiedSlotNumbers());
    }

    @Test
    void should_fail_when_picking_up_given_a_parked_car_and_an_used_ticket() throws NoCarToParkException, FullyOccupiedParkingLotException {
        //given
        ParkingLot parkingLot = getEmptyParkingLot(10);
        Car carToPark = new Car();
        Tickets ticket = parkingLot.park(carToPark);
        Tickets.destroyTicket(ticket);

        //when
        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUpCar(ticket));

        //then
        assertEquals(1, parkingLot.occupiedSlotNumbers());
    }

}