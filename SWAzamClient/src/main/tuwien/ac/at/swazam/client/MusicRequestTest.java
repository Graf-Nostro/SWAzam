package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;
import main.tuwien.ac.at.swazam.client.connector.Request;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class MusicRequestTest {

	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.MusicRequestTest");
	
	public static void main(String[] args) throws IOException {
		
		
		IPeerConnector peerConnector = new PeerConnector("http://localhost:8081/SWAzamPeer");
		
		Fingerprint fp = Fingerprinter.getFingerprint(new File(System.getProperty("user.dir")+"/library/f01small.wav"));
//		Fingerprint fp2 = Fingerprinter.getFingerprint(new File(System.getProperty("user.dir")+"/library/f01.wav"));
		
//		logger.info("match = "+fp.match(fp2));
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
