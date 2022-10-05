import java.time.LocalDate;

public class ComingSoon extends EventAbstract{
    public ComingSoon(String pEventName, LocalDate pDate) {
        super(pEventName, pDate);
    }

    @Override
    public double profit(double profitPercentage) {
        return 0;
    }

    @Override
    public int accept(Visitor visitor) {
        return 0;
    }
}
