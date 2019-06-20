package util;

import parkinglot.model.ParkingLot;
import parkinglot.model.Ticket;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-20
 */
public class TicketUtil {
    public static Ticket getInvalidTicket() {
        return Ticket.getNewTicket(ParkingLot.getEmptyParkingLot(1));
    }
}
