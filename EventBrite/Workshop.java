import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Workshop extends EventAbstract{
    private final List<Workshop> aPrerequisites;

    public Workshop(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, List<Workshop> pPrerequisites) {
        super(pEventName, pLocation, pDate, pPrice, pNumTickets);
        aPrerequisites = pPrerequisites;
    }
    public void addPrerequisites(Workshop pWorkshop){
        aPrerequisites.add(pWorkshop);
    }
    public List<Workshop> getaPrerequisites(){
        return aPrerequisites;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visitWorkshop(this);
    }

    @Override
    public double profit(double profitPercentage) {
        return this.getNumTickets().get() * this.getPrice().get() * profitPercentage;
    }

}
