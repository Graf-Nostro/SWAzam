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
			System.out.println("Missing peer name.");
			System.exit(-1);
		}
		
		// Get ServerConnector instance
		ServerConnector serverConnector = ServerConnector.getInstance();
		serverConnector.setServerURL("http://localhost:8080/SWAzamServer");
		
		// Create this peer
		peer = new Peer(args[0], "localhost", 8080);
		
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
