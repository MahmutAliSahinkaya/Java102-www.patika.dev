public class Main {
    public static void main(String[] args) {
        // Sunucuyu başlatma
        Thread serverThread = new Thread(() -> {
            System.out.println("Server starting...");
            Server.main(null);
        });
        serverThread.start();

        // İstemciyi başlatma
        Thread clientThread = new Thread(() -> {
            System.out.println("Client starting...");
            Client.main(null);
        });
        clientThread.start();
    }
}
