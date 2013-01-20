package main.tuwien.ac.at.swazam.peer.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;
import main.tuwien.ac.at.swazam.peer.music.library.Library;
import main.tuwien.ac.at.swazam.peer.util.Peer;
import main.tuwien.ac.at.swazam.peer.util.PeerCreator;
import main.tuwien.ac.at.swazam.util.PropertyReader;

import ac.at.tuwien.infosys.swa.audio.Fingerprint;

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

/**
 * 
 * REST endpoint for Client Requests
 * 
 * @author Raunig Stefan
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
@Path("/rest/find/music")
public class ClientToPeerREST {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String requestPlainText() {
		return "Searching music library ...";
	}

	// This method is called if JSON is request
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestJson(String json, @Context HttpServletRequest request) {
		Peer peer = new PeerCreator().createFromRequest(request);		
		Library library = peer.getLibrary();
		Fingerprint fingerprint = new Gson().fromJson(json, Fingerprint.class);
		String result = library.matchFingerprint(fingerprint);

		return Response.status(201).entity(result).build();
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String requestHtml() {
		return "<html> " + "<title>" + "SWAzam" + "</title>" + "<body><h1>"
				+ "Welcome to SWAzam this is a html response!" + "</body></h1>"
				+ "</html> ";
	}
}