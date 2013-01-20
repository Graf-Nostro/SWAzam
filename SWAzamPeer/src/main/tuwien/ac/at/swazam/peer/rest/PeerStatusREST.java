package main.tuwien.ac.at.swazam.peer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.tuwien.ac.at.swazam.peer.MainPeer;
import main.tuwien.ac.at.swazam.util.PropertyReader;

/**
 * PeerStatusREST
 * 
 * @author Florian Eckerstorfer <florian@eckerstorfer.co>
 */
@Path("/status")
public class PeerStatusREST {
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		System.out.println(PropertyReader.getInstance(MainPeer.PROPERTY_FILE).getProperty("library-directory"));
		return "ok";
	}
}
