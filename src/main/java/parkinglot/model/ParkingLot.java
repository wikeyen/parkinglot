package parkinglot.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class ParkingLot {
    private final Map<Ticket, Car> pool;
    private final int capacity;
    private AtomicInteger occupiedAmount;

    private ParkingLot(int capacity) {
        this.capacity = capacity;
        this.pool = new ConcurrentHashMap<>(capacity);
        occupiedAmount = new AtomicInteger(0);
    }

    public static ParkingLot getEmptyParkingLot(int capacity) {
        return new ParkingLot(capacity);
    }

    public boolean isFull() {
        return occupiedAmount.get() >= capacity;
    }

    public Map<Ticket, Car> getPool() {
        return pool;
    }

    public int getCapacity() {
        return capacity;
    }

    public AtomicInteger getOccupiedAmount() {
        return occupiedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingLot that = (ParkingLot) o;
        return capacity == that.capacity &&
                Objects.equals(pool, that.pool) &&
                Objects.equals(occupiedAmount, that.occupiedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}
