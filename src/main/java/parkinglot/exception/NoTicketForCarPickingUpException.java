package parkinglot.exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-19
 */
public class NoTicketForCarPickingUpException extends Exception {
    public NoTicketForCarPickingUpException(String message) {
        super(message);
    }
}
