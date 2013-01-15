package test.tuwien.ac.at.swazam.peer.util;

import static org.junit.Assert.*;

import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.peer.util.PeerRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * PeerRegistryTest
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class PeerRegistryTest {
	
	private PeerRegistry peerRegistry;

	@Before
	public void setUp() throws Exception {
		peerRegistry = new PeerRegistry();
	}

	@After
	public void tearDown() throws Exception {
		peerRegistry = null;
	}

	@Test
	public void testAddPeer() {
		Peer peer = new Peer("peer1", "127.0.0.1", 80);
		peerRegistry.addPeer(peer);
		assertEquals(new Integer(1), peerRegistry.getSize());
	}
	
	@Test
	public void testAddPeerIpAndPort() {
		peerRegistry.addPeer("peer1", "127.0.0.1", 80);
		assertEquals(new Integer(1), peerRegistry.getSize());
	}
	
	@Test
	public void testGetPeer() {
		peerRegistry.addPeer("peer1", "127.0.0.1", 80);
		peerRegistry.addPeer("peer2", "127.0.0.1", 81);
		assertEquals("peer1", peerRegistry.getPeer("peer1").getName());
	}

}
