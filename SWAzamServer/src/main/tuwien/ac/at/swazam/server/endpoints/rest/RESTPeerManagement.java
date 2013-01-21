package main.tuwien.ac.at.swazam.server.endpoints.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
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
		
		try {
			peerManagement = new CorePeerManagement();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.created(null).build();
		}
		
		System.out.println("printing out request: " + request);
		try {
			Gson gson = new Gson();
			Peer peer = gson.fromJson(request, Peer.class);
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
		Gson gson = new Gson();
		try {
			peerManagement = new CorePeerManagement();
		} catch (Exception e) {
			return gson.toJson(null);
		}
		
		System.out.println("/peers called (returning " + peerManagement.getPeers().size() + " peers)");
		
		return gson.toJson(peerManagement.getPeers());
	}
	
	/*Server akzeptiert einen PeerLookup Request des Clients (#13)*/
	@PUT
	@Path("/lookup")
	public Peer lookup(String request) {
		/* Request Body:
		 * {"username":"pw"} */
		System.out.println("printing out request: " + request);
		Peer peer = null;
		try {
			peerManagement = new CorePeerManagement();
			CoreUserManagement user = new CoreUserManagement();
			Gson gson = new Gson();
			String[] data = gson.fromJson(request, String[].class); 
			if ( user.checkLogin(data[0], data[1]) ) {
				return peerManagement.getRandomPeer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		System.out.println("/lookup called ");
		
		return peer;
	}
	
}
