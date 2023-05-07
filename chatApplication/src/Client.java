import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            Scanner serverReader = new Scanner(socket.getInputStream());
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            Scanner userInput = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String userName = userInput.nextLine();
            serverWriter.println(userName);
            serverWriter.flush();

            Thread messageThread = new Thread(() -> {
                while (true) {
                    if (serverReader.hasNextLine()) {
                        String serverMessage = serverReader.nextLine();
                        System.out.println(serverMessage);
                    }
                }
            });
            messageThread.start();

            String clientMessage;

            do {
                clientMessage = userInput.nextLine();
                serverWriter.println(clientMessage);
                serverWriter.flush();
            } while (!clientMessage.equals("exit"));

            serverReader.close();
            serverWriter.close();
            userInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


