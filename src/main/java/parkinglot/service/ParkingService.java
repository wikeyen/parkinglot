package parkinglot.service;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.util.Either;

import java.util.function.BiFunction;

import static parkinglot.model.Ticket.getNewTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class ParkingService {
    private BiFunction<Car, ParkingLot, Either> defaultParking = ((car, parkingLot) -> {
            if (car == null) {
                return Either.Left(new NoCarToParkException("You must have a car before parking"));
            }
            if (parkingLot.isFull()) {
                return Either.Left(new FullyOccupiedParkingLotException("The Parking lot is full"));
            }

            Ticket ticket = getNewTicket(parkingLot);
            parkingLot.getPool().put(ticket, car);
            parkingLot.getOccupiedAmount().incrementAndGet();
            return Either.Right(ticket);
        });

    public Either parkCar(Car car, ParkingLot parkingLot) {
        return defaultParking.apply(car, parkingLot);
    }

}
