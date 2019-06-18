package parkinglot.impl;

import com.sun.tools.javac.util.Pair;
import parkinglot.ParkingLotFacade;
import parkinglot.functionality.ParkingFunction;
import parkinglot.functionality.PickingUpFunction;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

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
    public Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Pair> parkingFunctionality) throws Exception {
        Pair parkingResult = parkingFunctionality.apply(car, parkingLot);
        if (parkingResult.snd != null) {
            return (Ticket) parkingResult.snd;
        }

        throw (Exception) parkingResult.fst;
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws Exception {
        Pair parkingResult = parkingFunction.parkCar().apply(car, parkingLot);
        if (parkingResult.snd != null) {
            return (Ticket) parkingResult.snd;
        }

        throw (Exception) parkingResult.fst;
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Pair> pickingUpFunctionality) throws Exception {
        Pair pickingUpResult = pickingUpFunctionality.apply(ticket, parkingLot);
        if (pickingUpResult.snd != null) {
            return (Car) pickingUpResult.snd;
        }

        throw (Exception) pickingUpResult.fst;
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Exception {
        Pair pickingUpResult = pickingUpFunction.pickUpCar().apply(ticket, parkingLot);
        if (pickingUpResult.snd != null) {
            return (Car) pickingUpResult.snd;
        }

        throw (Exception) pickingUpResult.fst;
    }
}
