package main.tuwien.ac.at.swazam.peer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;

/**
 * Stores peers and provides possibilities to access peers.
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class PeerRegistry
{
	private static Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.peer.util.PeerRegistry");
	List<Peer> peers = new ArrayList<Peer>();
	ServerConnector serverConnector;
	
	public PeerRegistry(ServerConnector serverConnector) {
		this.serverConnector = serverConnector;
	}
	
	/**
	 * Fetches a list of peers from the servers and stores it in the registry.
	 * 
	 * @return
	 */
	public PeerRegistry updatePeersFromServer() {
		logger.log(Level.INFO, "Trying to get peer list from server.");
		ArrayList<Peer> peers = null;
		try {
			peers = serverConnector.getPeers();
		} catch (ServerNotAvailableException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < peers.size(); i++) {
			logger.log(Level.INFO, "Got peer from server: " + peers.get(i).getName());
			addPeer(peers.get(i));
		}
		
		return this;
	}
	
	/**
	 * Returns the peer with the given name.
	 * 
	 * @param name Name of the peer
	 * 
	 * @return The peer
	 */
	public Peer getPeer(String name) {
		for (int i = 0; i < peers.size(); i++) {
			if (name == peers.get(i).getName()) {
				return peers.get(i);
			}
		}
		return null;
	}

	/**
	 * Adds the given peer to the registry.
	 * 
	 * @param name Name of the peer.
	 * @param ip IP address of the peer.
	 * @param port Port of the peer.
	 * 
	 * @return
	 */
	public PeerRegistry addPeer(String name, String ip, Integer port) {
		addPeer(new Peer(name, ip, port));
		return this;
	}
	
	/**
	 * Adds the given peer to the registry
	 * 
	 * @param peer Peer
	 * 
	 * @return
	 */
	public PeerRegistry addPeer(Peer peer) {
		peers.add(peer);
		return this;
	}
	
	/**
	 * Returns the number of peers in the registry.
	 * 
	 * @return Number of peers in the registry
	 */
	public Integer getSize() {
		return peers.size();
	}
	
	public Peer getRandomPeer() {
		logger.log(Level.INFO, "Selecting random peer. Peer count: " + peers.size());
		
		if (peers.size() == 0) {
			return null;
		}
		
		int index = 0 + (int)(Math.random() * ((peers.size()) - 1));
		return peers.get(index);
	}
	
}
