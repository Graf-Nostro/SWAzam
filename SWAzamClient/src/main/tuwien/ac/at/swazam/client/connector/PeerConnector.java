package main.tuwien.ac.at.swazam.client.connector;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.Fingerprinter;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import main.tuwien.ac.at.swazam.client.utils.PeerManagement;
import main.tuwien.ac.at.swazam.entity.Peer;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;
import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import com.google.gson.Gson;

public class PeerConnector implements IPeerConnector {
	
	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.connector.PeerConnector");

	private String peerURL; 
	
	public PeerConnector() {
	}
	
	public PeerConnector(String peerURL) {
		setPeerURL(peerURL);
	}
	
	public PeerConnector setPeerURL(String peerURL) {
		this.peerURL = peerURL;
		return this;
	}
	
	public String getPeerURL() {
		return peerURL;
	}
	
	
	@Override
	public String sendMusicRecognitionRequest(File sample) throws PeerNotAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Gson gson = new Gson();
			
			Fingerprint fp = Fingerprinter.getFingerprint(sample);
			Peer peer = PeerManagement.getPeer();
			
			String url = "http://" + peer.getIp() + ":" + peer.getPort() + "/SWAzamPeer-" + peer.getName() + "/rest/find/music";
			Response response = rest.post(new URL(url), gson.toJson(fp));
			
//			logger.info("response CODE = "+response.getCode());
//			logger.info("response MSG = "+response.getMessage());
//			logger.info("response BODY = "+response.getBody());
			
			if (response.getCode() >= 300) {
				throw new PeerNotAvailableException();
			} else {
				return response.getBody();
			}
		} catch (MalformedURLException e) {
			logger.warning("Invalid URL");
		} catch (IOException e) {
			logger.warning("Error when sending request");
		}
		
		return null;
	}
	
	
}
