package parkingattendant.impl;

import parkingattendant.ParkingAttendantPickingUpAction;
import parkinglot.ParkingLotFacade;
import parkinglot.impl.ParkingLotFacadeImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantPickingUpActionImpl implements ParkingAttendantPickingUpAction {
    private ParkingLotFacade parkingLotFacade;

    public ParkingAttendantPickingUpActionImpl() {
        this.parkingLotFacade = new ParkingLotFacadeImpl();
    }


    @Override
    public Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws Exception {
        return parkingLotFacade.pickUp(ticket, parkingLot);
    }
}
