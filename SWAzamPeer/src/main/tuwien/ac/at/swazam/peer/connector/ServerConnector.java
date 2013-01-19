package main.tuwien.ac.at.swazam.peer.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;

public class ServerConnector {
	
	private static ServerConnector instance;
	
	public static ServerConnector getInstance() {
		if (null == instance) {
			instance = new ServerConnector();
		}
		return instance;
	}
	
	private String serverURL; 
	
	private ServerConnector() {
	}
	
	private ServerConnector(String serverURL) {
		setServerURL(serverURL);
	}
	
	public ServerConnector setServerURL(String serverURL) {
		this.serverURL = serverURL;
		return this;
	}
	
	public String getServerURL() {
		return serverURL;
	}
	
	public ArrayList<Peer> getPeers() throws ServerNotAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Response response = rest.get(new URL(serverURL + "/RESTPeerManagement/peers"));
			if (response.getCode() >= 300) {
				throw new ServerNotAvailableException();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Peer> result = new ArrayList<Peer>();
		// TODO: Parse result and add to array
		return result;
	}

	public Boolean register(Peer peer) throws ServerNotAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Gson gson = new Gson();
			Response response = rest.put(new URL(serverURL + "/RESTPeerManagement/register"), gson.toJson(peer));
			if (response.getCode() != 200) {
				throw new ServerNotAvailableException();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
