import java.time.LocalDate;
import java.util.Optional;

public class Screening extends EventAbstract{
    private Rating aRate;

    public Screening(String pEventName, Optional<Location> pLocation, LocalDate pDate, Optional<Double> pPrice, Optional<Integer> pNumTickets, Rating pRate) {
        super(pEventName, pLocation, pDate, pPrice, pNumTickets);
        aRate = pRate;
    }

    public Screening(String pEventName, LocalDate pDate) {
        super(pEventName, pDate);
    }

    @Override
    public double profit(double profitPercentage) {
        return this.getNumTickets().get() * this.getPrice().get() * profitPercentage;
    }

    public void setRate(Rating pRate){
        aRate = pRate;
    }
    public Rating getRate(){
        return aRate;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visitScreening(this);
    }
    public Rating getaRate(){
        return aRate;
    }
}
