package parkingattendant.impl;

import parkingattendant.ParkingAttendantService;
import parkinglot.ParkingLotFacade;
import parkinglot.impl.ParkingLotFacadeImpl;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantServiceImpl implements ParkingAttendantService {
    private ParkingLotFacade parkingLotFacade;

    public ParkingAttendantServiceImpl() {
        this.parkingLotFacade = new ParkingLotFacadeImpl();
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws Throwable {
        return parkingLotFacade.park(car, parkingLot);
    }

    @Override
    public Car pickUpCar(Ticket ticket) throws Throwable {
        return parkingLotFacade.pickUp(ticket);
    }
}
