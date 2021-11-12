package models;

public class Plan {
    private String actionsAsString;
    public Plan() {
        actionsAsString = "";
    }
    public void addAction(Actions action) {
        actionsAsString += action + ";";
    }

    public String getActionsAsString() {
        return actionsAsString;
    }

}
