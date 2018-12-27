package be.thomasmore.projectmobiledevelopment.models;

public class WoordAfbeelding {

    private long id;
    private Long woordID;
    private int afbeeldingNr;
    private int juist;

    public WoordAfbeelding(){}
    public WoordAfbeelding(long id, Long woordID, int afbeeldingNr, int juist){
        this.id = id;
        this.woordID = woordID;
        this.afbeeldingNr = afbeeldingNr;
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

    public int getJuist() {
        return juist;
    }

    public void setJuist(int juist) {
        this.juist = juist;
    }
}
