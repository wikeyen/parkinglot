package parkinglot;

import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.util.Either;

import java.util.function.BiFunction;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public interface ParkingLotFacade {

    Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Either> biFunction) throws Throwable;

    Ticket park(Car car, ParkingLot parkingLot) throws Throwable;

    Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Either> biFunction) throws Throwable;

    Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Throwable;
}
