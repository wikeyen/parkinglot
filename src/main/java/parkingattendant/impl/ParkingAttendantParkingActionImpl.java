package parkingattendant.impl;

import parkingattendant.ParkingAttendantParkingAction;
import parkinglot.ParkingCarAction;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.exception.NoCarToParkException;
import parkinglot.impl.ParkingCarActionImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantParkingActionImpl implements ParkingAttendantParkingAction {
    private ParkingCarAction parkingCarAction;

    public ParkingAttendantParkingActionImpl() {
        this.parkingCarAction = new ParkingCarActionImpl();
    }

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) throws FullyOccupiedParkingLotException, NoCarToParkException {
        ParkingLot parkingLot = parkingLots.stream()
                                           .filter(parkinglot -> !parkinglot.isFull())
                                           .findFirst()
                                           .orElseThrow(() -> new FullyOccupiedParkingLotException("All parking-lots are full"));

        return parkingCarAction.park(car, parkingLot);
    }
}
