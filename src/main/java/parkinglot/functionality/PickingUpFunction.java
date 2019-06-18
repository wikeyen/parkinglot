package parkinglot.functionality;

import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.TicketAndParkingLotNotMatch;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.util.Either;

import java.util.function.BiFunction;

import static parkinglot.model.Ticket.destroyTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class PickingUpFunction {

    public BiFunction<Ticket, ParkingLot, Either> pickUpCar() {
        return ((ticket, parkingLot) -> {
            if (ticket == null) {
                return Either.Left(new CarPickingUpWithoutTicketException("You must have a ticket to pick up car"));
            }
            if (!parkingLot.equals(ticket.getParkingLot())) {
                return Either.Left(new TicketAndParkingLotNotMatch("You ticket and the parking lot does not match"));
            }
            if (!ticket.isValid() || !parkingLot.getPool().containsKey(ticket)) {
                return Either.Left(new InvalidTicketException("You ticket is invalid"));
            }

            Car car = parkingLot.getPool().remove(ticket);
            parkingLot.getOccupiedAmount().decrementAndGet();
            destroyTicket(ticket);
            return Either.Right(car);
        });
    }

}
