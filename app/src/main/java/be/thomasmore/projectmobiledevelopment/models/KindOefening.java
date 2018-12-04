package be.thomasmore.projectmobiledevelopment.models;

public class KindOefening {

    private long id;
    private int score;
    private int oefeningNr;
    private KindSessie kindSessie;


    public KindOefening(){}
    public KindOefening(long id, int score, int oefeningNr, KindSessie kindSessie){
        this.id = id;
        this.score = score;
        this.oefeningNr = oefeningNr;
        this.kindSessie = kindSessie;
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

    public KindSessie getKindSessie() {
        return kindSessie;
    }
    public void setKindSessie(KindSessie kindSessie) {
        this.kindSessie = kindSessie;
    }
}
