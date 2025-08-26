import java.io.*;
import java.net.*;

public class App {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server running on port 80...");
        while (true) {
            Socket client = serverSocket.accept();
            OutputStream out = client.getOutputStream();
            out.write("HTTP/1.1 200 OK\r\n\r\nHello from Java App".getBytes());
            out.flush();
            client.close();
        }
    }
}
