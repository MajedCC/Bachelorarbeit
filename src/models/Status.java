package models;

public class Status {

    private double timeToDie;
    private double timeToKillEnemy;
    private double timeToPickHealth;
    private double timeToPickWeapon;
    private double timeToPickAmmunition;
    private double timeToRunOutOfAmmunition;
    private Actions action = null;
    private double score;

    public Status() {
        this(-1.0, -1.0, -1.0, -1.0, -1.0, -1.0);
    }


    public Status(double timeToDie, double timeToKillEnemy, double timeToPickHealth, double timeToPickWeapon, double timeToPickAmmunition, double timeToRunOutOfAmmunition) {
        this.timeToDie = timeToDie;
        this.timeToKillEnemy = timeToKillEnemy;
        this.timeToPickHealth = timeToPickHealth;
        this.timeToPickWeapon = timeToPickWeapon;
        this.timeToPickAmmunition = timeToPickAmmunition;
        this.timeToRunOutOfAmmunition = timeToRunOutOfAmmunition;
    }

    public double[] getVector() {

        return new double[]{this.timeToDie, this.timeToKillEnemy, this.timeToPickHealth,
                this.timeToPickWeapon, this.timeToPickAmmunition, this.timeToRunOutOfAmmunition};
    }


    public double[] getVectorWithDerivedAttributes() {
        return new double[]{this.timeToDie - this.timeToKillEnemy, this.timeToPickHealth, this.timeToPickWeapon, this.timeToPickAmmunition, this.timeToRunOutOfAmmunition - this.timeToKillEnemy};
    }

    public String getDescribedVector() {
        return "{ " + " TD = " + this.timeToDie + ", TK = " + this.timeToKillEnemy + ", TH = " + this.timeToPickHealth + ", TW = " + this.timeToPickWeapon + ", TA = " + this.timeToPickAmmunition + ", TRA = " + this.timeToRunOutOfAmmunition + " }";
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public double getTimeToPickHealth() {
        return timeToPickHealth;
    }

    public double getTimeToPickWeapon() {
        return timeToPickWeapon;
    }

    public double getTimeToDie() {
        return timeToDie;
    }

    public void setTimeToDie(double timeToDie) {
        this.timeToDie = timeToDie;
    }

    public double getTimeToKillEnemy() {
        return timeToKillEnemy;
    }

    public double getTimeToPickAmmunition() {
        return timeToPickAmmunition;
    }

    public double getTimeToRunOutOfAmmunition() {
        return timeToRunOutOfAmmunition;
    }

    public void setTimeToRunOutOfAmmunition(double timeToRunOutOfAmmunition) {
        this.timeToRunOutOfAmmunition = timeToRunOutOfAmmunition;
    }

    public double evaluationFunction() {
        double timeToKillEnemyGrounded = timeToKillEnemy;

        if (timeToKillEnemy < 0) {
            timeToKillEnemyGrounded = 0;
        }

        return timeToDie - timeToKillEnemyGrounded + Math.signum(timeToRunOutOfAmmunition - timeToKillEnemyGrounded);

    }

    @Override
    public String toString() {

        return "Status [timeToDie=" + timeToDie +
                ", timeToKillEnemy=" + timeToKillEnemy +
                ", timeToPickHealth=" + timeToPickHealth +
                ", timeToPickWeapon=" + timeToPickWeapon +
                ", timeToPickAmmunition=" + timeToPickAmmunition +
                ", timeToRunOutOfAmmunition=" + timeToRunOutOfAmmunition +
                ", action=" + action +
                ", score=" + score +
                "]";
    }


}
