public class vipCount implements Visitor{
    private int aVipCount;
    public int getaVipCount(){
        return aVipCount;
    }

    @Override
    public int visitConcert(Concert concert) {
        aVipCount = concert.getaVIPs().size();
        return aVipCount;
    }

    @Override
    public int visitGala(Gala gala) {
        aVipCount = gala.getaVIPs().size();
        return aVipCount;
    }

    @Override
    public int visitWorkshop(Workshop workshop) {
        return 0;

    }

    @Override
    public int visitScreening(Screening screening) {
        return 0;
    }

    @Override
    public int visitFestival(Festival festival) {
        int result = 0;
        for(Event event: festival.getaEvents()){
            result = result + event.accept(this);
        }
        return result;
    }
}
