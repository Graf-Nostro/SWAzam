package main.tuwien.ac.at.swazam.peer.util;

public class PeerFinder {
	
	PeerRegistry peerRegistry;
	
	public PeerFinder(PeerRegistry peerRegistry) {
		this.peerRegistry = peerRegistry;
	}

	public Peer findPeer() {
		System.out.println("findPeer");
		Peer randomPeer = peerRegistry.getRandomPeer();
		return randomPeer;
	}
	
}
