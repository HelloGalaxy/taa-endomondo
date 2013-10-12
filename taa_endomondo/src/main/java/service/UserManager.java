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
public class UserManager {

	EntityManagerFactory emf;
	EntityManager em;

	private void init() {
		// Use The persistence.xml configuration
		emf = Persistence.createEntityManagerFactory("endomondo");
		// Retrieve an entity manager
		em = emf.createEntityManager();
		// work with entity manager

	}

	
	
	
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Bonjour premier  test qvec jersy "
				+ "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

	@GET
	@Path("/allFirstNames")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<String> listUsers() {
		init();
		UserDao dao = new UserDao(em);
		Collection<String> s = dao.getAllUsers();
		closeresources();
		return s;
	}
   
	
	/**
	 *   Search a  
	 */
  	@GET
	@Path("searchById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String findById(@PathParam("id") int arg0) {
		init();
		String res = new UserDao(em).getUserById(arg0);
		closeresources();
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

	public void closeresources() {
		em.close();
		emf.close();
	}
}
