package server;

import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server started");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
        }

        while (listening)
	    new TCPServerThread(serverSocket.accept()).start();
        serverSocket.close();
    }
}
