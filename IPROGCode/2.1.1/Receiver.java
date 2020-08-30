import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Alexander Castman - alca3002
 */
public class Receiver extends Thread {

    private boolean running;
    private BufferedReader reader;
    private Client client;

    /**
     * Constructior that sets attributes of the Receiver objects.
     * @param client that created the receiver and handles the messages.
     * @param reader that reads messages from the server.
     */
    public Receiver(Client client, BufferedReader reader){

        this.reader = reader;
        this.client = client;
    }


    /**
     * Method that uppdates continously looking for new messages from the server.
     */
    @Override
    public void run(){

        running = true;

        while(running){

            try{

                String message = reader.readLine();

                if(message == null){
                    client.quitProgram("Connection lost...");
                } else{
                    
                    client.receiveMessage(message);
                }


            } catch(IOException e){
                System.err.println("Error occured when reading line");
            }
        }

    }


    /**
     * Method that stops the thread.
     */
    public void stopThread(){
        running = false;
    }


}