import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCode {
        VIP vip1 = new VIP("py");
        VIP vip2 = new VIP("ty");
        List<VIP> vips = new ArrayList<>();
        List<Workshop>  workshops = new ArrayList<Workshop>();
        Event concert = new Concert("concert", Optional.of(Location.BellCentre), LocalDate.of(2022,03,01), Optional.of(200.0), Optional.of(2000), vips);
        Event gala = new Gala("gala", Optional.of(Location.OlympicStadium), LocalDate.of(2022,03,21), Optional.of(300.0), Optional.of(3000), vips);
        Event comingSoon = new ComingSoon("coming soon", LocalDate.of(2022,04,30));
        Event workshop = new Workshop("workshop", Optional.of(Location.ParcJeanDrapeau), LocalDate.of(2022,04,03), Optional.of(400.0), Optional.of(4000), workshops);
        Event screening = new Screening("screening", Optional.of(Location.PlaceDesArts),LocalDate.of(2022,04,10), Optional.of(500.0), Optional.of(5000), Rating.G);


        @Test
        public void testEvent(){
                assertEquals("concert", concert.getName());
                assertEquals(LocalDate.of(2022,04,30),comingSoon.getDate());
                assertEquals(Location.PlaceDesArts, screening.getLocation().get());
                assertTrue(400.0 == workshop.getPrice().get());
                assertTrue(3000 == gala.getNumTickets().get());
        }
        @Test
        public void testEvent2(){
                vips.add(vip1);
                workshops.add((Workshop) workshop);
                assertEquals(vips, ((Concert) concert).getaVIPs());
                assertEquals(Rating.G, ((Screening) screening).getRate());
                assertEquals(workshops, ((Workshop) workshop).getaPrerequisites());
                assertEquals(Optional.empty(), ((ComingSoon) comingSoon).getLocation());
        }
        @Test
        public void testFestival(){
                List<Event> eventList = new ArrayList<>();
                eventList.add(workshop);
                eventList.add(concert);
                eventList.add(gala);
                Festival festival = new Festival("festival",LocalDate.of(2022,04,30), eventList);
                double price = (workshop.getPrice().get() + concert.getPrice().get()+ gala.getPrice().get())*0.2;
                assertTrue(price == festival.getPrice().get());
                assertTrue(Location.Multiple == festival.getLocation().get());
                assertEquals(concert.getNumTickets().get(),festival.getNumTickets().get());
        }
        @Test
        public void testFilter(){
                Informations informations = new Informations(Optional.empty(), LocalDate.of(2022,05,01));
                HashMap<Informations,Event> HashMap = new HashMap<>();
                EventInformation eventInformation = new EventInformation(HashMap);
                EventManagement eventManagement = new EventManagement(eventInformation);
                eventManagement.addWorkshopEvent(workshop.getName(),workshop.getLocation(),workshop.getDate(),workshop.getPrice(),workshop.getNumTickets(),workshops);
                eventManagement.addConcertEvent(concert.getName(),concert.getLocation(),concert.getDate(),concert.getPrice(),concert.getNumTickets(),((Concert)concert).getaVIPs());
                eventManagement.addGalaEvent(gala.getName(),gala.getLocation(),gala.getDate(),gala.getPrice(),gala.getNumTickets(),((Gala)gala).getaVIPs());
                PriceFilter priceFilter = new PriceFilter(0, 350.0);
                eventManagement.addFilter(priceFilter);
                FilterResult filterResult = eventManagement.afterApplyFilters(eventManagement.getaFilterList());
                List<Event> result = new ArrayList<>();
                for(Event event: eventManagement.getHostedEvents()){
                        if(event.getPrice().get() > 0 && event.getPrice().get() < 350.0){
                                result.add(event);
                        }
                }
                assertEquals(result, filterResult.getFilterEvents());
        }
        @Test
        public void testProfit(){
                Informations informations = new Informations(Optional.empty(), LocalDate.of(2022,05,01));
                HashMap<Informations,Event> HashMap = new HashMap<>();
                EventInformation eventInformation = new EventInformation(HashMap);
                EventManagement eventManagement = new EventManagement(eventInformation);
                eventManagement.addScreeningEvent(screening.getName(),screening.getLocation(),screening.getDate(),screening.getPrice(),screening.getNumTickets(),((Screening) screening).getRate());
                eventManagement.addConcertEvent(concert.getName(),concert.getLocation(),concert.getDate(),concert.getPrice(),concert.getNumTickets(),((Concert)concert).getaVIPs());
                eventManagement.addGalaEvent(gala.getName(),gala.getLocation(),gala.getDate(),gala.getPrice(),gala.getNumTickets(),((Gala)gala).getaVIPs());
                eventManagement.addWorkshopEvent(workshop.getName(),workshop.getLocation(),workshop.getDate(),workshop.getPrice(),workshop.getNumTickets(),workshops);
                PriceFilter priceFilter = new PriceFilter(0, 5000.0);
                eventManagement.addFilter(priceFilter);
                FilterResult filterResult = eventManagement.afterApplyFilters(eventManagement.getaFilterList());
                double profit = eventManagement.expectedProfit(filterResult,0.6,0.3, 0.5, 0.1);
                double expected = concert.profit(0.6)+gala.profit(0.3)+workshop.profit(0.5)+screening.profit(0.1);
                assertTrue(expected == profit);
        }
        @Test
        public void testVipCount(){
                Informations informations = new Informations(Optional.empty(), LocalDate.of(2022,05,01));
                HashMap<Informations,Event> HashMap = new HashMap<>();
                EventInformation eventInformation = new EventInformation(HashMap);
                EventManagement eventManagement = new EventManagement(eventInformation);
                eventManagement.addScreeningEvent(screening.getName(),screening.getLocation(),screening.getDate(),screening.getPrice(),screening.getNumTickets(),((Screening) screening).getRate());
                eventManagement.addConcertEvent(concert.getName(),concert.getLocation(),concert.getDate(),concert.getPrice(),concert.getNumTickets(),((Concert)concert).getaVIPs());
                eventManagement.addGalaEvent(gala.getName(),gala.getLocation(),gala.getDate(),gala.getPrice(),gala.getNumTickets(),((Gala)gala).getaVIPs());
                eventManagement.addWorkshopEvent(workshop.getName(),workshop.getLocation(),workshop.getDate(),workshop.getPrice(),workshop.getNumTickets(),workshops);
                PriceFilter priceFilter = new PriceFilter(0, 5000.0);
                eventManagement.addFilter(priceFilter);
                FilterResult filterResult = eventManagement.afterApplyFilters(eventManagement.getaFilterList());
                vips.add(vip1);
                vips.add(vip2);
                int realVipCount = ((Concert) concert).getaVIPs().size()+((Gala) gala).getaVIPs().size();
                int codeVipCount = eventManagement.countVIP(filterResult);
                assertEquals(realVipCount, codeVipCount);
        }
}
