package parkinglot.impl;

import parkinglot.ParkingLotFacade;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.service.ParkingService;
import parkinglot.service.PickingUpService;
import parkinglot.util.Either;

import java.util.function.BiFunction;


/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class ParkingLotFacadeImpl implements ParkingLotFacade {
    private ParkingService parkingService;
    private PickingUpService pickingUpService;

    public ParkingLotFacadeImpl() {
        this.parkingService = new ParkingService();
        this.pickingUpService = new PickingUpService();
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Either> parkingFunctionality) throws Throwable {
        Either parkingResult = parkingFunctionality.apply(car, parkingLot);
        return (Ticket) parkingResult.getRight().orElseThrow(parkingResult::getLeft);
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws Throwable {
        Either parkingResult = parkingService.parkCar(car, parkingLot);
        return (Ticket) parkingResult.getRight().orElseThrow(parkingResult::getLeft);
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Either> pickingUpFunctionality) throws Throwable {
        Either pickingUpResult = pickingUpFunctionality.apply(ticket, parkingLot);
        return (Car) pickingUpResult.getRight().orElseThrow(pickingUpResult::getLeft);
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Throwable {
        Either pickingUpResult = pickingUpService.pickUpCar(ticket, parkingLot);
        return (Car) pickingUpResult.getRight().orElseThrow(pickingUpResult::getLeft);
    }
}
