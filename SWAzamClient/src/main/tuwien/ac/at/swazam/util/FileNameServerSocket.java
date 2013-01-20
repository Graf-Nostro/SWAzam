package main.tuwien.ac.at.swazam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FileNameServerSocket {

	private int PORT;
	
	public FileNameServerSocket(final int port){
		this.PORT = port;
	}
	
	public String readMsg(){
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(1);
        }
 
        Socket clientSocket = null;
        
        //accept socket con
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
		
        BufferedReader in = null; 
        String inMsg = null;
 
        //read msg
        try{
        	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	
        	if((inMsg = in.readLine()) != null) {
        		System.out.println("Server: " + inMsg);
        	}
        } catch(IOException e){
        	e.printStackTrace();
        }
        
        //cleanup
        try {
        	in.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return inMsg;
	}
}
