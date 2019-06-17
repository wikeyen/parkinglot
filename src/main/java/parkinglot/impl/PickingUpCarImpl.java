package parkinglot.impl;

import parkinglot.PickingUpCar;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import static parkinglot.model.Ticket.destroyTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class PickingUpCarImpl implements PickingUpCar {

    @Override
    public Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws CarPickingUpWithoutTicketException, InvalidTicketException {
        if (ticket == null) {
            throw new CarPickingUpWithoutTicketException("You must have a ticket to pick up car");
        }
        if (!ticket.isValid() || !parkingLot.getPool().containsKey(ticket)) {
            throw new InvalidTicketException("You ticket is invalid");
        }

        Car car = parkingLot.getPool().remove(ticket);
        parkingLot.getOccupiedAmount().decrementAndGet();
        destroyTicket(ticket);
        return car;
    }
}
