package parkingattendant.model;

import parkinglot.exception.FullyOccupiedParkingLotException;
import parkinglot.model.ParkingLot;

import java.util.List;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingAttendantGraduate extends ParkingAttendant {
    public ParkingAttendantGraduate(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.qualificationLevel = ParkingQualificationLevel.GRADUATE;
    }

    @Override
    protected ParkingLot findParkingLot() throws FullyOccupiedParkingLotException {
        return parkingLots.stream()
                          .filter(parkinglot -> !parkinglot.isFull())
                          .findFirst()
                          .orElseThrow(() -> new FullyOccupiedParkingLotException("All parking-lots are full"));
    }
}
