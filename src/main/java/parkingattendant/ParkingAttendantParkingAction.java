package parkingattendant;

import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public interface ParkingAttendantParkingAction {
    Ticket park(Car car, List<ParkingLot> parkingLots) throws Throwable;
}
