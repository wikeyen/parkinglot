package domain;

/**
 * @author yancy3@lenovo.com
 * created on 2019-06-16
 */
public class Tickets {
    private boolean isValid;

    private Tickets(boolean isValid) {
        this.isValid = isValid;
    }

    public static Tickets getTicket() {
        return new Tickets(true);
    }

    public static void destroyTicket(Tickets ticket) {
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
        Tickets ticket = (Tickets) o;
        return isValid == ticket.isValid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
