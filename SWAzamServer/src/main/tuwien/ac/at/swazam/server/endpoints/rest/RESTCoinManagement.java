package main.tuwien.ac.at.swazam.server.endpoints.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.tuwien.ac.at.swazam.server.core.CoreCoinManagement;
import main.tuwien.ac.at.swazam.server.user.SongRequest;

import com.google.gson.Gson;

@Path("/RESTCoinManagement")
@Produces(MediaType.APPLICATION_JSON)
public class RESTCoinManagement {
	CoreCoinManagement coinManagement;
	
	/*Server akzeptiert einen Request von einem Peer wenn dieser einen Titel erkannt hat (#10)*/
	@PUT
	@Path("/acceptrequest")
	public Response acceptrequest(String request) {
		/* DO REGISTER 
		 * Request Body:
		 * {"requestname":"test","name":"peer1", coins:3, recognizedSong: false} */
		
		try {
			coinManagement = new CoreCoinManagement();
			Gson gson = new Gson();
			SongRequest req = gson.fromJson(request, SongRequest.class);
			coinManagement.addSongRequest(req);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.created(null).build();
		}
		
		System.out.println("printing out request: " + request);
		try {
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		System.out.println("/acceptrequest called ");
		
		return Response.created(null).build();
	}
}
