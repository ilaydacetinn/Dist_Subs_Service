import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        int port = 5002; // Server2'nin dinlediği port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server2 is running on port " + port);

        // Server2, Server1 ve Server3'e bağlantı kurar
        try {
            Socket connectionToServer1 = new Socket("localhost", 5001);
            System.out.println("Connected to Server1");
            
            Socket connectionToServer3 = new Socket("localhost", 5003);
            System.out.println("Connected to Server3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Server2'nin bağlantı kabul etmesi
        for (int i = 0; i < 2; i++) { // 2 bağlantı kabul edecek
            Socket connection = serverSocket.accept();
            System.out.println("Accepted connection from " + connection.getInetAddress());
        }

        serverSocket.close();
    }
}
