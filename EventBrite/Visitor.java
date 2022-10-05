public interface Visitor {
    int visitConcert(Concert concert);
    int visitGala(Gala gala);
    int visitWorkshop(Workshop workshop);
    int visitScreening(Screening screening);
    int visitFestival(Festival festival);
}
