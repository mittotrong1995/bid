package client;

import java.io.*;
import java.net.*;

public class TCPClient implements Runnable{

    static Socket clientSocket = null;
    static PrintStream os = null;
    static DataInputStream is = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;

    public static void main(String[] args) {

	int port_number = 4444;
        String host = "localhost";

	try {
            clientSocket = new Socket(host, port_number);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host: " + host);
        }

	// If everything has been initialized then we want to write some data
	// to the socket we have opened a connection to on port port_number

        if (clientSocket != null && os != null && is != null) {
            try {
                new Thread(new TCPClient()).start();
		while (!closed) {
                    os.println(inputLine.readLine());
                }
		os.close();
		is.close();
		clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    public void run() {
	String responseLine;
	try{
	    while ((responseLine = is.readLine()) != null) {
		System.out.println(responseLine);
		if (responseLine.indexOf("*** Bye") != -1) break;
	    }
            closed = true;
	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }
}