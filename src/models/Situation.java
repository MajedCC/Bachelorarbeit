package models;

public class Situation {

    private String player;

    private Status playerStatus;

    public Situation() {

    }

    public Situation(String player, Status playerStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
    }

    public String getPlayer() {
        return player;
    }

    public Status getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setPlayerStatus(Status playerStatus) {
        this.playerStatus = playerStatus;
    }
//
//    @Override
//    public String toString() {
//        return "Situation [player=" + player + ", playerStatus=" + playerStatus.toString() + "]";
//    }
}
