package similarity;

import com.google.gson.Gson;
import models.Actions;
import models.Status;
import util.Utils;

import java.util.*;

import static util.Utils.CASE_BASE;

public class SimilarityMethods {

    public static HashMap<Double, Actions> similarityMap = new HashMap<>();
    public static HashMap<Double, Actions> tempMap = new HashMap<>();
    public static int numberOfExecutedActions = 0;
    public static int numberOfRejectedActions = 0;
    public static int bestIsFirst = 0;
    public static int bestIsNotFirst = 0;
    public static List<Double> simValues;
    public static double minDistance;
    public static double maxSimilarity;
    public static Actions action;
    public static Gson gson = new Gson();
    public static int numberOfActionPickWeapon = 0;
    public static int numberOfActionPickHealth = 0;

    /**
     * Method that returns the action associated with the most similar case to the player situation using cosine similarity
     * @param playerStatus The status of the player
     * @return The action associated with the most similar case to the player situation
     */
    public static Actions getActionUsingCosineSimilarity_1(Status playerStatus) {
        boolean isActionExecutable = false;
        int requestsCounter = 0;

        while(!isActionExecutable) {

            if (requestsCounter == 0) {
                for (Status status : CASE_BASE) {
                    similarityMap.put(Equations.cosineSimilarity(status.getVectorWithDerivedAttributes(), playerStatus.getVectorWithDerivedAttributes()), status.getAction());
                }
            }

            simValues = new ArrayList<>(similarityMap.keySet());
            maxSimilarity = Collections.max(simValues);
            action = similarityMap.get(maxSimilarity);

            isActionExecutable = Utils.checkIfActionExecutable(playerStatus, action);

            if (!isActionExecutable) {
                similarityMap.remove(maxSimilarity);
                requestsCounter++;

            } else {
                similarityMap.clear();
            }
        }

        if (requestsCounter != 0) {
            numberOfRejectedActions += 1;
        }

        numberOfExecutedActions += 1;

        System.out.println("------------------------------------------------");
        System.out.println("Number of rejected Actions = " + numberOfRejectedActions);
        System.out.println("Number of executed Actions = " + numberOfExecutedActions);
        System.out.println("ACTION => " + action);
        System.out.println("------------------------------------------------");

        return action;

    }
    /**
     * Method that returns the action obtained by using 3-Nearest Neighbors with cosine similarity
     * @param playerStatus The status of the player
     * @return The action obtained by using 3-Nearest Neighbors with cosine similarity
     */
    public static Actions getActionUsingCosineSimilarity_3(Status playerStatus) {
        boolean isActionExecutable = false;
        int requestsCounter = 0;
        Actions action1 = null;
        Actions action2 = null;
        Actions action3 = null;

        while(!isActionExecutable) {

            if (requestsCounter == 0) {
                for (Status status : CASE_BASE) {
                    similarityMap.put(Equations.cosineSimilarity(status.getVector(), playerStatus.getVector()), status.getAction());
                    tempMap.put(Equations.cosineSimilarity(status.getVector(), playerStatus.getVector()), status.getAction());
                }
            } else {
                tempMap.clear();

                for (Map.Entry<Double, Actions> entry : similarityMap.entrySet()) {
                    tempMap.put(entry.getKey(), entry.getValue());
                }
            }

            simValues = new ArrayList<>(similarityMap.keySet());
            maxSimilarity = Collections.max(simValues);
            action1 = similarityMap.get(maxSimilarity);
            similarityMap.remove(maxSimilarity);
            tempMap.remove(maxSimilarity);

            simValues = new ArrayList<>(tempMap.keySet());
            action2 = tempMap.get( Collections.max(simValues));
            tempMap.remove(maxSimilarity);

            simValues = new ArrayList<>(tempMap.keySet());
            action3 = tempMap.get( Collections.max(simValues));


            if (action2.equals(action3) && !action2.equals(action1)) {
                bestIsNotFirst += 1;
                action = action2;
            } else {
                bestIsFirst += 1;
                action = action1;
            }

            isActionExecutable = Utils.checkIfActionExecutable(playerStatus, action);


            if (!isActionExecutable) {
                requestsCounter++;

            } else {
                similarityMap.clear();
            }
        }

        if (requestsCounter != 0) {
            numberOfRejectedActions += 1;
        }

        numberOfExecutedActions += 1;

        System.out.println("------------------------------------------------");
        System.out.println("Number of rejected Actions = " + numberOfRejectedActions);
        System.out.println("Number of executed Actions = " + numberOfExecutedActions);
        System.out.println("bestIsNotFirst: " + bestIsNotFirst);
        System.out.println("bestIsFirst: " + bestIsFirst);
        System.out.println( "action1 = " + action1);
        System.out.println( "action2 = " + action2);
        System.out.println( "action3 = " + action3);
        System.out.println("ACTION => " + action);
        System.out.println("------------------------------------------------");

        if (action.equals(Actions.PickWeapon)){
            numberOfActionPickWeapon += 1;
            System.out.println("####################################");
            System.out.println("numberOfActionPickWeapon: " + numberOfActionPickWeapon);
            System.out.println("####################################");

        }
        if (action.equals(Actions.PickHealth)){
            numberOfActionPickHealth += 1;
            System.out.println("####################################");
            System.out.println("numberOfActionPickHealth: " + numberOfActionPickHealth);
            System.out.println("####################################");

        }

        return action;

    }

