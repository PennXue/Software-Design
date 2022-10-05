import java.util.ArrayList;
import java.util.List;

public class LocationFilter implements Filters {
    private Location aLocation;
    @Override
    public List<Event> filter(List<Event> eventList) {
        List<Event> result = new ArrayList<>();
        for(Event event: eventList){
            if(event.getLocation().get() == aLocation){
                result.add(event);
            }
        }
        return result;
    }
}
