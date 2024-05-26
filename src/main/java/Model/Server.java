package Model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     InputStream input = clientSocket.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                     OutputStream output = clientSocket.getOutputStream();
                     PrintWriter writer = new PrintWriter(output, true)) {

                    System.out.println("New client connected");
                    String text;

                    while ((text = reader.readLine()) != null) {
                        System.out.println("Received: " + text);
                        writer.println("Server: " + text);

                        if (text.equalsIgnoreCase("bye")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Client handling exception: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(2005);
    }

}
