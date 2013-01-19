package main.tuwien.ac.at.swazam.peer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import main.tuwien.ac.at.swazam.peer.music.library.Fingerprinter;

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
	public Response requestJson(String json) {
		// create response obj. with gson to jason format
		
		//path user.dir is home dir must add the path manual
		//String PATH = System.getProperty("user.dir")+"/library/";
		Fingerprinter fprinter = new Fingerprinter("/home/user/Programmieren/workspace/SWAzam/SWAzamPeer/library");

		// convert json to fingerprint
		Gson gson = new Gson();
		Fingerprint fp = gson.fromJson(json, Fingerprint.class);
		
		//find match and return it
		String result = fprinter.matchFingerprintToLibrary(fp);

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