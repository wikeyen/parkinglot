package parkingattendant;

import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public interface ParkingAttendantService {
    Ticket park(Car car, ParkingLot parkingLot) throws Throwable;

    Car pickUpCar(Ticket ticket) throws Throwable;
}
