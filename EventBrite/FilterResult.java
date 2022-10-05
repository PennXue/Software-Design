import java.util.List;

public class FilterResult {
    private List<Event> aFilterEvents;

    public void setFilterEvents(List<Event> pFilterEvents){
        aFilterEvents = pFilterEvents;
    }


    public List<Event> getFilterEvents(){
        return aFilterEvents;
    }
}
