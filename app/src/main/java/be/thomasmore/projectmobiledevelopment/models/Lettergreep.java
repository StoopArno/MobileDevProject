package be.thomasmore.projectmobiledevelopment.models;

public class Lettergreep {

    private long id;
    private int positieInWoord;
    private Long woordID;


    public Lettergreep(){}
    public Lettergreep(long id, int positieInWoord, Long woordID){
        this.id = id;
        this.positieInWoord = positieInWoord;
        this.woordID = woordID;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getPositieInWoord() {
        return positieInWoord;
    }
    public void setPositieInWoord(int positieInWoord) {
        this.positieInWoord = positieInWoord;
    }

    public Long getWoordID() {
        return woordID;
    }
    public void setWoordID(Long woordID) {
        this.woordID = woordID;
    }
}
