package parkingattendant.model;

import org.junit.jupiter.api.Test;
import parkinglot.exception.*;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static parkinglot.model.Ticket.getNewTicket;
import static util.ParkingLotUtil.*;
import static util.TicketUtil.getInvalidTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-20
 */
class ParkingAttendantManagerTest {
    @Test
    void should_park_into_the_first_parking_lot_and_get_a_valid_ticket_from_it_when_parking_given_a_parking_car_manager_and_one_car_and_two_not_fully_occupied_parking_lots() throws Throwable {
        List<ParkingLot> parkingLots = getTwoPartiallyOccupiedParkingLotsWithEvenVacancies();
        ParkingAttendantManager manager = new ParkingAttendantManager(parkingLots);
        Car carToPark = new Car();

        Ticket ticket = manager.park(carToPark);

        assertNotNull(ticket);
        assertEquals(1, parkingLots.get(0).getOccupiedAmount().get());
    }


    @Test
    void should_parking_into_the_second_parking_lot_and_get_a_ticket_when_parking_given_one_parking_car_manager_one_car_and_two_parking_lots_with_the_first_one_fully_occupied() throws Throwable {
        List<ParkingLot> oneFullOneEmptyParkingLots = getFirstHalfFullParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantManager(oneFullOneEmptyParkingLots);

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
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantManager(parkingLots);

        assertThrows(FullyOccupiedParkingLotException.class, () -> graduateParkingAttendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_given_one_car_and_no_parking_lot() {
        Car carToPark = new Car();
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantManager(new ArrayList<>());

        assertThrows(NoParkingLotException.class, () -> graduateParkingAttendant.park(carToPark));
    }

    @Test
    void should_fail_when_parking_without_car_given_a_parking_car_manager() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2, 2, 2);
        ParkingAttendant graduateParkingAttendant = new ParkingAttendantManager(parkingLots);

        assertThrows(NoCarToParkException.class, () -> graduateParkingAttendant.park(null));
    }

    @Test
    void should_get_the_car_and_set_ticket_invalid_when_picking_up_from_the_correct_parking_lot_given_a_parking_car_manager_and_a_certain_parking_lot_with_a_parked_car_and_a_related_valid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getFirstHalfFullParkingLots(2, 2, 2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantManager(parkingLots);
        Ticket ticket = attendant.park(carToPark);
        parkingLots.get(2);

        Car carPickedUp = attendant.pickingUpCar(ticket);

        assertNotNull(carPickedUp);
        assertEquals(carToPark, carPickedUp);
        assertEquals(0, parkingLots.get(2).getOccupiedAmount().get());
        assertFalse(ticket.isValid());
    }

