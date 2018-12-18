package be.thomasmore.projectmobiledevelopment.models;

public class KindOefening {

    private long id;
    private int score;
    private int oefeningNr;
    private Long kindSessieID;
    private Long woordID;


    public KindOefening(){}
    public KindOefening(long id, int score, int oefeningNr, Long kindSessieID, Long woordID){
        this.id = id;
        this.score = score;
        this.oefeningNr = oefeningNr;
        this.kindSessieID = kindSessieID;
        this.woordID = woordID;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getOefeningNr() {
        return oefeningNr;
    }
    public void setOefeningNr(int oefeningNr) {
        this.oefeningNr = oefeningNr;
    }

    public Long getKindSessieID() {
        return kindSessieID;
    }
    public void setKindSessieID(Long kindSessieID) {
        this.kindSessieID = kindSessieID;
    }

    public Long getWoordID() {
        return woordID;
    }

    public void setWoordID(Long woordID) {
        this.woordID = woordID;
    }
}
