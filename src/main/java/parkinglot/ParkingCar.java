package parkinglot;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public interface ParkingCar {
    Ticket park(Car car, ParkingLot parkingLot) throws NoCarToParkException, FullyOccupiedParkingLotException;
}
