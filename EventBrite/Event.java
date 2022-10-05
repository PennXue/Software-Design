import java.time.LocalDate;
import java.util.Optional;

/*
Representation of a type of Event that can exist
 */
public interface Event{
    public String getName();
    public Optional<Location> getLocation();
    public LocalDate getDate();
    public Optional<Double> getPrice();
    public Optional<Integer> getNumTickets();
    public int accept(Visitor visitor);
    public abstract double profit(double profitPercentage);
}
