package main.tuwien.ac.at.swazam.peer.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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
		Response response = null;
		
		try {
			response = rest.get(new URL(serverURL + "/RESTPeerManagement/peers"));
			if (response.getCode() >= 300) {
				throw new ServerNotAvailableException();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Type collectionType = new TypeToken<ArrayList<Peer>>(){}.getType();
		ArrayList<Peer> peers = new Gson().fromJson(response.getBody(), collectionType);
		
		return peers;
	}

	public Boolean register(Peer peer) throws ServerNotAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Gson gson = new Gson();
			Response response = rest.put(new URL(serverURL + "/RESTPeerManagement/register"), gson.toJson(peer));
			if (response.getCode() >= 300) {
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
