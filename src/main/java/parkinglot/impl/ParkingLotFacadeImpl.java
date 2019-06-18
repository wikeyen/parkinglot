package parkinglot.impl;

import parkinglot.ParkingLotFacade;
import parkinglot.functionality.ParkingFunction;
import parkinglot.functionality.PickingUpFunction;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;
import parkinglot.util.Either;

import java.util.function.BiFunction;


/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class ParkingLotFacadeImpl implements ParkingLotFacade {
    private ParkingFunction parkingFunction;
    private PickingUpFunction pickingUpFunction;

    public ParkingLotFacadeImpl() {
        this.parkingFunction = new ParkingFunction();
        this.pickingUpFunction = new PickingUpFunction();
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Either> parkingFunctionality) throws Throwable {
        Either parkingResult = parkingFunctionality.apply(car, parkingLot);
        return (Ticket) parkingResult.getRight().orElseThrow(parkingResult::getLeft);
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws Throwable {
        Either parkingResult = parkingFunction.parkCar().apply(car, parkingLot);
        return (Ticket) parkingResult.getRight().orElseThrow(parkingResult::getLeft);
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Either> pickingUpFunctionality) throws Throwable {
        Either pickingUpResult = pickingUpFunctionality.apply(ticket, parkingLot);
        return (Car) pickingUpResult.getRight().orElseThrow(pickingUpResult::getLeft);
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Throwable {
        Either pickingUpResult = pickingUpFunction.pickUpCar().apply(ticket, parkingLot);
        return (Car) pickingUpResult.getRight().orElseThrow(pickingUpResult::getLeft);
    }
}
