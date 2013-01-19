package main.tuwien.ac.at.swazam.peer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		return "ok";
	}
}
