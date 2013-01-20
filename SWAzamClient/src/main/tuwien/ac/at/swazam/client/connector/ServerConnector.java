package main.tuwien.ac.at.swazam.client.connector;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import main.tuwien.ac.at.swazam.client.Fingerprinter;
import main.tuwien.ac.at.swazam.client.exception.LoginFailedException;
import main.tuwien.ac.at.swazam.client.exception.NoPeerAvailableException;
import main.tuwien.ac.at.swazam.client.exception.PeerNotAvailableException;
import main.tuwien.ac.at.swazam.client.exception.RegistrationFailedException;
import main.tuwien.ac.at.swazam.entity.Peer;
import main.tuwien.ac.at.swazam.util.RESTUtil;
import main.tuwien.ac.at.swazam.util.Response;

public class ServerConnector implements IServerConnector {

	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.connector.ServerConnector");
	
	private String serverURL;
	
	public ServerConnector() {
	}
	
	public ServerConnector(String serverURL) {
		setServerURL(serverURL);
	}
	
	public ServerConnector setServerURL(String serverURL) {
		this.serverURL = serverURL;
		return this;
	}
	
	public String getServerURL() {
		return serverURL;
	}
	
	
	
	@Override
	public Boolean register(String username, String password)
			throws RegistrationFailedException {
		
		RESTUtil rest = new RESTUtil();
		
		try {

			Response response = rest.post(new URL(System.getProperty("server-url") + "/register?name="+username+"&passwd="+password), "");
			
			logger.info("response code = "+response.getCode());
			logger.info("response msg = "+response.getMessage());
			logger.info("response body = "+response.getBody());
			
			if (response.getCode() >= 300)
				throw new RegistrationFailedException();
			
			else
				return true;
			
		} catch (MalformedURLException e) {
			logger.warning("Invalid URL");
		} catch (IOException e) {
			logger.warning("Error when sending request");
		}
		
		return false;
	}

	@Override
	public Boolean login(String username, String password)
			throws LoginFailedException {
		
		RESTUtil rest = new RESTUtil();
		
		try {
			
			Response response = rest.post(new URL(System.getProperty("server-url") + "/login?name="+username+"&passwd="+password), "");
			
			logger.info("response code = "+response.getCode());
			logger.info("response msg = "+response.getMessage());
			logger.info("response body = "+response.getBody());
			
			if (response.getCode() >= 300)
				throw new LoginFailedException();
			
			else
				return true;
			
		} catch (MalformedURLException e) {
			logger.warning("Invalid URL");
		} catch (IOException e) {
			logger.warning("Error when sending request");
		}
		return false;
	}

	@Override
	public Peer askForPeer(String username, String password) throws NoPeerAvailableException {
		RESTUtil rest = new RESTUtil();
		
		try {
			Gson gson = new Gson();
//			
			String[] arr = new String[2];
			arr[0] = username;
			arr[1] = password;
			
			Response response = rest.put(new URL(System.getProperty("server-url") + "/RESTPeerManagement/lookup"), gson.toJson(arr));
			
			Peer peer = gson.fromJson(response.getBody(), Peer.class);
			logger.info("peer = "+peer.getName());
			
//			logger.info("response code = "+response.getCode());
//			logger.info("response msg = "+response.getMessage());
//			logger.info("response body = "+response.getBody());
			
			if (response.getCode() >= 300)
				throw new NoPeerAvailableException();
			
			else
				return peer;
			
		} catch (MalformedURLException e) {
			logger.warning("Invalid URL");
		} catch (IOException e) {
			logger.warning("Error when sending request");
		}
		return null;
	}
	
	
}
