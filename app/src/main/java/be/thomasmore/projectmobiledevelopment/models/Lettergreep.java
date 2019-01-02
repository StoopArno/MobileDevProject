package be.thomasmore.projectmobiledevelopment.models;

public class Lettergreep {

    private long id;
    private int positieInWoord;
    private String lettergreep;
    private Long woordID;


    public Lettergreep(){}
    public Lettergreep(long id, int positieInWoord, String lettergreep, Long woordID){
        this.id = id;
        this.positieInWoord = positieInWoord;
        this.lettergreep = lettergreep;
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

    public String getLettergreep() {
        return lettergreep;
    }

    public void setLettergreep(String lettergreep) {
        this.lettergreep = lettergreep;
    }

    public Long getWoordID() {
        return woordID;
    }
    public void setWoordID(Long woordID) {
        this.woordID = woordID;
    }
}
