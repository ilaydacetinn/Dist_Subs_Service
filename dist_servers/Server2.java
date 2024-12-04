import java.io.*;
import java.net.*;
import java.time.Instant;

public class Server2 {
    public static void main(String[] args) {
        int port = 5002; // Server2 için port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server2 is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // İstemciyi kabul et
                new Thread(new ClientHandler(clientSocket)).start(); // Her istemci için yeni bir thread başlat
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String message = in.readLine();
                System.out.println("Received: " + message);

                if (message != null && message.contains("method=STRT")) {
                    out.println("demand=STRT response=YEP"); // Başarılı yanıt
                    System.out.println("Sent response: YEP");
                } else if (message != null && message.contains("demand=CPCTY")) {
                    // Kapasite sorgusuna yanıt ver
                    long timestamp = Instant.now().getEpochSecond();
                    String response = String.format("server2_status: 1000 timestamp: %d", timestamp);
                    out.println(response);
                    System.out.println("Sent capacity response: " + response);
                } else {
                    out.println("demand=STRT response=NOP"); // Başarısız yanıt
                    System.out.println("Sent response: NOP");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}