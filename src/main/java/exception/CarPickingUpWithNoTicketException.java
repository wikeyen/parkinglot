package exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class CarPickingUpWithNoTicketException extends Exception {
    public CarPickingUpWithNoTicketException(String message) {
        super(message);
    }
}
