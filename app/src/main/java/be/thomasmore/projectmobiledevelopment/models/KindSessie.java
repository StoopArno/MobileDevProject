package be.thomasmore.projectmobiledevelopment.models;

public class KindSessie {

    private long id;
    private Long kindID;
    private Long woordgroepID;


    public KindSessie(){}
    public KindSessie(long id, Long kindID, Long woordgroepID){
        this.id = id;
        this.kindID = kindID;
        this.woordgroepID = woordgroepID;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getKindID() {
        return kindID;
    }
    public void setKindID(Long kindID) {
        this.kindID = kindID;
    }

    public Long getWoordgroepID() {
        return woordgroepID;
    }
    public void setWoordgroepID(Long woordgroepID) {
        this.woordgroepID = woordgroepID;
    }
}
