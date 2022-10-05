import java.util.ArrayList;
import java.util.List;

public class PriceFilter implements Filters {
    private double lowest;
    private double highest;

    public PriceFilter(double lowest, double highest){
        this.lowest = lowest;
        this.highest = highest;
    }

    @Override
    public List<Event> filter(List<Event> eventList) {
        List<Event> result = new ArrayList<>();
        for(Event event: eventList){
            if(event.getPrice().isPresent()){
                if(event.getPrice().get() > lowest && event.getPrice().get() < highest){
                    result.add(event);
                }
            }
        }
        return result;
    }
}
