package main.tuwien.ac.at.swazam.peer.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.tuwien.ac.at.swazam.peer.util.PeerCreator;

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
	public String status(@Context HttpServletRequest request) {
		new PeerCreator().createFromRequest(request);
		return "ok";
	}
}
