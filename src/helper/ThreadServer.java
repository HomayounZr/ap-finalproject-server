package helper;

import appStart.AppStart;
import models.RestMessage;
import models.ScoreItem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServer implements Runnable {

    private Socket socket;

    public ThreadServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
                ){

            RestMessage request = (RestMessage) inputStream.readObject();
            if(request.getRoute().equalsIgnoreCase("getAll")){

                ArrayList<ScoreItem> items = AppStart.scoreBoardController.getScores();
                outputStream.writeObject(new RestMessage("CLIENT",items));

            } else if(request.getRoute().equalsIgnoreCase("newRecord")){

                ScoreItem newItem = (ScoreItem) request.getBody();
                boolean result = AppStart.scoreBoardController.addScore(newItem);
                String response = "OK";
                if(!result)
                    response = "USERNAME";

                outputStream.writeObject(new RestMessage("CLIENT",response));

            } else if(request.getRoute().equalsIgnoreCase("changeUsername")){

                String newUsername = (String) request.getBody();
                boolean result = AppStart.scoreBoardController.changeUsername(newUsername);
                String response = "OK";
                if(!result)
                    response = "USERNAME";

                outputStream.writeObject(new RestMessage("CLIENT",response));

            } else {
                outputStream.writeObject(new RestMessage("CLIENT","NOT FOUND"));
            }

            outputStream.flush();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
