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
    private String localaddr;
    public static List <TCPServerThread> TCP_SERVER_THREADS = new LinkedList();

    public PrintWriter getOut() {
        return out;
    }

    public TCPServerThread(Socket socket) {
	super();
	this.socket = socket;
        inputString = "";
        outputString = "";
        auctionProtocol = new AuctionProtocol();
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
	try {
            TCP_SERVER_THREADS.add(this);
            localaddr = socket.getInetAddress().getHostAddress();
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
            auctionProtocol.changeHighestBidder(localaddr);
	}
    }

    public String processInput(String theInput) {
        String theOutput = "";
        try{
        if (!theInput.isEmpty()){
            byte action = Byte.parseByte(theInput.substring(0, 1));
            switch(action){
                case 0: theOutput = auctionProtocol.advertiseAction(theInput,TCP_SERVER_THREADS);
                        break;
                case 1: theOutput = auctionProtocol.listAction(theInput);
                        break;
                case 2: theOutput = auctionProtocol.registerAction(theInput);
                        break;
                case 3: theOutput = auctionProtocol.bidAction(theInput,TCP_SERVER_THREADS);
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
        }
        catch(Exception e){
            theOutput = auctionProtocol.disconnect(theInput);
        }
        return theOutput;
    }
}
