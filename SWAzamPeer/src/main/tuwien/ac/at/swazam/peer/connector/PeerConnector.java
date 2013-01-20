package main.tuwien.ac.at.swazam.peer.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import com.google.gson.Gson;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;

public class PeerConnector {
	
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.connector.PeerConnector");
	private static PeerConnector instance;
	
	public static PeerConnector getInstance() {
		if (null == instance) {
			instance = new PeerConnector();
		}
		return instance;
	} 
	
	private PeerConnector() {
	}
	
	public String redirectMatchRequest(Peer peer, Fingerprint fingerprint) {
		if (peer == null) {
			logger.log(Level.SEVERE, "No peer given!");
		}
		
		RESTUtil rest = new RESTUtil();
		Response response = null;
		String url = "http://" + peer.getIp() + ":" + peer.getPort() + "/SWAzamPeer-" + peer.getName() + "/rest/find/music ";
		
		try {
			response = rest.post(new URL(url), new Gson().toJson(fingerprint));
			return response.getBody();
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Malformed URL \"" + url + "\".");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not complete request to \"" + url + "\".");
			e.printStackTrace();
		}
		
		return null;
	}
		
	public Boolean peerStatus(Peer peer) {
		if (peer == null) {
			return new Boolean(false);
		}
		
		RESTUtil rest = new RESTUtil();
		Response response = null;
		
		try {
			response = rest.get(new URL("http://" + peer.getIp() + ":" + peer.getPort() + "/SWAzamPeer-" + peer.getName() + "/status"));
			if (response.getCode() >= 300) {
				return new Boolean(false);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (response.getBody() == "ok") {
			return new Boolean(true);
		} else {
			return new Boolean(false);
		}
	}

}
