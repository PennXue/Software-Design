import java.time.LocalDate;
import java.util.Optional;

public class Informations {
    private Optional<Location> aLocations;
    private LocalDate aDate;
    public Informations(Optional<Location> pLocations, LocalDate pDate){
        aDate = pDate;
        aLocations = pLocations;
    }
    public Optional<Location> getLocations(){
        return aLocations;
    }
    public LocalDate getDate(){
        return aDate;
    }
}
