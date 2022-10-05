import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private final List<Event> aHostedEvents = new ArrayList<Event>();
    private final List<Filters> aFilterList = new ArrayList<Filters>();
    private final EventInformation aEventInformation;

    public EventManagement(EventInformation pEventInformation) {
        aEventInformation = pEventInformation;
    }

    /*
    Method to host a new concert event
     */
    public void addConcertEvent(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs){
        Concert concert = new Concert(pEventName,pLocation,pDate,pPrice,pNumTickets, pVIPs);
        if(aEventInformation.createConcert(pEventName,pLocation,pDate,pPrice,pNumTickets, pVIPs) != null){
            aHostedEvents.add(concert);
        }
    }

    /*
    Method to host a new Gala event
     */
    public void addGalaEvent(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs){
        Gala gala = new Gala(pEventName, pLocation, pDate, pPrice, pNumTickets, pVIPs);
        if(aEventInformation.createGala(pEventName, pLocation, pDate, pPrice, pNumTickets, pVIPs) != null){
            aHostedEvents.add(gala);
        }
    }

    /*
    Method to host a new Screening event
     */
    public void addScreeningEvent(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, Rating pRate){
        Screening screening = new Screening(pEventName, pLocation, pDate, pPrice, pNumTickets, pRate);
        if(aEventInformation.createScreening(pEventName, pLocation, pDate, pPrice, pNumTickets, pRate) != null){
            aHostedEvents.add(screening);
        }
    }

    /*
    Method to host a new Workshop event
     */
    public void addWorkshopEvent(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<Workshop> pPrerequisites){
        Workshop workshop = new Workshop(pEventName, pLocation, pDate, pPrice, pNumTickets, pPrerequisites);
        if(aEventInformation.createWorkshop(pEventName, pLocation, pDate, pPrice, pNumTickets, pPrerequisites) != null) {
            aHostedEvents.add(workshop);
        }
    }
    public void addComingSoonEvent(String pEventName, LocalDate pDate){
        ComingSoon comingSoon = new ComingSoon(pEventName, pDate);
        if(aEventInformation.createComingsoon(pEventName, pDate) != null){
            aHostedEvents.add(comingSoon);
        }
    }

    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
    public void addFilter(Filters pFilter){
        aFilterList.add(pFilter);
    }
    public FilterResult afterApplyFilters(List<Filters> aFilterList){
        FilterResult result = new FilterResult();
        for(Filters eachFilter: aFilterList){
            List<Event> afterOneFilter = eachFilter.filter(aHostedEvents);
            result.setFilterEvents(afterOneFilter);
        }
        return result;
    }
    public List<Filters> getaFilterList(){
        return this.aFilterList;
    }
    public double expectedProfit(FilterResult filterResult, double concertProfit, double galaProfit, double workshopProfit, double screeningProfit){
        assert filterResult != null && concertProfit != 0 && galaProfit != 0 && workshopProfit != 0 && screeningProfit != 0;
        List<Event> events = filterResult.getFilterEvents();
        double profits = 0;
        for(Event event: events){
            if(event instanceof Concert){
                profits = profits + event.profit(concertProfit);
            } else if (event instanceof Gala){
                profits = profits + event.profit(galaProfit);
            } else if (event instanceof Workshop){
                profits = profits + event.profit(workshopProfit);
            } else if (event instanceof Screening){
                profits = profits + event.profit(screeningProfit);
            } else if (event instanceof Festival){
                profits = profits + event.profit(0.2);
            }
        }
        return profits;
    }
    public EventInformation getEventInformation(){
        return aEventInformation;
    }
    public int countVIP(FilterResult filterResult){
        int vips = 0;
        List<Event> events = filterResult.getFilterEvents();
        for(Event event: events){
            Visitor visitor = new vipCount();
            vips = vips + event.accept(visitor);
        }
        return vips;
    }
}
