package client;

import java.io.*;
import java.net.*;

public class TCPClient implements Runnable {

    static Socket clientSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;
    static Thread t;

    public TCPClient(String host, int port) throws UnknownHostException, IOException, InterruptedException{

        clientSocket = new Socket(host, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        inputLine = new BufferedReader(new InputStreamReader(System.in));

        if (clientSocket != null && out != null && in != null) {
            new Thread(this).start();
            t = new Thread(this){
                public void run(){
                     while (!closed) {
                        try {
                            out.println(inputLine.readLine());
                        } catch (IOException ex) {

                        }
                    }
                };
            };
            t.start();

        }
    }

    public void run() {
	String responseLine;
	try{
	    while ((responseLine = in.readLine()) != null) {
		System.out.println(responseLine);
	    }
            closed = true;
        } catch (Exception e) {
	    System.err.println("IOException:  " + e);
	}
    }

    public PrintWriter getPrintStream() {
        return out;
    }

    void closeConnection() throws IOException {
            out.close();
            in.close();
            clientSocket.close();
    }
}