package main.tuwien.ac.at.swazam.server.endpoints.rest;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.tuwien.ac.at.swazam.server.core.CoreUserManagement;

import com.sun.jersey.api.view.Viewable;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class RESTUserManagement {
	
	@GET
	@Path("index")
	public Viewable index(@Context HttpServletRequest request) {
		request.setAttribute("obj", new String("IT Works"));
		System.out.println("/INDEX called");
		return new Viewable("/index.jsp", null);
	}
	
	@POST
	@Path("login")
	public Viewable login(@Context HttpServletRequest request) {
		// DO LOGIN
		
		request.setAttribute("obj", new String("IT Works"));
		System.out.println("/LOGIN called");
		return new Viewable("/index.jsp", null);
	}
	
	@POST
	@Path("/register")
	public Viewable register(@FormParam("name") String name, @FormParam("passwd") String passwd) {
		// DO REGISTER
		try {
			CoreUserManagement user = new CoreUserManagement();
			//user.createUser(name, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("obj", new String("IT Works"));
		System.out.println("/REGISTER called with " + name + " and " + passwd);
		
		return new Viewable("/../../index.html", null);
	}
}
