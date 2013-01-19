package main.tuwien.ac.at.swazam.server.endpoints.rest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.tuwien.ac.at.swazam.server.core.CorePeerManagement;
import main.tuwien.ac.at.swazam.server.core.CoreUserManagement;
import main.tuwien.ac.at.swazam.server.user.Peer;

import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;

@Path("/RESTPeerManagement")
@Produces(MediaType.APPLICATION_JSON)
public class RESTPeerManagement {
	CorePeerManagement peerManagement;
	
	@PUT
	@Path("/register")
	public Response register(String request) {
		/* DO REGISTER 
		 * Request Body:
		 * {"ip":"127.0.0.1","port":8080,"name":"peer1"} */
		System.out.println("printing out request: " + request);
		try {
			Gson gson = new Gson();
			Peer peer = gson.fromJson(request, Peer.class);
			peerManagement = new CorePeerManagement();
			peerManagement.registerPeer(peer);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		System.out.println("/REGISTER called ");
		
		return Response.created(null).build();
	}
	
	@GET
	@Path("/peers")
	@Produces("application/json")
	public String peers(@Context HttpServletRequest request) {
		try {
			peerManagement = new CorePeerManagement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		// produce json
		
		System.out.println("/getList called");
		
		return gson.toJson(peerManagement.getPeers());
	}
}