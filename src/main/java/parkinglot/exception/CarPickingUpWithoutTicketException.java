package parkinglot.exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class CarPickingUpWithoutTicketException extends Exception {
    public CarPickingUpWithoutTicketException(String message) {
        super(message);
    }
}
