package main.tuwien.ac.at.swazam.server.endpoints.rest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.tuwien.ac.at.swazam.server.core.CoreUserManagement;
import main.tuwien.ac.at.swazam.server.user.User;

import com.sun.jersey.api.view.Viewable;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class RESTUserManagement {
	CoreUserManagement user;
	@GET
	@Path("index")
	public Viewable index(@Context HttpServletRequest request) {
		request.setAttribute("obj", new String("IT Works"));
		System.out.println("/INDEX called");
		return new Viewable("/index.jsp", null);
	}
	
	@POST
	@Path("login")
	public Viewable login(@Context HttpServletRequest request, @FormParam("name") String name, @FormParam("passwd") String passwd) {
		System.out.println("/LOGIN called " + name +" " + passwd);
		// DO LOGIN
		try {
			user = new CoreUserManagement(); 
			if ( user.checkLogin(name, passwd) ) {
				request.setAttribute("name", new String(name));
				request.setAttribute("coins", user.getUserbyId(name).getCoins().toString());
				//request.setAttribute("songrequests", user.getUserbyId(name).getSongRequests());
				
				return new Viewable("/index.jsp", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Viewable("/../../index.html", null);
	}
	
	@POST
	@Path("/register")
	public Viewable register(@FormParam("name") String name, @FormParam("passwd") String passwd) {
		// DO REGISTER
		try {
			user = new CoreUserManagement();
			user.createUser(name, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("obj", new String("IT Works"));
		System.out.println("/REGISTER called with " + name + " and " + passwd);
		
		return new Viewable("/../../index.html", null);
	}
	
	@POST
	@Path("/update/{oldname}")
	public Viewable update(@PathParam("oldname") String oldname, @FormParam("name") String name, @FormParam("oldpasswd") String oldpasswd, @FormParam("newpasswd") String newpasswd) {

		System.out.println("/update called with oldname " + oldname + " and name " + name + " and oldpw " + oldpasswd + " and " + newpasswd);
		try {
			user = new CoreUserManagement();
			if (user.getUserbyId(oldname).getPassword().equals(new String(Base64.encode(oldpasswd.getBytes())))) {
				System.out.println("updateing ");
				User usr = user.getUserbyId(oldname);
				if (name != "") usr.setName(name);
				if (newpasswd != "") usr.setPassword(new String(Base64.encode(newpasswd.getBytes())));
				user.updateUser(usr);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("obj", new String("IT Works"));
		
		return new Viewable("/../../welcome-file.jsp", null);
	}
	
	@POST
	@Path("/delete/{pathname}")
	public Viewable delete(@PathParam("pathname") String name, @FormParam("name") String hiddenname) {
		try {
			user = new CoreUserManagement();
			if (hiddenname.equals(name)) {
				user.deleteUser(name);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("obj", new String("IT Works"));
		System.out.println("/delete called with " + name + " and " + hiddenname);
		
		return new Viewable("/../../welcome-file.jsp", null);
	}
}
