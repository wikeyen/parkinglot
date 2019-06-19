package parkinglot.service;

import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.util.Either;

import java.util.function.Function;

import static parkinglot.model.Ticket.destroyTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class PickingUpService {
    private Function<Ticket, Either> defaultPickingUpCar = ((ticket) -> {
        if (ticket == null) {
            return Either.Left(new CarPickingUpWithoutTicketException("You must have a ticket to pick up car"));
        }
        if (!ticket.isValid() || !ticket.getParkingLot().getPool().containsKey(ticket)) {
            return Either.Left(new InvalidTicketException("You ticket is invalid"));
        }

        ParkingLot parkingLot = ticket.getParkingLot();
        Car car = parkingLot.getPool().remove(ticket);
        parkingLot.getOccupiedAmount().decrementAndGet();
        destroyTicket(ticket);
        return Either.Right(car);
    });

    public Either pickUpCar(Ticket ticket) {
        return defaultPickingUpCar.apply(ticket);
    }

}
