package be.thomasmore.projectmobiledevelopment.models;

public class KindSessie {

    private long id;
    private Kind kind;
    private Woordgroep woordgroep;


    public KindSessie(){}
    public KindSessie(long id, Kind kind, Woordgroep woordgroep){
        this.id = id;
        this.kind = kind;
        this.woordgroep = woordgroep;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Kind getKind() {
        return kind;
    }
    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Woordgroep getWoordgroep() {
        return woordgroep;
    }
    public void setWoordgroep(Woordgroep woordgroep) {
        this.woordgroep = woordgroep;
    }
}
