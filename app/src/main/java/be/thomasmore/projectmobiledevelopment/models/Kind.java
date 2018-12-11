package be.thomasmore.projectmobiledevelopment.models;

public class Kind {

    private long id;
    private String naam;
    private Long groepID;


    public Kind(){}
    public Kind(long id, String naam, Long groepID){
        this.id = id;
        this.naam = naam;
        this.groepID = groepID;
    }

    public String toString(){
        return naam;
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

    public Long getGroepID() {
        return groepID;
    }
    public void setGroepID(Long groepID) {
        this.groepID = groepID;
    }
}
