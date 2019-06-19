package parkingattendant.model;

import parkingattendant.ParkingAttendantParkingAction;
import parkingattendant.ParkingAttendantPickingUpAction;
import parkinglot.exception.NoCarToParkException;
import parkinglot.exception.NoParkingLotException;
import parkinglot.exception.NoTicketException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public abstract class ParkingAttendant {
    protected ParkingQualificationLevel qualificationLevel;
    protected List<ParkingLot> parkingLots;
    protected ParkingAttendantParkingAction parkingAction;
    protected ParkingAttendantPickingUpAction pickingUpAction;

    protected ParkingAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    protected void assertCarAndParkingLot(Car car) throws NoCarToParkException, NoParkingLotException {
        if (car == null) {
            throw new NoCarToParkException("You must have a car before parking");
        }
        if (parkingLots == null || parkingLots.size() == 0) {
            throw new NoParkingLotException("There is no parking-lot to park");
        }
    }

    protected void assertTicket(Ticket ticket) throws NoTicketException {
        if (ticket == null) {
            throw new NoTicketException("You must have a ticket");
        }
    }

    public abstract Ticket park(Car car) throws Throwable;

    public abstract Car pickingUpCar(Ticket ticket, ParkingLot parkingLot) throws Throwable;

}
