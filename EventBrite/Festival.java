import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Festival extends EventAbstract{
    private final List<Event> aEvents;
    public Festival(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<Event> aEvents) {
        super(pEventName, pLocation, pDate, pPrice, pNumTickets);
        this.aEvents = aEvents;
    }

    public Festival(String pEventName, LocalDate pDate, List<Event> aEvents) {
        super(pEventName, pDate);
        this.aEvents = aEvents;
    }

    @Override
    public double profit(double profitPercentage) {
        double result = 0;
        for(Event event: this.getaEvents()){
            result += event.getNumTickets().get() * event.getPrice().get() * profitPercentage;
        }
        return result;
    }

    public List<Event> getaEvents(){
        return this.aEvents;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visitFestival(this);
    }
    @Override
    public Optional<Location> getLocation(){
        Location location =this.aEvents.get(0).getLocation().get();
        for (Event event: this.getaEvents()){
            if(event.getLocation().get() != location){
                location = Location.Multiple;
            }
        }
        return Optional.of(location);
    }
    public LocalDate getDate(){
        LocalDate date = LocalDate.now();
        for (Event event: this.getaEvents()){
            if(event.getDate().compareTo(date) < 1){
                date = event.getDate();
            }
        }
        return date;
    }
    public Optional<Double> getPrice(){
        Double price = 0.0;
        for(Event event: this.getaEvents()){
            price = event.getPrice().get()*0.2 + price;
        }
        return Optional.of(price);
    }
    public Optional<Integer> getNumTickets(){
        int numOfTicket = this.getaEvents().get(0).getNumTickets().get();
        for (Event event: this.getaEvents()){
            if(event.getNumTickets().get() < numOfTicket){
                numOfTicket = event.getNumTickets().get();
            }
        }
        return Optional.of(numOfTicket);
    }
}
