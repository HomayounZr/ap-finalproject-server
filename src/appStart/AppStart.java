package appStart;

import controllers.ScoreBoardController;
import helper.ThreadServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppStart {

    public static ScoreBoardController scoreBoardController;

    public static void runServer(){
        scoreBoardController = new ScoreBoardController();

        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            ServerSocket serverSocket = new ServerSocket(3000);

            while(true){
                Socket newSocket = serverSocket.accept();

                Thread threadServer = new Thread(new ThreadServer(newSocket));
                executorService.execute(threadServer);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        executorService.shutdown();
    }
}