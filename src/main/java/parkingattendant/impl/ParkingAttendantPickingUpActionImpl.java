package parkingattendant.impl;

import parkingattendant.ParkingAttendantPickingUpAction;
import parkinglot.PickingUpCarAction;
import parkinglot.exception.CarPickingUpWithoutTicketException;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.TicketAndParkingLotNotMatch;
import parkinglot.impl.PickingUpCarActionImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantPickingUpActionImpl implements ParkingAttendantPickingUpAction {
    private PickingUpCarAction pickingUpCarAction;

    public ParkingAttendantPickingUpActionImpl() {
        this.pickingUpCarAction = new PickingUpCarActionImpl();
    }

    @Override
    public Car pickUpCar(Ticket ticket, ParkingLot parkingLot) throws CarPickingUpWithoutTicketException, InvalidTicketException, TicketAndParkingLotNotMatch {
        return pickingUpCarAction.pickUpCar(ticket, parkingLot);
    }
}
