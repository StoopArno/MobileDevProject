package be.thomasmore.projectmobiledevelopment.models;

public class Meting {

    private long id;
    private int metingsNr;
    private int score;
    private Woord woord;
    private KindSessie kindSessie;


    public Meting(){}
    public Meting(long id, int metingsNr, int score, Woord woord, KindSessie kindSessie){
        this.id = id;
        this.metingsNr = metingsNr;
        this.score = score;
        this.woord = woord;
        this.kindSessie = kindSessie;
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

    public Woord getWoord() {
        return woord;
    }
    public void setWoord(Woord woord) {
        this.woord = woord;
    }

    public KindSessie getKindSessie() {
        return kindSessie;
    }
    public void setKindSessie(KindSessie kindSessie) {
        this.kindSessie = kindSessie;
    }
}
