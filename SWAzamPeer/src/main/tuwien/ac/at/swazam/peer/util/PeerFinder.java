package main.tuwien.ac.at.swazam.peer.util;

import main.tuwien.ac.at.swazam.peer.connector.PeerConnector;

public class PeerFinder {
	
	PeerRegistry peerRegistry;
	private PeerConnector peerConnector;
	
	public PeerFinder(PeerRegistry peerRegistry) {
		this.peerRegistry = peerRegistry;
		this.peerConnector = PeerConnector.getInstance();
	}

	public Peer findPeer() {
		Peer peer;
		peer = peerRegistry.getRandomPeer();
		while (peerNotAvailable(peer)) {
			peer = peerRegistry.getRandomPeer();
		}
		return peer;
	}

	private Boolean peerNotAvailable(Peer peer) {
		return peerConnector.peerStatus(peer);
	}
}
