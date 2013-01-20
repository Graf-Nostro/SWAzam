package main.tuwien.ac.at.swazam.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * FileServerSocket is used to send the Filename back to the client
 * 
 * @author Raunig Stefan
 */
public class FileNameClientSocket {

	private String HOST;
	private int    PORT; 
	
	public FileNameClientSocket(final String host, final int port){
		this.HOST = host;
		this.PORT = port;
	}
		
	public void sendMsg(final String fileName){

        Socket socket   = null;
        PrintWriter out = null;
        //open socket con
        try {
            socket = new Socket(HOST, PORT);
            
            //write filename
            out = new PrintWriter(socket.getOutputStream(), true);
			out.println(fileName);
        } catch (UnknownHostException e) {
            System.err.println("Can not reach host: " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + HOST);
            System.exit(1);
        }

        //clean up
        try {
        	out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
