package parkinglot.functional;

import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.TicketAndParkingLotNotMatch;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
@FunctionalInterface
public interface PickingUpCar {
    Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws CarPickingUpWithoutTicketException, InvalidTicketException, TicketAndParkingLotNotMatch;
}
