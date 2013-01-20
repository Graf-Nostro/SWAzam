package main.tuwien.ac.at.swazam.peer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.UnsupportedAudioFileException;


import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;

import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.LibrarySerializer;
import main.tuwien.ac.at.swazam.peer.util.Peer;

/**
 * MainPeer
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class MainPeer {
	
	public static final String PROPERTY_FILE = "tomcat.properties";
	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.MainPeer");
	public static Peer peer;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Missing action.");
			System.exit(-1);
		}
		if (args.length < 2) {
			System.err.println("Missing peer name.");
			System.exit(-1);
		}
		
		MainPeer mainPeer = new MainPeer();
		
		if (args[0].equals("start")) {
			mainPeer.start(args);
		} else if (args[0].equals("add")) {
			mainPeer.add(args);
		} else if (args[0].equals("remove")) {
			mainPeer.remove(args);
		} else if (args[0].equals("list")) {
			mainPeer.list(args);
		} else {
			System.err.println("Unkown action \"" + args[0] + "\"");
			System.exit(-1);
		}
		
		System.out.println("\n\nShutdown. Thank you very much.");
		System.exit(0);
	}
	
	protected Fingerprinter fingerprinter;
	protected LibrarySerializer librarySerializer;
	
	private void start(String[] args) {
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
			try{
				port = new Integer(args[3]);
			} catch (NumberFormatException ex) {
				port = 8080;
			}
		} else {
			port = 8080;
		}
				
		// Create this peer
		peer = new Peer(args[1], ip, port);
				
		// Register peer at server
		try {
			serverConnector.register(peer);
			logger.log(Level.INFO, "Registered peer '" + peer.getName() + "' at server.");
			initializeLibrary(peer);
		} catch (ServerNotAvailableException e) {
			logger.log(Level.INFO, "Could not register peer at server.");
			e.printStackTrace();
		}
	}
	
	private void list(String[] args) {
		Peer peer = new Peer(args[1]);
		initializeLibrary(peer);
		
		System.out.println("Songs currently in the library:");
		for (File song : peer.getLibrary().getSongs()) {
			System.out.println(" - " + song.getName());
		}
	}
	
	private void add(String[] args) {
		Peer peer = new Peer(args[1]);
		initializeLibrary(peer);
		
		if (args.length < 3) {
			System.err.println("Missing file name.");
			System.exit(-1);
		}
		
		File file = new File(System.getProperty("library-directory") + "/" + args[2]);
		try {
			peer.getLibrary().addSong(file);
			librarySerializer.serialize();
			logger.log(Level.INFO, "Saved library to \"" + peer.getLibrary().getLibraryFile() + "\".");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not read file \"" + file.getAbsoluteFile() + "\".");
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			logger.log(Level.SEVERE, "Audio encoding in \"" + file.getAbsoluteFile() + "\" is not supported.");
			e.printStackTrace();
		}
	}
	
	private void remove(String[] args) {
		Peer peer = new Peer(args[1]);
		initializeLibrary(peer);
		
		if (args.length < 3) {
			System.err.println("Missing file name.");
			System.exit(-1);
		}
		
		File file = new File(System.getProperty("library-directory") + "/" + args[2]);
		peer.getLibrary().deleteSong(file);
		librarySerializer.serialize();
		logger.log(Level.INFO, "Saved library to \"" + peer.getLibrary().getLibraryFile() + "\".");
	}
	
	protected void initializeLibrary(Peer peer) {
		librarySerializer = new LibrarySerializer(peer.getLibrary());
		librarySerializer.deserialize();
	}
}
