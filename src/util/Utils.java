package util;

import com.google.gson.Gson;
import models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    private static final SecureRandom random = new SecureRandom();
    public static Gson gson = new Gson();
    public static List<Status> CASE_BASE = new ArrayList<>();

    /**
     * Method that checks if an action is executable at runtime
     *
     * @param status the player status
     * @param action the action to check
     * @return true if the action can be executed, otherwise false
     */
    public static Boolean checkIfActionExecutable(Status status, Actions action) {
        boolean isActionExecutable = true;

        if (action.equals(Actions.UseCover)) {
            return true;
        }

        if (status.getTimeToPickHealth() < 0 && action.equals(Actions.PickHealth)) {
            isActionExecutable = false;
        }
        if (status.getTimeToPickWeapon() < 0 && action.equals(Actions.PickWeapon)) {
            isActionExecutable = false;

        }
        if (status.getTimeToPickAmmunition() < 0 && action.equals(Actions.PickAmmunition)) {
            isActionExecutable = false;
        }
        if (status.getTimeToKillEnemy() < 0 && action.equals(Actions.MoveToShoot)) {
            isActionExecutable = false;
        }


        return isActionExecutable;

    }

    /**
     * Method that returns a random action that can be executed by the case-based Agent
     *
     * @param status the agent's status
     * @return Random Action
     */
    public static Actions getRandomAction(Status status) {

        Actions action = getRandomAction();

        if (status.getTimeToPickAmmunition() > 0 && action.equals(Actions.PickAmmunition)) {

            return action;
        }
        if (status.getTimeToPickWeapon() > 0 && action.equals(Actions.PickWeapon)) {

            return action;
        }
        if (status.getTimeToPickHealth() > 0 && action.equals(Actions.PickHealth)) {

            return action;
        }
        if (status.getTimeToKillEnemy() >= 0 && action.equals(Actions.MoveToShoot)) {

            return action;
        }
        action = Actions.UseCover;

        return action;
    }

    /**
     * Method that return the number of actions existing in a file
     *
     * @return the number of the actions appearances in a file
     */
    public static String getActionsAppearance() {
        int health = 0;
        int weapon = 0;
        int ammunition = 0;
        int cover = 0;
        int shoot = 0;
        for (Status status : CASE_BASE) {
            if (status.getAction() == Actions.PickHealth) {
                health += 1;
            }
            if (status.getAction() == Actions.PickWeapon) {
                weapon += 1;
            }
            if (status.getAction() == Actions.PickAmmunition) {
                ammunition += 1;
            }
            if (status.getAction() == Actions.MoveToShoot) {
                shoot += 1;
            }
            if (status.getAction() == Actions.UseCover) {
                cover += 1;
            }
        }

        return "PickHealth: " + health + ", " +
                "PickWeapon: " + weapon + ", " +
                "PickAmmunition: " + ammunition + ", " +
                "MoveToShoot: " + shoot + ", " +
                "UseCover: " + cover;
    }

    /**
     * Method that read load the cases of the case base
     */
    public static void readTheCaseBase() {
        try {
            File myObj = new File("case_base.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                CASE_BASE.add(gson.fromJson(data, Status.class));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Method that writes data to a file
     *
     * @param text the player status representing as gson string
     */
    public static void writeToFile(String text) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(System.getProperty("user.dir"), "none.txt"), true);
            fw.write(text);
            fw.write("\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method that return a random action from the Enum-Class Actions
     *
     * @return
     */
    public static Actions getRandomAction() {

        int randomIndex = random.nextInt(Actions.class.getEnumConstants().length);

        return Actions.class.getEnumConstants()[randomIndex];
    }

}
