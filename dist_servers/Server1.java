import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) throws IOException {
        int port = 5001; // Server1'in dinlediği port
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server1 is running on port " + port);

        // Server1, Server2 ve Server3'e bağlantı kurar
        try {
            Socket connectionToServer2 = new Socket("localhost", 5002);
            System.out.println("Connected to Server2");
            
            Socket connectionToServer3 = new Socket("localhost", 5003);
            System.out.println("Connected to Server3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Server1'in bağlantı kabul etmesi
        for (int i = 0; i < 2; i++) { // 2 bağlantı kabul edecek
            Socket connection = serverSocket.accept();
            System.out.println("Accepted connection from " + connection.getInetAddress());
        }

        serverSocket.close();
    }
}
