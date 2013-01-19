package test.tuwien.ac.at.swazam.peer.util;

import static org.junit.Assert.*;

import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.peer.util.PeerFinder;
import main.tuwien.ac.at.swazam.peer.util.PeerRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PeerFinderTest {
	
	PeerFinder peerFinder;

	@Before
	public void setUp() throws Exception {
		ServerConnector serverConnector = ServerConnector.getInstance();
		serverConnector.setServerURL("http://localhost:8080/SWAzamServer");
		PeerRegistry peerRegistry = new PeerRegistry(serverConnector);
		peerFinder = new PeerFinder(peerRegistry);
		
		serverConnector.register(new Peer("peer1", "localhost", 8080));
		peerRegistry.updatePeersFromServer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindPeer() {
		Peer peer = peerFinder.findPeer();
		assertNotNull(peer);
	}

}
