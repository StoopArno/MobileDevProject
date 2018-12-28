package be.thomasmore.projectmobiledevelopment.models;

public class AssociatieWoord {

    private long id;
    private Long woordID;
    private int afbeeldingNr;
    private String woord;
    private int juist;

    public AssociatieWoord(){}
    public AssociatieWoord(long id, Long woordID, int afbeeldingNr, String woord, int juist){
        this.id = id;
        this.woordID = woordID;
        this.afbeeldingNr = afbeeldingNr;
        this.woord = woord;
        this.juist = juist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getWoordID() {
        return woordID;
    }

    public void setWoordID(Long woordID) {
        this.woordID = woordID;
    }

    public int getAfbeeldingNr() {
        return afbeeldingNr;
    }

    public void setAfbeeldingNr(int afbeeldingNr) {
        this.afbeeldingNr = afbeeldingNr;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }

    public int getJuist() {
        return juist;
    }

    public void setJuist(int juist) {
        this.juist = juist;
    }
}
