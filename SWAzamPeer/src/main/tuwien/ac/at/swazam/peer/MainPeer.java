package main.tuwien.ac.at.swazam.peer;

import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;

import main.tuwien.ac.at.swazam.peer.util.Peer;

public class MainPeer {
	
	public static final String PROPERTY_FILE = "tomcat.properties";
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.MainPeer");
	public static Peer peer;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Missing action.");
			System.exit(-1);
		}
		if (args.length < 2) {
			System.out.println("Missing peer name.");
			System.exit(-1);
		}
		
		System.out.println(args[0]);
		
		if (args[0].equals("start")) {
			start(args);
		} else {
			System.out.println("Unkown action \"" + args[0] + "\"");
		}
	}
	
	private static void start(String[] args) {
		// Get ServerConnector instance
		ServerConnector serverConnector = ServerConnector.getInstance();
		serverConnector.setServerURL(System.getProperty("server-url"));
		
		String ip;
		Integer port;
		if (args.length == 3) {
			ip = args[2];
		} else {
			ip = "localhost";
		}
		if (args.length == 4) {
			port = new Integer(args[3]);
		} else {
			port = 8080;
		}
				
		// Create this peer
		peer = new Peer(args[1], ip, port);
				
		System.out.print("Peer name: " + peer.getName());
				
		// Register peer at server
		try {
			serverConnector.register(peer);
			System.out.println("Registered peer '" + peer.getName() + "' at server.");
		} catch (ServerNotAvailableException e) {
			System.out.println("Could not register peer at server.");
			e.printStackTrace();
		}
	}
}
