package server;

import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);

        } catch (IOException e) {
            System.err.println("Cannot listen on port: 4444.");
        }

        while (listening){
            socket = serverSocket.accept();
	    new TCPServerThread(socket).start();
        }
        serverSocket.close();
    }
}
