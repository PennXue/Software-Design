import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Gala extends EventAbstract {
    private final List<VIP> aVIPs;

    public Gala(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs) {
        super(pEventName, pLocation, pDate, pPrice, pNumTickets);
        aVIPs = pVIPs;
    }


    public void addVIP(VIP pVIP){
        aVIPs.add(pVIP);
    }
    public List<VIP> getaVIPs(){
        return this.aVIPs;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visitGala(this);
    }

    @Override
    public double profit(double profitPercentage) {
        return this.getNumTickets().get() * this.getPrice().get() * profitPercentage;
    }
}
