package parkingattendant.model;

import parkingattendant.impl.ParkingAttendantParkingActionImpl;
import parkingattendant.impl.ParkingAttendantPickingUpActionImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-19
 */
public class ParkingAttendantSmart extends ParkingAttendant {
    public ParkingAttendantSmart(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.SMART;
        this.parkingAction = new ParkingAttendantParkingActionImpl();
        this.pickingUpAction = new ParkingAttendantPickingUpActionImpl();
    }

    @Override
    public Ticket park(Car car) throws Throwable {
        assertCarAndParkingLot(car);
        return parkingAction.park(car, parkingLots);
    }

    @Override
    public Car pickingUpCar(Ticket ticket, ParkingLot parkingLot) throws Throwable {
        return null;
    }
}