    /**
     * Method that returns the action associated with the most similar case to the player situation using Euclidean Distance
     * @param playerStatus The status of the player
     * @return The action associated with the most similar case to the player situation
     */
    public static Actions getActionUsingEuclideanDistance_1(Status playerStatus) {
        boolean isActionExecutable = false;
        int requestsCounter = 0;

        while(!isActionExecutable) {

            if (requestsCounter == 0) {
                for (Status status : CASE_BASE) {
                    similarityMap.put(Equations.euclideanDistance(status.getVectorWithDerivedAttributes(), playerStatus.getVectorWithDerivedAttributes()), status.getAction());
                }
            }

            simValues = new ArrayList<>(similarityMap.keySet());
            minDistance = Collections.min(simValues);
            action = similarityMap.get(minDistance);

            isActionExecutable = Utils.checkIfActionExecutable(playerStatus, action);


            if (!isActionExecutable) {
                similarityMap.remove(minDistance);
                requestsCounter++;

            } else {
                similarityMap.clear();
            }
        }

        if (requestsCounter != 0) {
            numberOfRejectedActions += 1;
        }

        numberOfExecutedActions += 1;

        System.out.println("------------------------------------------------");
        System.out.println("Number of rejected Actions = " + numberOfRejectedActions);
        System.out.println("Number of executed Actions = " + numberOfExecutedActions);
        System.out.println("ACTION => " + action);
        System.out.println("------------------------------------------------");

        //playerStatus.setAction(action);
        //write(gson.toJson(playerStatus));
        return action;

    }

    /**
     * Method that returns the action obtained by using 3-Nearest Neighbors with Euclidean Distance
     * @param playerStatus The status of the player
     * @return The action obtained by using 3-Nearest Neighbors with Euclidean Distance
     */
    public static Actions getActionUsingEuclideanDistance_3(Status playerStatus) {
        boolean isActionExecutable = false;
        int requestsCounter = 0;
        Actions action1 = null;
        Actions action2 = null;
        Actions action3 = null;

        while(!isActionExecutable) {

            if (requestsCounter == 0) {
                for (Status status : CASE_BASE) {
                    similarityMap.put(Equations.euclideanDistance(status.getVector(), playerStatus.getVector()), status.getAction());
                }
            }

            simValues = new ArrayList<>(similarityMap.keySet());
            minDistance = Collections.min(simValues);
            action1 = similarityMap.get(minDistance);
            similarityMap.remove(minDistance);

            simValues = new ArrayList<>(similarityMap.keySet());
            action2 = similarityMap.get( Collections.min(simValues));
            similarityMap.remove(minDistance);

            simValues = new ArrayList<>(similarityMap.keySet());
            action3 = similarityMap.get( Collections.min(simValues));
            similarityMap.remove(minDistance);


            if (action2.equals(action3) && !action2.equals(action1)) {
                bestIsNotFirst += 1;
                action = action2;
            } else {
                bestIsFirst += 1;
                action = action1;
            }

            isActionExecutable = Utils.checkIfActionExecutable(playerStatus, action);


            if (!isActionExecutable) {
                requestsCounter++;

            } else {
                similarityMap.clear();
            }
        }

        if (requestsCounter != 0) {
            numberOfRejectedActions += 1;
        }

        numberOfExecutedActions += 1;

        System.out.println("------------------------------------------------");
        System.out.println("Number of rejected Actions = " + numberOfRejectedActions);
        System.out.println("Number of executed Actions = " + numberOfExecutedActions);
        System.out.println("bestIsNotFirst: " + bestIsNotFirst);
        System.out.println("bestIsFirst: " + bestIsFirst);
        System.out.println( "action1 = " + action1);
        System.out.println( "action2 = " + action2);
        System.out.println( "action3 = " + action3);
        System.out.println("ACTION => " + action);
        System.out.println("------------------------------------------------");

        if (action.equals(Actions.PickWeapon)){
            numberOfActionPickWeapon += 1;
            System.out.println("####################################");
            System.out.println("numberOfActionPickWeapon: " + numberOfActionPickWeapon);
            System.out.println("####################################");

        }
        if (action.equals(Actions.PickHealth)){
            numberOfActionPickHealth += 1;
            System.out.println("####################################");
            System.out.println("numberOfActionPickHealth: " + numberOfActionPickHealth);
            System.out.println("####################################");

        }
        return action;

    }

}
