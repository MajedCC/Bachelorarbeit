package models;

public class Request {

    private Situation situation;

    public Request() {
        this(new Situation());
    }

    public Request(Situation situation) {
        this.situation = situation;
    }


    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Situation getSituation() {
        return situation;
    }

    @Override
    public String toString() {
        return "Request [situation=" + situation.toString() + "]";
    }
}
