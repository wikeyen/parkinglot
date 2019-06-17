package parkinglot.impl;

import com.sun.tools.javac.util.Pair;
import parkinglot.ParkingLotFacade;
import parkinglot.model.Car;
import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

import java.util.function.BiFunction;


/**
 * @author yancy3@lenovo.com
 * created on 2019-06-18
 */
public class ParkingLotFacadeImpl implements ParkingLotFacade {
    private ParkingLotFunctionality functionality;

    public ParkingLotFacadeImpl() {
        this.functionality = new ParkingLotFunctionality();
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot, BiFunction<Car, ParkingLot, Pair> functionality) throws Exception {
        Pair parkingResult = functionality.apply(car, parkingLot);
        if (parkingResult.snd != null) {
            return (Ticket) parkingResult.snd;
        }

        throw (Exception) parkingResult.fst;
    }

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) throws Exception {
        Pair parkingResult = functionality.parkCar().apply(car, parkingLot);
        if (parkingResult.snd != null) {
            return (Ticket) parkingResult.snd;
        }

        throw (Exception) parkingResult.fst;
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot, BiFunction<Ticket, ParkingLot, Pair> functionality) throws Exception {
        Pair pickingUpResult = functionality.apply(ticket, parkingLot);
        if (pickingUpResult.snd != null) {
            return (Car) pickingUpResult.snd;
        }

        throw (Exception) pickingUpResult.fst;
    }

    @Override
    public Car pickUp(Ticket ticket, ParkingLot parkingLot) throws Exception {
        Pair pickingUpResult = functionality.pickUpCar().apply(ticket, parkingLot);
        if (pickingUpResult.snd != null) {
            return (Car) pickingUpResult.snd;
        }

        throw (Exception) pickingUpResult.fst;
    }
}
