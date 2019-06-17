package parkinglot.model;

/**
 * @author chongyang18@gmail.com
 * created on 2019-06-16
 */
public class Ticket {
    private boolean isValid;

    private Ticket(boolean isValid) {
        this.isValid = isValid;
    }

    public static Ticket getNewTicket() {
        return new Ticket(true);
    }

    public static void destroyTicket(Ticket ticket) {
        ticket.setValid(false);
    }

    public boolean isValid() {
        return isValid;
    }

    private void setValid(boolean valid) {
        isValid = valid;
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
}
