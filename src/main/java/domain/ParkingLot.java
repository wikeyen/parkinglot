package domain;

import exception.CarPickingUpWithNoTicketException;
import exception.FullyOccupiedParkingLotException;
import exception.InvalidTicketException;
import exception.NoCarToParkException;

import java.util.HashMap;
import java.util.Map;

import static domain.Tickets.destroyTicket;
import static domain.Tickets.getTicket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class ParkingLot {
    private final Map<Tickets, Car> pool;
    private final int capacity;
    private int occupiedAmount;

    private ParkingLot(int capacity) {
        this.capacity = capacity;
        this.pool = new HashMap<>(capacity);
    }

    public static ParkingLot getEmptyParkingLot(int capacity) {
        return new ParkingLot(capacity);
    }

    public static ParkingLot getFullParkingLot(int capacity) {
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.occupiedAmount = capacity;
        return parkingLot;
    }

    public Tickets park(Car car) throws NoCarToParkException, FullyOccupiedParkingLotException {
        if (car == null) {
            throw new NoCarToParkException("You must have a car to park");
        }
        if (isFull()) {
            throw new FullyOccupiedParkingLotException("The Parking lot is full");
        }

        Tickets ticket = getTicket();
        pool.put(ticket, car);
        occupiedAmount++;
        return ticket;
    }

    public Car pickUpCar(Tickets ticket) throws CarPickingUpWithNoTicketException, InvalidTicketException {
        if (ticket == null) {
            throw new CarPickingUpWithNoTicketException("You must have a ticket to pick up car");
        }
        if (!ticket.isValid() || !pool.containsKey(ticket)) {
            throw new InvalidTicketException("You ticket is invalid");
        }

        Car car = pool.remove(ticket);
        occupiedAmount--;
        destroyTicket(ticket);
        return car;
    }

    public int occupiedSlotNumbers() {
        return occupiedAmount;
    }

    public boolean isFull() {
        return occupiedAmount >= capacity;
    }
}