    @Test
    void should_not_getting_a_car_when_picking_up_given_a_parking_car_manager_and_an_invalid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2, 2);
        Car carToPark = new Car();
        ParkingAttendant attendant = new ParkingAttendantManager(parkingLots);
        attendant.park(carToPark);
        ParkingLot parkedParkingLot = parkingLots.get(0);
        Ticket ticket = getNewTicket(parkedParkingLot);

        assertThrows(InvalidTicketException.class, () -> attendant.pickingUpCar(ticket));
    }

    @Test
    void should_get_a_valid_ticket_when_asking_a_graduate_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_a_car_and_two_not_full_parking_lots() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);
        Car carToPark = new Car();

        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, carToPark);

        assertNotNull(ticket);
        assertEquals(1, parkingLots.get(0).getOccupiedAmount().get());
    }

    @Test
    void should_get_refused_for_parking_when_asking_the_graduate_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_no_car_and_two_empty_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, null));
    }

    @Test
    void should_parking_failed_when_asking_the_graduate_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_a_car_and_two_full_parking_lots() {
        List<ParkingLot> parkingLots = getFullParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);
        Car carToPark = new Car();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, carToPark));
    }

    @Test
    void should_refuse_to_park_when_asking_the_graduate_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_graduate_parking_attendant_and_no_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, null));
    }

    @Test
    void should_should_pick_and_return_the_car_to_the_manager_when_asking_the_graduate_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_graduate_parking_attendant_and_a_valid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);
        Car carToPark = new Car();
        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, carToPark);

        Car pickedUpCar = parkingAttendantManager.askEmployeeToPickUp(parkingAttendantGraduate, ticket);

        assertNotNull(ticket);
        assertEquals(carToPark, pickedUpCar);
    }

    @Test
    void should_fail_to_pick_up_a_car_when_asking_the_graduate_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_graduate_parking_attendant_and_and_invalid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, carToPark);
        Ticket ticket = getInvalidTicket();

        assertThrows(InvalidTicketException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantGraduate, ticket));
    }

    @Test
    void should_refuse_to_park_when_the_graduate_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_graduate_parking_attendant_and_no_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendantGraduate parkingAttendantGraduate = new ParkingAttendantGraduate(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantGraduate, carToPark);

        assertThrows(NoTicketForCarPickingUpException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantGraduate, null));
    }

    @Test
    void should_get_a_valid_ticket_when_asking_a_smart_parking_attendant_to_park_given_a_parking_car_manager_and_a_smart_parking_attendant_and_a_car_and_two_not_full_parking_lots() throws Throwable {
        List<ParkingLot> parkingLots = getTwoParkingLotsWithTheFirstOneHavingOneVacanciesAndSecondHavingFiveVacancies();
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);
        Car carToPark = new Car();

        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, carToPark);

        assertNotNull(ticket);
        assertEquals(6, parkingLots.get(1).getOccupiedAmount().get());
    }

    @Test
    void should_get_refused_for_parking_when_asking_the_smart_parking_attendant_to_park_given_a_parking_car_manager_and_a_smart_parking_attendant_and_no_car_and_two_empty_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, null));
    }

    @Test
    void should_parking_failed_when_asking_the_smart_parking_attendant_to_park_given_a_parking_car_manager_and_a_smart_parking_attendant_and_a_car_and_two_full_parking_lots() {
        List<ParkingLot> parkingLots = getFullParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);
        Car carToPark = new Car();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, carToPark));
    }

    @Test
    void should_refuse_to_park_when_asking_the_smart_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_smart_parking_attendant_and_no_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, null));
    }

    @Test
    void should_should_pick_and_return_the_car_to_the_manager_when_asking_the_smart_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_smart_parking_attendant_and_a_valid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);
        Car carToPark = new Car();
        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, carToPark);

        Car pickedUpCar = parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSmart, ticket);

        assertNotNull(ticket);
        assertEquals(carToPark, pickedUpCar);
    }

    @Test
    void should_fail_to_pick_up_a_car_when_asking_the_smart_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_smart_parking_attendant_and_and_invalid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, carToPark);
        Ticket ticket = getInvalidTicket();

        assertThrows(InvalidTicketException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSmart, ticket));
    }

    @Test
    void should_refuse_to_park_when_the_smart_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_smart_parking_attendant_and_no_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSmart = new ParkingAttendantSmart(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantSmart, carToPark);

        assertThrows(NoTicketForCarPickingUpException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSmart, null));
    }

    @Test
    void should_get_a_valid_ticket_when_asking_a_super_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_a_car_and_two_not_full_parking_lots() throws Throwable {
        List<ParkingLot> parkingLots = getTwoParkingLotsWithTheSecondHavingAHigherVacancyRate();
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);
        Car carToPark = new Car();

        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, carToPark);

        assertNotNull(ticket);
        assertEquals(6, parkingLots.get(1).getOccupiedAmount().get());
    }

    @Test
    void should_get_refused_for_parking_when_asking_the_super_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_no_car_and_two_empty_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, null));
    }

    @Test
    void should_parking_failed_when_asking_the_super_parking_attendant_to_park_given_a_parking_car_manager_and_a_graduate_parking_attendant_and_a_car_and_two_full_parking_lots() {
        List<ParkingLot> parkingLots = getFullParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);
        Car carToPark = new Car();

        assertThrows(FullyOccupiedParkingLotException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, carToPark));
    }

    @Test
    void should_refuse_to_park_when_asking_the_super_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_graduate_parking_attendant_and_no_parking_lots() {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);

        assertThrows(NoCarToParkException.class, () -> parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, null));
    }

    @Test
    void should_should_pick_and_return_the_car_to_the_manager_when_asking_the_super_parking_attendant_to_pick_up_the_car_given_a_car_parking_manager_and_a_super_parking_attendant_and_a_valid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);
        Car carToPark = new Car();
        Ticket ticket = parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, carToPark);

        Car pickedUpCar = parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSuper, ticket);

        assertNotNull(ticket);
        assertEquals(carToPark, pickedUpCar);
    }

    @Test
    void should_fail_to_pick_up_a_car_when_asking_the_super_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_super_parking_attendant_and_and_invalid_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, carToPark);
        Ticket ticket = getInvalidTicket();

        assertThrows(InvalidTicketException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSuper, ticket));
    }

    @Test
    void should_refuse_to_park_when_the_super_parking_attendant_to_pick_up_given_a_car_parking_manager_and_a_super_parking_attendant_and_no_ticket() throws Throwable {
        List<ParkingLot> parkingLots = getEmptyParkingLots(2);
        ParkingAttendantManager parkingAttendantManager = new ParkingAttendantManager();
        ParkingAttendant parkingAttendantSuper = new ParkingAttendantSuper(parkingLots);
        Car carToPark = new Car();
        parkingAttendantManager.askEmployeeToPark(parkingAttendantSuper, carToPark);

        assertThrows(NoTicketForCarPickingUpException.class, () -> parkingAttendantManager.askEmployeeToPickUp(parkingAttendantSuper, null));
    }
}