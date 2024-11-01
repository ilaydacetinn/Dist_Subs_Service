import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    public static void main(String[] args) throws IOException {
        int port = 5003; // Server3'ün dinlediği port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server3 is running on port " + port);

        // Server3, Server1 ve Server2'ye bağlantı kurar
        try {
            Socket connectionToServer1 = new Socket("localhost", 5001);
            System.out.println("Connected to Server1");
            
            Socket connectionToServer2 = new Socket("localhost", 5002);
            System.out.println("Connected to Server2");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Server3'ün bağlantı kabul etmesi
        for (int i = 0; i < 2; i++) { // 2 bağlantı kabul edecek
            Socket connection = serverSocket.accept();
            System.out.println("Accepted connection from " + connection.getInetAddress());
        }

        serverSocket.close();
    }
}
