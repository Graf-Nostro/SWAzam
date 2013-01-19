package main.tuwien.ac.at.swazam.server.core;
  
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.server.user.Peer; 

public class CorePeerManagement {
	private final Logger LOG = Logger.getLogger(CorePeerManagement.class.getName());
	
	private List<Peer> peer = Collections.synchronizedList(new ArrayList<Peer>());
	
	public CorePeerManagement() {
	}
	
	public boolean registerPeer(String name, String ip, Integer port) {
		try {
			peer.add(new Peer(name, ip, port));
			return true;
		} catch (Exception e) {
			LOG.warning("COULDNT ADD PEER");
		}
		return false;
	}
	public boolean registerPeer(Peer peer) {
		try {
			this.peer.add(peer);
			return true;
		} catch (Exception e) {
			LOG.warning("COULDNT ADD PEER");
		}
		return false;
	}
	
	public List<Peer> getPeers() {
		return peer;		
	}
	
}
