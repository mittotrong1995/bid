package server;

import java.net.*;
import java.io.*;

public class TCPServerThread extends Thread {
    private Socket socket = null;

    public TCPServerThread(Socket socket) {
	super();
	this.socket = socket;
    }

    public void run() {

	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	    String inputLine, outputLine;
	    AuctionProtocol ap = new AuctionProtocol();
	    outputLine = ap.processInput("");
	    out.println(outputLine);

	    while ((inputLine = in.readLine()) != null) {
		outputLine = ap.processInput(inputLine);
		out.println(outputLine);
	    }
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }
}
