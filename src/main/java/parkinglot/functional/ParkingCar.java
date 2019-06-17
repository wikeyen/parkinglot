package parkinglot.functional;

import com.sun.tools.javac.util.Pair;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
@FunctionalInterface
public interface ParkingCar {
    Pair park(Car car, ParkingLot parkingLot) throws NoCarToParkException, FullyOccupiedParkingLotException;
}
