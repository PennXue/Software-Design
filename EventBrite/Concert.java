import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Concert extends EventAbstract {
    private String aArtist;
    private final List<VIP> aVIPs;

    public Concert(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<VIP> pVIPs) {
        super(pEventName, pLocation, pDate, pPrice, pNumTickets);
        aVIPs = pVIPs;
    }

    public void setArtist(String pArtist){
        this.aArtist = pArtist;
    }
    public String getaArtist(){
        return this.aArtist;
    }
    public void addVIP(VIP pVIP){
        this.aVIPs.add(pVIP);
    }
    public List<VIP> getaVIPs(){
        return this.aVIPs;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visitConcert(this);
    }

    @Override
    public double profit(double profitPercentage) {
        return this.getNumTickets().get() * this.getPrice().get() * profitPercentage;
    }
}
