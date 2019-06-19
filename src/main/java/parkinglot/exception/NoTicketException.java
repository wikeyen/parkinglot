package parkinglot.exception;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-19
 */
public class NoTicketException extends Exception {
    public NoTicketException(String message) {
        super(message);
    }
}
