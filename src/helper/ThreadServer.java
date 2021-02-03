package helper;

import appStart.AppStart;
import models.BoardItem;
import models.servermodels.*;
import org.json.simple.JSONArray;

import java.io.*;
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
            System.out.println(request.getRoute());
//            Object object = inputStream.readObject();
//            System.out.println(object);
//            RestMessage request = (RestMessage) object;
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

            } else if(request.getRoute().equalsIgnoreCase("newSave")){

                String username = ((StringMessage)request.getBody()).getContent();

//                DataInputStream dataInputStream = new DataInputStream(inputStream);
//                byte[] buffer = new byte[50000];
//                int bytes = dataInputStream.read(buffer,0,buffer.length);
//                AppStart.savesController.saveNewFile(username,buffer,bytes);

                byte[] buffer = new byte[4096];
                BufferedInputStream in = new BufferedInputStream(new DataInputStream(inputStream));
                File file = new File("./data/saves/" + username + ".txt");
                if(file.exists()){
                    file.delete();
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
//                System.out.println("hellooooo");
                while(in.available() > 0){
                    System.out.println("reading...");
                    int count = in.read(buffer);
                    out.write(buffer,0,count);
                }
                out.flush();

//                System.out.println("saved...");

                out.close();
                in.close();

            } else if(request.getRoute().equalsIgnoreCase("getSaves")){

                String username = ((StringMessage) request.getBody()).getContent();
//                byte[] buffer = new byte[4096];
//                BufferedInputStream in = new BufferedInputStream(AppStart.savesController.getUserSaves(username));
//                BufferedOutputStream out = new BufferedOutputStream(new DataOutputStream(socket.getOutputStream()));
//                while(in.available() > 0){
//                    int count = in.read(buffer);
//                    out.write(buffer,0,count);
//                    System.out.println("chunk " + count);
//                }
//                out.flush();
                File file = new File("./data/saves/" + username + ".txt");

                DataInputStream in = new DataInputStream(new FileInputStream(file));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeLong(file.length());
                int bytes = 0;
                byte[] buffer = new byte[4096];
//                System.out.println("Checkpoint");
                while((bytes = in.read(buffer)) != -1){
                    System.out.println("chunk" + bytes);
                    out.write(buffer,0,bytes);
                    out.flush();
                }
                in.close();

            } else {
                outputStream.writeObject(new RestMessage("CLIENT",new StringMessage("NOT FOUND")));
            }

            outputStream.flush();

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception ex){

            }
        }
    }
}
