package parkingattendant.model;

import parkingattendant.ParkingAttendantService;
import parkingattendant.impl.ParkingAttendantServiceImpl;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.exception.NoParkingLotException;
import parkinglot.exception.NoTicketForCarPickingUpException;
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
    protected ParkingAttendantService parkingService;

    protected ParkingAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.parkingService = new ParkingAttendantServiceImpl();
    }

    protected void checkCar(Car car) throws NoCarToParkException, NoParkingLotException {
        if (car == null) {
            throw new NoCarToParkException("You must have a car before parking");
        }
        if (parkingLots == null || parkingLots.size() == 0) {
            throw new NoParkingLotException("There is no parking-lot to park");
        }
    }

    protected void checkTicket(Ticket ticket) throws NoTicketForCarPickingUpException {
        if (ticket == null) {
            throw new NoTicketForCarPickingUpException("You must have a ticket");
        }
    }

    protected abstract ParkingLot findParkingLot() throws NoParkingLotException, FullyOccupiedParkingLotException;

    public Ticket park(Car car) throws Throwable {
        checkCar(car);
        return parkingService.park(car, findParkingLot());
    }

    public Car pickingUpCar(Ticket ticket) throws Throwable {
        checkTicket(ticket);
        return parkingService.pickUpCar(ticket);
    }

    ;

}
