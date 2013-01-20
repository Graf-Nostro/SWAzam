package main.tuwien.ac.at.swazam.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.connector.IPeerConnector;
import main.tuwien.ac.at.swazam.client.connector.PeerConnector;
import main.tuwien.ac.at.swazam.client.connector.Request;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import main.tuwien.ac.at.swazam.client.utils.ClientUtils;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

public class ClientThread implements Runnable {

	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.ClientThread");
	
	private String sample;
	
	
	public ClientThread(String sample) {
		this.sample = sample;
	}
	
	
	@Override
	public void run() {
		
		IPeerConnector peerConnector = new PeerConnector("http://localhost:8081/SWAzamPeer");
		
		try {
			
			logger.info("path = "+ClientUtils.getFilePath()+sample);
			Fingerprint fp = Fingerprinter.getFingerprint(new File(ClientUtils.getFilePath()+sample));
			Boolean result = peerConnector.sendRequest(new Request(fp));
			
			if(result) {
				logger.info("Request sent successfully");
			}
			
		} catch (IOException e) {
			logger.severe("Fingerprint error");
		} catch (PeerNotAvailableException e) {
			logger.warning("Peer not available");
			e.printStackTrace();
		}
		
	}

}
