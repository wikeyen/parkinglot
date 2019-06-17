package parkingattendant.model;

import parkingattendant.ParkingAttendantParkingAction;
import parkingattendant.ParkingAttendantPickingUpAction;
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

    public abstract Ticket park(Car car) throws Exception;

    public abstract Car pickingUpCar(Ticket ticket, ParkingLot parkingLot) throws Exception;

}
