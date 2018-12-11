package be.thomasmore.projectmobiledevelopment.models;

public class ConditieGroep {

    private long id;
    private int conditieNr;
    private Long groepID;
    private Long woordgroepID;


    public ConditieGroep(){}
    public ConditieGroep(long id, int conditieNr, Long groepID, Long woordgroepID){
        this.id = id;
        this.conditieNr = conditieNr;
        this.groepID = groepID;
        this.woordgroepID = woordgroepID;
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

    public Long getGroepID() {
        return groepID;
    }
    public void setGroepID(Long groepID) {
        this.groepID = groepID;
    }

    public Long getWoordgroepID() {
        return woordgroepID;
    }
    public void setWoordgroepID(Long woordgroepID) {
        this.woordgroepID = woordgroepID;
    }
}
