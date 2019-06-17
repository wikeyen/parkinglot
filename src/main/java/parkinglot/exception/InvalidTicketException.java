package parkinglot.exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class InvalidTicketException extends Exception {
    public InvalidTicketException(String message) {
        super(message);
    }
}
