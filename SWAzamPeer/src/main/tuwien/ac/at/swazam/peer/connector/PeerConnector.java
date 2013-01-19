package main.tuwien.ac.at.swazam.peer.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;

public class PeerConnector {
	
	private static PeerConnector instance;
	
	public static PeerConnector getInstance() {
		if (null == instance) {
			instance = new PeerConnector();
		}
		return instance;
	} 
	
	private PeerConnector() {
	}
		
	public Boolean peerStatus(Peer peer) {
		RESTUtil rest = new RESTUtil();
		Response response = null;
		
		try {
			response = rest.get(new URL("http://" + peer.getIp() + ":" + peer.getPort() + "/SWAzamPeer/status"));
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
