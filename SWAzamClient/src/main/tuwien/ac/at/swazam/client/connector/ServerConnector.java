package main.tuwien.ac.at.swazam.client.connector;

import main.tuwien.ac.at.swazam.client.exception.LoginFailedException;
import main.tuwien.ac.at.swazam.client.exception.RegistrationFailedException;
import main.tuwien.ac.at.swazam.entity.Peer;

public class ServerConnector implements IServerConnector {

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
	public void register(String username, String password)
			throws RegistrationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String username, String password)
			throws LoginFailedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Peer askForPeer() {
		
		return null;
	}
	
	
}
