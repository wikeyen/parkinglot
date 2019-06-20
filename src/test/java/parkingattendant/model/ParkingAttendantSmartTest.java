package parkingattendant.model;

import org.junit.jupiter.api.Test;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static util.ParkingLotUtil.*;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-20
 */
class ParkingAttendantSmartTest {

    @Test
    void should_parking_into_the_second_parking_lot_and_get_a_ticket_when_parking_given_one_smart_parking_attendant_one_car_and_two_not_full_parking_lots_with_the_first_one_more_occupied() throws Throwable {
        List<ParkingLot> parkingLots = getTwoParkingLotsWithTheFirstOneHavingOneVacanciesAndSecondHavingFiveVacancies();
        Car carToPark = new Car();
        ParkingAttendant smartParkingAttendant = new ParkingAttendantSmart(parkingLots);

        Ticket ticket = smartParkingAttendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(6, parkingLots.get(1).getOccupiedAmount().get());
    }

    @Test
    void should_parking_into_the_first_parking_lot_and_get_a_ticket_when_parking_given_one_smart_parking_attendant_one_car_and_two_evenly_occupied_parking_lots_with_some_vacancies() throws Throwable {
        List<ParkingLot> parkingLots = getTwoPartiallyOccupiedParkingLotsWithEvenVacancies();
        Car carToPark = new Car();
        ParkingAttendant smartParkingAttendant = new ParkingAttendantSmart(parkingLots);

        Ticket ticket = smartParkingAttendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(1, parkingLots.get(0).getOccupiedAmount().get());
    }


    @Test
    void should_get_the_car_and_set_ticket_invalid_when_picking_up_from_the_correct_parking_lot_given_a_smart_parking_attendant_a_certain_parking_lot_with_a_parked_car_and_a_related_valid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getFirstHalfFullParkingLots(2, 2, 2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantSmart(parkingLots);
        Ticket ticket = attendant.park(carToPark);
        parkingLots.get(2);

        Car carPickedUp = attendant.pickingUpCar(ticket);

        assertNotNull(carPickedUp);
        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLots.get(2).getOccupiedAmount().get());
        assertFalse(ticket.isValid());
    }

}