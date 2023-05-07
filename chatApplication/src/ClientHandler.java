import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private PrintWriter writer;
    private Scanner reader;

    public ClientHandler(Socket clientSocket, PrintWriter writer) {
        this.clientSocket = clientSocket;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            reader = new Scanner(clientSocket.getInputStream());

            String userName = reader.nextLine();
            Server.broadcast(userName + " joined the chat.");

            String clientMessage;
            do {
                clientMessage = reader.nextLine();
                Server.broadcast(userName + ": " + clientMessage);
            } while (!clientMessage.equals("exit"));

            Server.broadcast(userName + " left the chat.");
            Server.clients.remove(writer);
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}