package server;

import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class TCPServerThread extends Thread {
    private Socket socket;
    private String inputString;
    private String outputString;
    private AuctionProtocol auctionProtocol;
    private PrintWriter out;
    private BufferedReader in;
    public static List <TCPServerThread> TCP_SERVER_THREADS;

    public PrintWriter getOut() {
        return out;
    }


    public TCPServerThread(Socket socket) {
	super();
	this.socket = socket;
        inputString = "";
        outputString = "";
        auctionProtocol = new AuctionProtocol();
        TCP_SERVER_THREADS = new LinkedList();
    }

    public Socket getSocket() {
        return socket;
    }

    public void run() {
        
	try {
            TCP_SERVER_THREADS.add(this);
            System.out.println(socket.getInetAddress());
	    out = new PrintWriter(socket.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Welcome! You have been sucessfully connected!");            

	    while ((inputString = in.readLine()) != null) {         
                if(auctionProtocol.getAuctionList().size() > 0)
                {
                    out.println(processInput(inputString));
                }
                else{
                    outputString = processInput(inputString);
                    out.println(outputString);
                }
	    }
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }

    public String processInput(String theInput) {
        String theOutput = "";

        if (!theInput.isEmpty()){
            byte action = Byte.parseByte(theInput.substring(0, 1));
            switch(action){
                case 0: theOutput = auctionProtocol.advertiseAction(theInput);
                        break;
                case 1: theOutput = auctionProtocol.listAction(theInput);
                        break;
                case 2: theOutput = auctionProtocol.registerAction(theInput);
                        break;
                case 3: theOutput = auctionProtocol.bidAction(theInput);
                        break;
                case 4: theOutput = auctionProtocol.highestAction(theInput);
                        break;
                case 5: theOutput = auctionProtocol.historyAction(theInput);
                        break;
                case 6: theOutput = auctionProtocol.withdrawAction(theInput);
                        break;
                case 7: theOutput = auctionProtocol.participantsAction(theInput);
                        break;
                case 8: theOutput = auctionProtocol.messageAction(theInput,TCP_SERVER_THREADS);
                        break;
                case 9: theOutput = auctionProtocol.tableAction(theInput);
                        break;
                default:
                        break;
            }
        }
        return theOutput;
    }
}
