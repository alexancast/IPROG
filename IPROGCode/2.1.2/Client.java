import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {

    private Server server;
    private Socket socket;
    private boolean running = false;

    public Client(Server server) {

        this.server = server;
        start();

    }

    @Override
    public void run() {

        running = true;
        while (running)
        {
            try {
                socket = server.getServerSocket().accept();
                Server.print("New socket has connected!");
                server.connectPeer();
                waitForMessage();
            } catch (Exception e) {
                Server.print("Could not bind socket to server");
            }
        }
    }

    public void waitForMessage(){

        while(running)
        {
            
        }

    }

    public void stopThread(){
        running = false;
    }

}