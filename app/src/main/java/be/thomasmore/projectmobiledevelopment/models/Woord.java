package be.thomasmore.projectmobiledevelopment.models;

public class Woord {

    private long id;
    private String woord;
    private Woordgroep woordgroep;


    public Woord(){}
    public Woord(long id, String woord, Woordgroep woordgroep){
        this.id = id;
        this.woord = woord;
        this.woordgroep = woordgroep;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getWoord() {
        return woord;
    }
    public void setWoord(String woord) {
        this.woord = woord;
    }

    public Woordgroep getWoordgroep() {
        return woordgroep;
    }
    public void setWoordgroep(Woordgroep woordgroep) {
        this.woordgroep = woordgroep;
    }
}
