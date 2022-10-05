import java.time.LocalDate;
import java.util.Optional;

public abstract class EventAbstract implements Event{
    private final String aEventName;
    private final Optional<Location> aLocation;
    private final LocalDate aDate;
    private final Optional<Double> aPrice;
    private final Optional<Integer> aNumTickets;
    public EventAbstract(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets) {
        this.aEventName = pEventName;
        this.aLocation = pLocation;
        this.aDate = pDate;
        this.aPrice = pPrice;
        this.aNumTickets = pNumTickets;
    }
    public EventAbstract(String pEventName, LocalDate pDate){
        this.aEventName = pEventName;
        this.aLocation = Optional.empty();
        this.aDate = pDate;
        this.aPrice = Optional.empty();
        this.aNumTickets = Optional.empty();
    }
    public String getName(){
        return this.aEventName;
    }
    public Optional<Location> getLocation(){
        return this.aLocation;
    }
    public LocalDate getDate(){
        return this.aDate;
    }
    public Optional<Double> getPrice(){return this.aPrice;}
    public Optional<Integer> getNumTickets(){
        return this.aNumTickets;
    }

}
