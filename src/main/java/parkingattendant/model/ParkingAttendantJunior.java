package parkingattendant.model;

import parkingattendant.impl.ParkingAttendantParkingActionImpl;
import parkingattendant.impl.ParkingAttendantPickingUpActionImpl;
import parkinglot.exception.*;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantJunior extends ParkingAttendant {
    public ParkingAttendantJunior(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.JUNIOR;
        this.parkingAction = new ParkingAttendantParkingActionImpl();
        this.pickingUpAction = new ParkingAttendantPickingUpActionImpl();
    }

    @Override
    public Ticket park(Car car) throws NoCarToParkException, FullyOccupiedParkingLotException, NoParkingLotException {
        if (car == null) {
            throw new NoCarToParkException("You must have a car before parking");
        }
        if (parkingLots == null || parkingLots.size() == 0) {
            throw new NoParkingLotException("There is no parking-lot to park");
        }
        return parkingAction.park(car, parkingLots);
    }

    @Override
    public Car pickingUpCar(Ticket ticket, ParkingLot parkingLot) throws InvalidTicketException, CarPickingUpWithoutTicketException, TicketAndParkingLotNotMatch {
        return pickingUpAction.pickUpCar(ticket, parkingLot);
    }
}
