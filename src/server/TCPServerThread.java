package server;

import java.net.*;
import java.io.*;

public class TCPServerThread extends Thread {
    private Socket socket;
    private String inputString;
    private String outputString;
    private AuctionProtocol auctionProtocol;

    public TCPServerThread(Socket socket) {
	super();
	this.socket = socket;
        inputString = "";
        outputString = "";
    }

    public void run() {

	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Welcome! You have been sucessfully connected!");

	    auctionProtocol = new AuctionProtocol();
            auctionProtocol.processInput("");
//	    outputString = auctionProtocol.processInput("");
//	    out.println(outputString);

	    while ((inputString = in.readLine()) != null) {
		outputString = auctionProtocol.processInput(inputString);
		out.println(outputString);
	    }
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }
}
