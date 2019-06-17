package parkinglot;

import com.sun.tools.javac.util.Pair;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.function.BiFunction;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public interface ParkingLotFacade {

    Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Pair> biFunction) throws Exception;

    Ticket park(Car car, ParkingLot parkingLot) throws Exception;

    Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Pair> biFunction) throws Exception;

    Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Exception;
}
