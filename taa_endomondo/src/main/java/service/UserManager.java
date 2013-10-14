package service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		public Collection<String> listUsers() {
			beginPersitence();
			UserDao dao = new UserDao(em);
			Collection<String> s = dao.getAllUsers();
			endPersitence();
			return s;
		}

		@GET
		@Path("getUserById/{id}")
		@Produces({ MediaType.APPLICATION_JSON })
		public String findUserWithId(@PathParam("id") int arg0) {
			beginPersitence();
			String res = new UserDao(em).userById(arg0);
			endPersitence();
			return res;
		}

		@GET
		@Path("getUserByEmail/{mail}")
		@Produces({ MediaType.APPLICATION_JSON })
		public String findUserWithMail(@PathParam("mail") String arg0) {
			beginPersitence();
			String res = new UserDao(em).userByEmail(arg0);
			endPersitence();
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
}
