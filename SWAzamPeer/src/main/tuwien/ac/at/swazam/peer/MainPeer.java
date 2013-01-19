package main.tuwien.ac.at.swazam.peer;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.UnsupportedAudioFileException;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;
import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.NoSuchFileInMusicLibrary;
import main.tuwien.ac.at.swazam.peer.util.Peer;

public class MainPeer {
	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.MainPeer");

	public static void main(String[] args) {
		
		// Get ServerConnector instance
		ServerConnector serverConnector = ServerConnector.getInstance();
		serverConnector.setServerURL("http://localhost:8080");
		
		// Create this peer
		Peer peer = new Peer("peer", "localhost", 8080);
		
		// Register peer at server
		try {
			serverConnector.register(peer);
		} catch (ServerNotAvailableException e) {
			System.out.println("Could not register peer at server.");
			logger.log(Level.SEVERE, e.getMessage(), e.getStackTrace());
		}
		
		/*
		//TODO make a junit testcase out of it
		Fingerprinter fp = new Fingerprinter("");
		
		try {
			String filename = "f01.wav";
			//String sample   = "sample-01.wav";
			
			String path = System.getProperty("user.dir")+"/library/";
			
			File file = new File(path + filename);
			
			Fingerprint fp1 = Fingerprinter.getFingerprint(file);
			Fingerprint fp2 = fp.loadFingerprintFromMap(filename);
			
			System.out.print("Matching original file to stored file: ");
			double result1 = fp1.match(fp2);

			System.out.println(result1);
			
		} catch (NoSuchFileInMusicLibrary e) {
			System.out.println("Error " + e.getMessage());
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error IO");
			
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
