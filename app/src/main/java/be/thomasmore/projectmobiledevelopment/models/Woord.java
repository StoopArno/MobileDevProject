package be.thomasmore.projectmobiledevelopment.models;

public class Woord {

    private long id;
    private String woord;
    private Long woordgroepID;


    public Woord(){}
    public Woord(long id, String woord, Long woordgroepID){
        this.id = id;
        this.woord = woord;
        this.woordgroepID = woordgroepID;
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

    public Long getWoordgroepID() {
        return woordgroepID;
    }
    public void setWoordgroepID(Long woordgroepID) {
        this.woordgroepID = woordgroepID;
    }
}
