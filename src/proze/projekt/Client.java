package proze.projekt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public class Client {
    private Socket clientSocket;
    private DataOutputStream output;
    private DataInputStream input;

    public Client(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public String login()  {
        try {
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());

            String toSend = "LOGIN";
            output.writeUTF(toSend);

            String received = input.readUTF();
            if(!received.startsWith("LOGGED_IN")) {
                clientSocket.close();
                System.out.println(received);
                return "LOGIN_FAILED";
            }
            return received.substring(10); //erase the first 10 chars containing LOGGED_IN
        }
        catch(IOException e) {
            e.printStackTrace();

        }
        return "LOGIN_FAILED";
    }
    public String getHighScores()
    {
        try{
            output.writeUTF("GET_HIGH_SCORES");
            String highscores = input.readUTF();
            return highscores;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
    public int getLevels()
    {
        try{
            output.writeUTF("GET_LEVELS");
            String received = input.readUTF();
            if(received.startsWith("LEVELS")){
                return Integer.parseInt(received.substring(6));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }
    public int sendScore(String nick, int score)
    {
        try {
            output.writeUTF("SCORE"+nick+" "+score);
            String response = input.readUTF();
            if(response.equals("SCORE_ACCEPTED"))
                return 0;

        }catch (SocketException e) {
            try {
                clientSocket.close();
                System.exit(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void closeConnection(boolean isHost)
    {
        try {
            output.writeUTF("CLOSE");

            output.close();
            input.close();
            clientSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}