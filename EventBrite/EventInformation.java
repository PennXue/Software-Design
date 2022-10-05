import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EventInformation {
    private HashMap<Informations,Event> aEventsHashMap;
    public EventInformation( HashMap<Informations,Event> pEventsHashMap ){
        aEventsHashMap = pEventsHashMap;
    }
    public Event createConcert(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs){
        Informations informations = new Informations(pLocation, pDate);
        if (!aEventsHashMap.containsKey(informations)) {
            Concert concert = new Concert(pEventName, pLocation, pDate, pPrice, pNumTickets, pVIPs);
            aEventsHashMap.put(informations, concert);
            return aEventsHashMap.get(informations);
        } else {
            return null;
        }
    }
    public Event createGala(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs){
        Informations informations = new Informations(pLocation,pDate);
        if (!aEventsHashMap.containsKey(informations)) {
            Gala gala = new Gala(pEventName, pLocation, pDate, pPrice, pNumTickets, pVIPs);
            aEventsHashMap.put(informations, gala);
            return aEventsHashMap.get(informations);
        } else {
            return null;
        }

    }
    public Event createScreening(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, Rating pRate){
        Informations informations = new Informations(pLocation,pDate);
        if (!aEventsHashMap.containsKey(informations)) {
            Screening screening = new Screening(pEventName, pLocation, pDate, pPrice, pNumTickets, pRate);
            aEventsHashMap.put(informations, screening);
            return aEventsHashMap.get(informations);
        } else {
            return null;
        }

    }
    public Event createWorkshop(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<Workshop> pPrerequisites){
        Informations informations = new Informations(pLocation,pDate);
        if (!aEventsHashMap.containsKey(informations)) {
            Workshop workshop = new Workshop(pEventName, pLocation, pDate, pPrice, pNumTickets, pPrerequisites);
            aEventsHashMap.put(informations, workshop);
            return aEventsHashMap.get(informations);
        } else {
            return null;
        }

    }
    public Event createComingsoon(String pEventName, LocalDate pDate){
        Informations informations = new Informations(Optional.empty(),pDate);
        if (!aEventsHashMap.containsKey(informations)) {
            ComingSoon comingSoon = new ComingSoon(pEventName, pDate);
            aEventsHashMap.put(informations, comingSoon);
            return aEventsHashMap.get(informations);
        } else {
            return null;
        }

    }
}
