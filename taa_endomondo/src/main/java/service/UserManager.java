package service;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.User;
import datadao.UserDao;

/*
 * getUserById
 * getUserByEmail
 * getUserByName -> we need search by last/first Name
 * getAllUsers
 * getAllPlans
 * getFriends
 * 
 */

/**
 * Rest request linstener ....
 * 
 * @author Boussad
 * 
 * */

@Path("/user")
public class UserManager extends BasicManager {

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Bonjour premier  test qvec jersy "
				+ "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

	@GET
	@Path("/allusers")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<User> listUsers() {

		UserDao dao = new UserDao(em);
		Collection<User> s = dao.getAllUsers();

		return s;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User findUserWithId(@PathParam("id") int arg0) {

		User res = new UserDao(em).getUserById(arg0);

		return res;
	}

	@GET
	@Path("getUserByEmail/{mail}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User findUserWithMail(@PathParam("mail") String arg0) {

		User res = new UserDao(em).getUserByEmail(arg0);

		return res;
	}

	@GET
	@Path("/search")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<String> list() {
		Collection<String> s = new ArrayList<String>();
		s.add("Jhone smith ");
		s.add("Alan bistole");
		return s;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createCoord(User obj) {


		UserDao dataManager = new UserDao(em);
		obj.setId(0);
		boolean result = dataManager.create(obj);
	
		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateCoord(User obj) {


		UserDao dataManager = new UserDao(em);
		boolean result = dataManager.update(obj);

		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteCoord(@PathParam("id") int id) {


		UserDao dataManager = new UserDao(em);
		boolean result = dataManager.delete(id);


		return result;
	}
}
