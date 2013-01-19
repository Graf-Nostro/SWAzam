package test.tuwien.ac.at.swazam.peer.connector;

import static org.junit.Assert.*;

import main.tuwien.ac.at.swazam.peer.connector.ServerConnector;
import main.tuwien.ac.at.swazam.peer.connector.ServerNotAvailableException;
import main.tuwien.ac.at.swazam.peer.util.Peer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServerConnectorTest {
	
	private ServerConnector serverConnector;

	@Before
	public void setUp() throws Exception {
		serverConnector = ServerConnector.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		serverConnector = null;
	}

	@Test
	public void testSetServerUrl() {
		serverConnector.setServerURL("http://localhost/SWAzamServer");
		assertEquals("http://localhost/SWAzamServer", serverConnector.getServerURL());
	}
	
	@Test
	public void testRegister() {
		serverConnector.setServerURL("http://echo.braincrafted.com");
		Peer peer = new Peer("peer1", "127.0.0.1", 8080);
		try {
			serverConnector.register(peer);
			assertTrue(true);
		} catch (ServerNotAvailableException e) {
			assertFalse(true);
		}
	}

}
