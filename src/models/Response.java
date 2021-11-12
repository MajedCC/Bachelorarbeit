package models;

public class Response {
    private Situation situation;
    private Plan plan;

    public Response() {
        this(new Situation(), new Plan());
    }

    public Response(Situation situation, Plan plan) {
        this.situation = situation;
        this.plan = plan;
    }

    public Situation getSituation() {
        return situation;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Response [situation=" + situation.toString() + "]";
    }
}
