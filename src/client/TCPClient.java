package client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient implements Runnable {

    static Socket clientSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;

    public TCPClient(String host, int port) throws UnknownHostException, IOException, InterruptedException{

        clientSocket = new Socket(host, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        inputLine = new BufferedReader(new InputStreamReader(System.in));

        if (clientSocket != null && out != null && in != null) {
            new Thread(this).start();
            while (!closed) {
                out.println(inputLine.readLine());
            }
            out.close();
            in.close();
            clientSocket.close();
        }
    }

    public void run() {
	String responseLine;
	try{
	    while ((responseLine = in.readLine()) != null) {
		System.out.println(responseLine);
                if (responseLine.indexOf("*** Bye") != -1) break;
	    }
            closed = true;
        } catch (Exception e) {
	    System.err.println("IOException:  " + e);
	}
    }

    public PrintWriter getPrintStream() {
        return out;
    }
}