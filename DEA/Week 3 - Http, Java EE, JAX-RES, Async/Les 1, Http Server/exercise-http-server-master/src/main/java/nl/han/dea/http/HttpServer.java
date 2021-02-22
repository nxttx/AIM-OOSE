package nl.han.dea.http;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {

    private int tcpPort;

    public HttpServer(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public static void main(String[] args) {
        new HttpServer(8383).startServer();
    }

    public void startServer() {
        try (
                var serverSocket = new ServerSocket(this.tcpPort);
        ) {
            System.out.println("The Server is listening on port " + tcpPort);

            while(true) {
            var acceptedSocket = serverSocket.accept();
            var connectionHandler = new ConnectionHandler(acceptedSocket);
            new Thread(()-> connectionHandler.handle()).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
