package be.thomasmore.projectmobiledevelopment.models;

public class Kind {

    private long id;
    private String naam;
    private Groep groep;


    public Kind(){}
    public Kind(long id, String naam, Groep groep){
        this.id = id;
        this.naam = naam;
        this.groep = groep;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Groep getGroep() {
        return groep;
    }
    public void setGroep(Groep groep) {
        this.groep = groep;
    }
}
