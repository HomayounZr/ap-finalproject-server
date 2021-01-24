package helper;

import appStart.AppStart;
import models.BoardItem;
import models.servermodels.*;

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

                ArrayList<BoardItem> items = AppStart.scoreBoardController.getScores();
                outputStream.writeObject(new RestMessage("CLIENT",new BoardItemsMessage(items)));

            } else if(request.getRoute().equalsIgnoreCase("newRecord")){

                NewBoardItemMessage newItem = (NewBoardItemMessage) request.getBody();
                boolean result = AppStart.scoreBoardController.addScore(newItem.getItem());
                String response = "OK";
                if(!result)
                    response = "USERNAME";

                outputStream.writeObject(new RestMessage("CLIENT",new StringMessage(response)));

            } else if(request.getRoute().equalsIgnoreCase("changeUsername")){

                ChangeUsernameMessage usernames = (ChangeUsernameMessage)request.getBody();
                boolean result = AppStart.scoreBoardController.changeUsername(usernames.getPrevUsername(),usernames.getNewUsername());
                String response = "OK";
                if(!result)
                    response = "EXISTS";

                outputStream.writeObject(new RestMessage("CLIENT",new StringMessage(response)));

            } else {
                outputStream.writeObject(new RestMessage("CLIENT",new StringMessage("NOT FOUND")));
            }

            outputStream.flush();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
