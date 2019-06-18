package parkinglot.functionality;

import com.sun.tools.javac.util.Pair;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.TicketAndParkingLotNotMatch;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.function.BiFunction;

import static parkinglot.model.Ticket.destroyTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class PickingUpFunction {

    public BiFunction<Ticket, ParkingLot, Pair> pickUpCar() {
        return ((ticket, parkingLot) -> {
            if (ticket == null) {
                return Pair.of(new CarPickingUpWithoutTicketException("You must have a ticket to pick up car"), null);
            }
            if (!parkingLot.equals(ticket.getParkingLot())) {
                return Pair.of(new TicketAndParkingLotNotMatch("You ticket and the parking lot does not match"), null);
            }
            if (!ticket.isValid() || !parkingLot.getPool().containsKey(ticket)) {
                return Pair.of(new InvalidTicketException("You ticket is invalid"), null);
            }

            Car car = parkingLot.getPool().remove(ticket);
            parkingLot.getOccupiedAmount().decrementAndGet();
            destroyTicket(ticket);
            return Pair.of(null, car);
        });
    }

}
