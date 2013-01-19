package test.tuwien.ac.at.peer.rest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientToPeerRestTest {

	private ClientConfig config;
	private Client client;
	private WebResource service;
		
	private final String TEXT_PLAIN_REST = "Searching music library ...";
	private final String TEXT_HTML_REST  = "<html> <title>SWAzam</title><body><h1>Welcome to SWAzam this is a html response!</body></h1></html> ";
	
	private final int OK201 = 201;
	
	private final String PATH = System.getProperty("user.dir")+"/library/";
	private final String FILE_NAME = "f01small.wav";
	
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
		try {
			File file = new File(PATH + FILE_NAME);
			Fingerprint fpRaw = Fingerprinter.getFingerprint(file);
			
			//convert Fingerprint to json
			Gson gson = new Gson();
			String json = gson.toJson(fpRaw);
			
			//send to REST endpoint
			ClientResponse response = service.path("rest/").path("find/music").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);  
			
			// Return code should be 201 == created resource
			assertEquals(OK201, response.getStatus());
			
			System.out.println("The name of the song is: " + response.getEntity(String.class));
			
		} catch (IOException e) {
			fail();
		} catch (UnsupportedAudioFileException e) {
			fail();
		}
	}
}
