package be.thomasmore.projectmobiledevelopment.models;

public class Meting {

    private long id;
    private int metingsNr;
    private int score;
    private Long woordID;
    private Long kindSessieID;


    public Meting(){}
    public Meting(long id, int metingsNr, int score, Long woordID, Long kindSessieID){
        this.id = id;
        this.metingsNr = metingsNr;
        this.score = score;
        this.woordID = woordID;
        this.kindSessieID = kindSessieID;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getMetingsNr() {
        return metingsNr;
    }
    public void setMetingsNr(int metingsNr) {
        this.metingsNr = metingsNr;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Long getWoordID() {
        return woordID;
    }
    public void setWoordID(Long woordID) {
        this.woordID = woordID;
    }

    public Long getKindSessieID() {
        return kindSessieID;
    }
    public void setKindSessieID(Long kindSessieID) {
        this.kindSessieID = kindSessieID;
    }
}
