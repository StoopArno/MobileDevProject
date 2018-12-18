package be.thomasmore.projectmobiledevelopment.models;

public class Groep {

    private long id;


    public Groep(){}
    public Groep(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
