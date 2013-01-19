package test.tuwien.ac.at.peer.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientToPeerRESTTEST {

	private ClientConfig config;
	private Client client;
	private WebResource service;
		
	private final String TEXT_PLAIN_REST = "Searching music library ...";
	private final String TEXT_HTML_REST  = "<html> <title>SWAzam</title><body><h1>Welcome to SWAzam this is a html response!</body></h1></html> ";
	private final String TEXT_JSON_REST  = "";
	
	@Before
	public void setUp() throws Exception {
		config  = new DefaultClientConfig();
		client  = Client.create(config);
		service = client.resource(UriBuilder.fromUri("http://localhost:8080/SWAzamPeer").build());
	}
	
	@After
	public void tearDown() throws Exception {
		config  = null;
		client.destroy();
		service = null;
	}
	
	@Test
	public void responsePlainText() {
		// Get plain text
		assertEquals(TEXT_PLAIN_REST, service.path("rest/").path("find/music").accept(MediaType.TEXT_PLAIN).get(String.class));
		System.out.println(service.path("rest/").path("find/music").accept(MediaType.TEXT_PLAIN).get(String.class));
	}
	
	@Test
	public void responseHtmlTest() {
		// Get html
		assertEquals(TEXT_HTML_REST, service.path("rest/").path("find/music").accept(MediaType.TEXT_HTML).get(String.class));
		System.out.println(service.path("rest/").path("find/music").accept(MediaType.TEXT_HTML).get(String.class));
	}
	
	@Test
	public void responseJsonTest() {
		// Get JSON for application
		assertEquals(TEXT_JSON_REST, service.path("rest/").path("find/music").accept(MediaType.APPLICATION_JSON).get(String.class));
		System.out.println(service.path("rest/").path("find/music").accept(MediaType.APPLICATION_JSON).get(String.class));
	}
}
