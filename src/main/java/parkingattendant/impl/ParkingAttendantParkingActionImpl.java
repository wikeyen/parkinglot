package parkingattendant.impl;

import parkingattendant.ParkingAttendantParkingAction;
import parkinglot.ParkingLotFacade;
import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.impl.ParkingLotFacadeImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantParkingActionImpl implements ParkingAttendantParkingAction {
    private ParkingLotFacade parkingLotFacade;

    public ParkingAttendantParkingActionImpl() {
        this.parkingLotFacade = new ParkingLotFacadeImpl();
    }

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) throws Exception {
        ParkingLot parkingLot = parkingLots.stream()
                                           .filter(parkinglot -> !parkinglot.isFull())
                                           .findFirst()
                                           .orElseThrow(() -> new FullyOccupiedParkingLotException("All parking-lots are full"));

        return parkingLotFacade.park(car, parkingLot);
    }
}
