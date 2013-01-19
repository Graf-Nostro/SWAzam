package main.tuwien.ac.at.swazam.client.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;

import com.google.gson.Gson;

public class PeerConnector implements IPeerConnector {

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
	public Boolean sendRequest(Request request) throws PeerNotAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Gson gson = new Gson();
			Response response = rest.put(new URL(peerURL + "/rest/find/music "), gson.toJson(request));
			if (response.getCode() != 200) {
				throw new PeerNotAvailableException();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
}
