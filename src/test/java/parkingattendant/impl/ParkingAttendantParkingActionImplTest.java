package parkingattendant.impl;

import org.junit.jupiter.api.Test;
import parkingattendant.model.ParkingAttendant;
import parkingattendant.model.ParkingAttendantJunior;
import parkingattendant.model.ParkingAttendantSmart;
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
    void should_parking_into_the_first_parking_lot_and_get_a_ticket_when_parking_given_one_graduate_parking_attendant_one_car_and_two_not_full_parking_lot() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantJunior(parkingLots);

        Ticket ticket = graduateParkingAttendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(carToPark, parkingLots.get(0).getPool().get(ticket));
    }

    @Test
    void should_parking_into_the_second_parking_lot_and_get_a_ticket_when_parking_given_one_graduate_parking_attendant_one_car_and_two_parking_lots_with_the_first_one_fully_occupied() throws Throwable {
        List<ParkingLot> oneFullOneEmptyParkingLots = getFirstHalfFullParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantJunior(oneFullOneEmptyParkingLots);

        Ticket ticket = graduateParkingAttendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(2, oneFullOneEmptyParkingLots.get(0).getOccupiedAmount().get());
        assertEquals(1, oneFullOneEmptyParkingLots.get(1).getOccupiedAmount().get());
        assertEquals(carToPark, oneFullOneEmptyParkingLots.get(1).getPool().get(ticket));
    }

    @Test
    void should_fail_when_parking_given_one_car_and_two_full_parking_lots() {
        List<ParkingLot> parkingLots = getFullParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantJunior(parkingLots);

        assertThrows(FullyOccupiedParkingLotException.class, () -> graduateParkingAttendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_given_one_car_and_no_parking_lot() {
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantJunior(new ArrayList<>());

        assertThrows(NoParkingLotException.class, () -> graduateParkingAttendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_without_car() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2, 2, 2);
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantJunior(parkingLots);

        assertThrows(NoCarToParkException.class, () -> graduateParkingAttendant.park(null));
    }

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
        List<ParkingLot> parkingLots = getTwoParkingLotsWithBothEvenlyVacancies();
        Car carToPark = new Car();
        ParkingAttendant smartParkingAttendant = new ParkingAttendantSmart(parkingLots);

        Ticket ticket = smartParkingAttendant.park(carToPark);

        assertNotNull(ticket);
        assertEquals(1, parkingLots.get(0).getOccupiedAmount().get());
    }
}