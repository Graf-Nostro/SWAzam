package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.Response;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;
import main.tuwien.ac.at.swazam.client.connector.Request;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;


public class MainClient {

	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.MainClient");
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public static void main(String[] args) throws IOException {
		
		IPeerConnector peerConnector = new PeerConnector("http://localhost:8080/SWAzamPeer");
		
		Fingerprint fp = Fingerprinter.getFingerprint(new File(System.getProperty("user.dir")+"/library/f01small.wav"));
		Request request = new Request(fp);
		
		try {
			Boolean response = peerConnector.sendRequest(request);
			if(response)
				logger.fine("request successful!");
			
			else
				logger.fine("request NOT successful");
				
		} catch (PeerNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
