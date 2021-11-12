import com.google.gson.Gson;
import models.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static similarity.SimilarityMethods.*;
import static util.Utils.*;

public class Main {
    public static Gson gson = new Gson();


    /**
     * Method that open port 5555 to enable the communication with the Unity side.
     * It receives a request from the agent and send a response to the agent
     */
    public static void receive() {
        while (true) {
            ServerSocket serverSocket = null;
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            String clientSentence = null;
            {
                try {
                    serverSocket = new ServerSocket(5555);
                    socket = serverSocket.accept();
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in));
                    clientSentence = inFromClient.readLine();
                    Request request = gson.fromJson(clientSentence, Request.class);

                    String toC = getResponse(request);

                    out.write(toC.concat("\r\n").getBytes());
                    out.flush();

                } catch (IOException e) {
                    System.out.println("Exception when trying to connect to the port");
                } finally {
                    try {
                        out.close();
                        in.close();
                        socket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        System.out.println("Exception when trying to run FINALLY");
                    }

                }
            }
        }

    }

    /**
     * Method that return a response which contains a plan to the Agent can execute
     * @param request The agent's current situation
     * @return Response to the Agent
     */
    public static String getResponse(Request request) {
        Plan plan = new Plan();

        // Get an Action based on a similarity measure
        Actions action = getActionUsingCosineSimilarity_1(request.getSituation().getPlayerStatus());

        plan.addAction(action);
        Situation situation = request.getSituation();
        return gson.toJson(new Response(situation, plan));
    }


    public static void main(String[] args) {
        // Read the data from the case base
        readTheCaseBase();

        // Open port 5555 to communicate with the Unity Side
        receive();

    }
}
