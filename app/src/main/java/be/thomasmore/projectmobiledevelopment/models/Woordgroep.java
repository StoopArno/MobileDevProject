package be.thomasmore.projectmobiledevelopment.models;

public class Woordgroep {

    private long id;
    private String woordgroep;


    public Woordgroep(){}
    public Woordgroep(long id, String woordgroep){
        this.id = id;
        this.woordgroep = woordgroep;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getWoordgroep() {
        return woordgroep;
    }

    public void setWoordgroep(String woordgroep) {
        this.woordgroep = woordgroep;
    }
}
