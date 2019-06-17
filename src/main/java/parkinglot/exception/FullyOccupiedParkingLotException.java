package parkinglot.exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class FullyOccupiedParkingLotException extends Exception {
    public FullyOccupiedParkingLotException(String message) {
        super(message);
    }
}
