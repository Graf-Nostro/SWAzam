package main.tuwien.ac.at.swazam.client.utils;

import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.entity.Peer;

public class PeerManagement {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("main.tuwien.ac.at.swazam.client.utils.PeerManagement");
	
	private static Peer peer;
	
	
	public static void setPeer(Peer peer) {
		PeerManagement.peer = peer;
	}
	
	public static Peer getPeer() {
		return peer;
	}
	
	public static boolean isPeerAvailable() {
		return peer != null;
	}
	
}
