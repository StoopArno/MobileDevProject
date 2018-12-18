package be.thomasmore.projectmobiledevelopment.models;

public class KindSessie {

    private long id;
    private Long kindID;


    public KindSessie(){}
    public KindSessie(long id, Long kindID){
        this.id = id;
        this.kindID = kindID;
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
}
