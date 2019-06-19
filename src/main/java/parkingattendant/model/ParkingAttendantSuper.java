package parkingattendant.model;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.model.ParkingLot;

import java.util.Comparator;
import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-20
 */
public class ParkingAttendantSuper extends ParkingAttendant {
    public ParkingAttendantSuper(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.GRADUATE;
    }

    @Override
    protected ParkingLot findParkingLot() throws FullyOccupiedParkingLotException {
        return parkingLots.stream()
                          .filter(p -> !p.isFull())
                          .min(Comparator.comparing(ParkingLot::getLoad))
                          .orElseThrow(() -> new FullyOccupiedParkingLotException("The parking lots are full"));
    }
}
