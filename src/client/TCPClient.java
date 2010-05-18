package client;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class TCPClient implements Runnable{

    static Socket clientSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;
    static Thread t;
    String responseLine;
    Thread clientThread;
    
    public Thread getClientThread() {
        return clientThread;
    }

    public void setClientThread(Thread clientThread) {
        this.clientThread = clientThread;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public TCPClient(String host, int port) throws UnknownHostException, IOException, InterruptedException{
        clientSocket = new Socket(host, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        inputLine = new BufferedReader(new InputStreamReader(System.in));

        if (clientSocket != null && out != null && in != null) {
            t = new Thread(this);
            t.start();

        }
    }

    public void run() {
        while (!closed) {
            try {
                out.println(inputLine.readLine());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Error has occured","Error",3);
            }
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