package parkinglot.impl;

import parkinglot.PickingUpCarAction;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.TicketAndParkingLotNotMatch;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import static parkinglot.model.Ticket.destroyTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class PickingUpCarActionImpl implements PickingUpCarAction {

    @Override
    public Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws CarPickingUpWithoutTicketException, InvalidTicketException, TicketAndParkingLotNotMatch {
        if (ticket == null) {
            throw new CarPickingUpWithoutTicketException("You must have a ticket to pick up car");
        }
        if (!parkingLot.equals(ticket.getParkingLot())) {
            throw new TicketAndParkingLotNotMatch("You ticket and the parking lot does not match");
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
