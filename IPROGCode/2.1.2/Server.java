import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private int port = 2000;
    private ArrayList<Client> connectedPeers = new ArrayList<>();

    public static void main(String[] args) {
        Server server = new Server();
        server.run(args);
    }

    public static synchronized void print(String message) {
        System.out.println(message);
    }

    public synchronized void connectPeer() {
        Client client = new Client(this);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void run(String[] args) {

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        try {
            serverSocket = new ServerSocket(port);
            Server.print("Server open on port: " + port);

            Socket socket = serverSocket.accept();
            System.out.println("New socket connected");

        } catch (Exception e) {
            System.out.println("Socket unable to connect");
        }

    }
}