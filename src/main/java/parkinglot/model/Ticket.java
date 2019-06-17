package parkinglot.model;

/**
 * @author chongyang18@gmail.com
 * created on 2019-06-16
 */
public class Ticket {
    private boolean isValid;
    private ParkingLot parkingLot;

    private Ticket(ParkingLot parkingLot, boolean isValid) {
        this.parkingLot = parkingLot;
        this.isValid = isValid;
    }

    public static Ticket getNewTicket(ParkingLot parkingLot) {
        return new Ticket(parkingLot, true);
    }

    public static void destroyTicket(Ticket ticket) {
        ticket.setValid(false);
    }

    public boolean isValid() {
        return isValid;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return isValid == ticket.isValid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void setValid(boolean valid) {
        isValid = valid;
    }
}
