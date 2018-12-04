package be.thomasmore.projectmobiledevelopment.models;

public class ConditieGroep {

    private long id;
    private int conditieNr;
    private Groep groep;
    private Woordgroep woordgroep;


    public ConditieGroep(){}
    public ConditieGroep(long id, int conditieNr, Groep groep, Woordgroep woordgroep){
        this.id = id;
        this.conditieNr = conditieNr;
        this.groep = groep;
        this.woordgroep = woordgroep;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getConditieNr() {
        return conditieNr;
    }
    public void setConditieNr(int conditieNr) {
        this.conditieNr = conditieNr;
    }

    public Groep getGroep() {
        return groep;
    }
    public void setGroep(Groep groep) {
        this.groep = groep;
    }

    public Woordgroep getWoordgroep() {
        return woordgroep;
    }
    public void setWoordgroep(Woordgroep woordgroep) {
        this.woordgroep = woordgroep;
    }
}
