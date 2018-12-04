package be.thomasmore.projectmobiledevelopment.models;

public class Lettergreep {

    private long id;
    private int positieInWoord;
    private Woord woord;


    public Lettergreep(){}
    public Lettergreep(long id, int positieInWoord, Woord woord){
        this.id = id;
        this.positieInWoord = positieInWoord;
        this.woord = woord;
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

    public Woord getWoord() {
        return woord;
    }
    public void setWoord(Woord woord) {
        this.woord = woord;
    }
}
