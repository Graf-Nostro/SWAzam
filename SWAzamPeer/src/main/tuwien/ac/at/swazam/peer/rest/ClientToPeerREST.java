package main.tuwien.ac.at.swazam.peer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String sayHello() {
	  //create response obj. with gson to jason format
	  return "";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "SWAzam" + "</title>"
        + "<body><h1>" + "Welcome to SWAzam this is a html response!" + "</body></h1>" + "</html> ";
  }

} 