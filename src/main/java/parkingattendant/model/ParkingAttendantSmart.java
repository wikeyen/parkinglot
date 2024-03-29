package parkingattendant.model;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.model.ParkingLot;

import java.util.Comparator;
import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-19
 */
public class ParkingAttendantSmart extends ParkingAttendant {
    public ParkingAttendantSmart(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.SMART;
    }

    @Override
    protected ParkingLot findParkingLot() throws FullyOccupiedParkingLotException {
        return parkingLots.stream()
                          .filter(p -> !p.isFull())
                          .min(Comparator.comparingInt(a -> a.getOccupiedAmount().get()))
                          .orElseThrow(() -> new FullyOccupiedParkingLotException("All parking-lots are full"));
    }
}
