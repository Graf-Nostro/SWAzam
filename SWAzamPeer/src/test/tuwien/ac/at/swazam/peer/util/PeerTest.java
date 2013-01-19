package test.tuwien.ac.at.swazam.peer.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.tuwien.ac.at.swazam.peer.util.Peer;

/**
 * PeerTest.
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
public class PeerTest {
	
	private Peer peer;

	@Before
	public void setUp() throws Exception {
		peer = new Peer();
	}

	@After
	public void tearDown() throws Exception {
		peer = null;
	}
	
	@Test
	public void testConstructorNameAndIpAndPort() {
		peer = new Peer("peer1", "127.0.0.1", 80);
		assertEquals("peer1", peer.getName());
		assertEquals("127.0.0.1", peer.getIp());
		assertEquals(new Integer(80), peer.getPort());
	}
	
	@Test
	public void testSetName() {
		peer.setName("peer1");
		assertEquals("peer1", peer.getName());
	}

	@Test
	public void testSetIp() {
		peer.setIp("127.0.0.1");
		assertEquals("127.0.0.1", peer.getIp());
	}
	
	@Test
	public void testSetPort() {
		peer.setPort(80);
		assertEquals(new Integer(80), peer.getPort());
	}

}
