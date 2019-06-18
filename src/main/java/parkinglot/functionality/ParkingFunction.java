package parkinglot.functionality;

import com.sun.tools.javac.util.Pair;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.function.BiFunction;

import static parkinglot.model.Ticket.getNewTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class ParkingFunction {

    public BiFunction<Car, ParkingLot, Pair> parkCar() {
        return ((car, parkingLot) -> {
            if (car == null) {
                return Pair.of(new NoCarToParkException("You must have a car before parking"), null);
            }
            if (parkingLot.isFull()) {
                return Pair.of(new FullyOccupiedParkingLotException("The Parking lot is full"), null);
            }

            Ticket ticket = getNewTicket(parkingLot);
            parkingLot.getPool().put(ticket, car);
            parkingLot.getOccupiedAmount().incrementAndGet();
            return Pair.of(null, ticket);
        });
    }

}
