package parkingattendant.model;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-20
 */
public class ParkingAttendantManager extends ParkingAttendant {
    public ParkingAttendantManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.MANAGER;
    }

    public ParkingAttendantManager() {
        super();
        this.qualificationLevel = ParkingQualificationLevel.MANAGER;
    }

    @Override
    protected ParkingLot findParkingLot() throws FullyOccupiedParkingLotException {
        return parkingLots.stream()
                          .filter(parkinglot -> !parkinglot.isFull())
                          .findFirst()
                          .orElseThrow(() -> new FullyOccupiedParkingLotException("All parking-lots are full"));
    }

    public Ticket askEmployeeToPark(ParkingAttendant parkingAttendant, Car car) throws Throwable {
        return parkingAttendant.park(car);
    }

    public Car askEmployeeToPickUp(ParkingAttendant parkingAttendant, Ticket ticket) throws Throwable {
        return parkingAttendant.pickingUpCar(ticket);
    }
}
