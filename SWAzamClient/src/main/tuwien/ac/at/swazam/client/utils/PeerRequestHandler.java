package main.tuwien.ac.at.swazam.client.utils;

import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.client.connector.IServerConnector;
import main.tuwien.ac.at.swazam.client.exception.NoPeerAvailableException;
import main.tuwien.ac.at.swazam.entity.Peer;

public class PeerRequestHandler extends Thread {

	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.utils.PeerRequestHandler");
	
	private IServerConnector serverConnector;
	
	
	public PeerRequestHandler(IServerConnector serverConnector) {
		this.serverConnector = serverConnector;
	}


	@Override
	public void run() {
		
		Peer peer = null;

		while(true) {
			
			if(UserManagement.isLoginDataAvailable()) {
				
				if(peer == null) {
					try {
						logger.info("Ask for Peer");
						peer = serverConnector.askForPeer(UserManagement.getUsername(), UserManagement.getPassword());
						PeerManagement.setPeer(peer);
					} catch (NoPeerAvailableException e) {
						logger.warning("No Peer available");
					}
				}
				else
					break;
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
		}
		
	}
	
}
