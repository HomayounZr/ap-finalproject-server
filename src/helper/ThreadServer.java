package helper;

import java.net.Socket;

public class ThreadServer implements Runnable {

    private Socket socket;

    public ThreadServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
