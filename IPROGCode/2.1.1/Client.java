import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Low level stream socket chat application client.
 * 
 * @author Alexander Castman - alca3002
 */
public class Client extends JFrame {

    private JButton sendButton;
    private JTextField messageField;
    private JPanel messagePanel;

    private PrintWriter writer;

    /**
     * Constructor that setup the messaging window and adds imortant event
     * listeners.
     */
    public Client() {

        super("Chat app");
        setSize(600, 500);
        setLocationRelativeTo(null);

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(messagePanel), BorderLayout.CENTER);

        JPanel sendPanel = new JPanel();
        sendPanel.setBackground(Color.DARK_GRAY);
        sendPanel.setLayout(new BorderLayout());
        add(sendPanel, BorderLayout.SOUTH);

        messageField = new JTextField();
        sendPanel.add(messageField, BorderLayout.CENTER);

        sendButton = new JButton("Send!");
        sendPanel.add(sendButton, BorderLayout.EAST);

        SendListener sendListener = new SendListener();
        messageField.addKeyListener(sendListener);
        sendButton.addKeyListener(sendListener);
        sendButton.addActionListener(sendListener);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * Main method that runs when starting the program.
     * 
     * @param args
     */
    public static void main(String[] args) {

        Client client = new Client();
        client.run(args);

    }

    /**
     * Method that connects client to server and creates threads for reading and
     * writing messages.
     */
    public void run(String[] args) {

        String IPAdress = "127.0.0.1";
        int port = 2000;

        switch (args.length) {

            case 2:
                try {
                    port = Integer.parseInt(args[1]);

                } catch (NumberFormatException e) {
                    System.err.println("Error converting port, remains as default");
                }

            case 1:
                IPAdress = args[0];
                break;

            case 0:
                break;

            default:
                quitProgram("Invalid number of arguments");

        }

        try {

            Socket socket = new Socket(IPAdress, port);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            String connectionInfo = socket.getInetAddress().toString() + ":" + socket.getPort();
            receiveMessage("CONNECTED TO: " + connectionInfo.substring(1));
            setTitle("Chat App - " + connectionInfo.substring(1));
            System.out.println("Connection successful");
            Receiver receiver = new Receiver(this, inputStream);
            receiver.run();

        } catch (Exception e) {

            quitProgram("Connection unsuccessful...");

        }

    }

    /**
     * Method that closes the java program and prints a message in the terminal.
     */
    public void quitProgram(String message) {
        System.out.println(message);
        System.exit(0);
    }

    /**
     * Prints a message received from the server to the message panel displayed in
     * the client window.
     * 
     * @param message that is received from the server.
     */
    public void receiveMessage(String message) {

        messagePanel.add(new JLabel(message));
        messagePanel.revalidate();
        messagePanel.repaint();

    }

    /**
     * Inner class that handles the events that sends a message to the server.
     */
    class SendListener implements ActionListener, KeyListener {

        /**
         * Method that takes the text from the text field and sends it to the server.
         */
        public void sendMessage() {

            String message = messageField.getText();
            writer.println(message);

            messageField.setText("");

        }

        /**
         * Action invoked when pressing a button.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            sendMessage();

        }

        /**
         * Action invoked when pressing a key.
         */
        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMessage();
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
    }

}