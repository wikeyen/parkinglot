package util;

import parkinglot.model.ParkingLot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-17
 */
public class ParkingLotUtil {
    private ParkingLotUtil() {}

    public static ParkingLot getFullParkingLot(int capacity) {
        ParkingLot parkingLot = ParkingLot.getEmptyParkingLot(capacity);
        parkingLot.getOccupiedAmount().set(capacity);
        return parkingLot;
    }

    public static List<ParkingLot> getEmptyParkingLots(int... capacity) {
        return Arrays.stream(capacity).mapToObj(ParkingLot::getEmptyParkingLot).collect(Collectors.toList());
    }

    public static List<ParkingLot> getFullParkingLots(int... capacity) {
        return Arrays.stream(capacity).mapToObj(ParkingLotUtil::getFullParkingLot).collect(Collectors.toList());
    }

    public static List<ParkingLot> getFirstHalfFullParkingLots(int... capacity) {
        List<ParkingLot> parkingLots = Arrays.stream(capacity)
                                             .mapToObj(ParkingLot::getEmptyParkingLot)
                                             .collect(Collectors.toList());

        parkingLots.stream().limit(capacity.length / 2).forEach(parkingLot -> parkingLot.getOccupiedAmount().set(parkingLot.getCapacity()));
        return parkingLots;
    }

    public static List<ParkingLot> getTwoParkingLotsWithTheFirstOneHavingOneVacanciesAndSecondHavingFiveVacancies() {
        ParkingLot firstParkingLot = ParkingLot.getEmptyParkingLot(10);
        firstParkingLot.getOccupiedAmount().set(9);
        ParkingLot secondParkingLot = ParkingLot.getEmptyParkingLot(10);
        secondParkingLot.getOccupiedAmount().set(5);

        return Arrays.asList(firstParkingLot, secondParkingLot);
    }

    public static List<ParkingLot> getTwoParkingLotsWithTheSecondHavingAHigherVacancyRate() {
        ParkingLot firstParkingLot = ParkingLot.getEmptyParkingLot(10);
        firstParkingLot.getOccupiedAmount().set(9);
        ParkingLot secondParkingLot = ParkingLot.getEmptyParkingLot(10);
        secondParkingLot.getOccupiedAmount().set(5);

        return Arrays.asList(firstParkingLot, secondParkingLot);
    }

    public static List<ParkingLot> getTwoPartiallyOccupiedParkingLotsWithEvenVacancies() {
        return getEmptyParkingLots(2);
    }
}
