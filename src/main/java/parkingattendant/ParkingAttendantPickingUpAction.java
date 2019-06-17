package parkingattendant;

import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public interface ParkingAttendantPickingUpAction {
    Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws Exception;
}
