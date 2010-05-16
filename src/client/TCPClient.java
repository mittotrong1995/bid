package client;

import adt.Client;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class TCPClient implements Runnable {

    static Socket clientSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;
    static Thread t;
    static Client client;
    String responseLine;

    public TCPClient(String host, int port) throws UnknownHostException, IOException, InterruptedException{

        client = new Client(host);
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

    public static Client getClient() {
        return client;
    }

    public void run() {
	try{
	    while ((responseLine = in.readLine()) != null) {
                if(responseLine.indexOf("connected") > -1)
                {
                    JOptionPane.showMessageDialog(null,responseLine, "Connection Accepted", 1);
                }
                
		System.out.println(responseLine);
	    }
            closed = true;
        } catch (Exception e) {
	    System.err.println("IOException:  " + e);
	}
    }

    public static BufferedReader getIn() {
        return in;
    }

    public PrintWriter getPrintWriter() {
        return out;
    }

    public String sendString()
    {
        return responseLine;
    }

    void closeConnection() throws IOException {
            out.close();
            in.close();
            clientSocket.close();
    }
}