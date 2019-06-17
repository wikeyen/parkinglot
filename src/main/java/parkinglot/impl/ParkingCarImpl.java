package parkinglot.impl;

import parkinglot.ParkingCar;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import static parkinglot.model.Ticket.getNewTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingCarImpl implements ParkingCar {

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws NoCarToParkException, FullyOccupiedParkingLotException {
        if (car == null) {
            throw new NoCarToParkException("You must have a car to park");
        }
        if (parkingLot.isFull()) {
            throw new FullyOccupiedParkingLotException("The Parking lot is full");
        }

        Ticket ticket = getNewTicket();
        parkingLot.getPool().put(ticket, car);
        parkingLot.getOccupiedAmount().incrementAndGet();
        return ticket;
    }
}
