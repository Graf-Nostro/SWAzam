package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;

public class MusicRequestTest {

	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.MusicRequestTest");
	
	public static void main(String[] args) throws IOException {
		
		
		IPeerConnector peerConnector = new PeerConnector("http://localhost:8081/SWAzamPeer");
		
		try {
			String response = peerConnector.sendMusicRecognitionRequest(new File(System.getProperty("user.dir")+"/library/f01small.wav"));
			if(response != null)
				logger.fine("request successful!");
			
			else
				logger.fine("request NOT successful");
				
		} catch (PeerNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
