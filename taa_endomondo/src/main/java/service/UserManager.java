package service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.Plan;
import model.database.User;
import datadao.PlanDao;
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
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public UserManager() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
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
	@Path("getMyFriends/{nickname}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<User> retrievingFriends(@PathParam("nickname") String arg0) {

		Collection<User> res = new UserDao(em).getFriends(arg0);
        System.out.println(" Res Size is "+res.size());
		return res;
	}
	
	@GET
	@Path("getMyChallenges/{nickname}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Plan> retrievingPlans(@PathParam("nickname") String arg0) {

		Collection<Plan> res = new UserDao(em).getPlans(arg0);
        System.out.println(" Res Size is "+res.size());
		return res;
	}
	
	 // getUserByNickNameEmail(String _nickname,String _mail )
	@GET
	@Path("/{nickname}/{mail}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User authentifUserWithNicknameMail(@PathParam("nickname") String arg0, @PathParam("mail") String arg1) {
		User usr =null;
		//usr = new UserDao(em).getUserByNickNameEmail(arg0,arg1);
		System.out.println("searhc user for :"+arg0+"   -- "+arg1 );
		usr = new UserDao(em).getUserByNickNameEmail(arg0, arg1);
		System.out.println("Insde User manager : ");
		if(usr != null){ System.out.println("return userd nickname"+usr.getNickname());}
        return usr;
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
	public boolean createUser(User obj) {

		UserDao dataManager = new UserDao(em);
		//obj.setId(0);
		boolean result = dataManager.create(obj);  

		return result;
	}

	/**  add a friend  to my friend list */
	@POST
	@Path("{id}/addFriend/{idfriend}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean addFriend(Object o, @PathParam("id") int id, @PathParam("idfriend") int idfriend) {

		UserDao userManager = new UserDao(em);
		User me = userManager.getUserById(id);
		User mynewFriend = userManager.getUserById(idfriend);
		
		me.addFriend(mynewFriend);
		mynewFriend.addFriend(me);
	
		return userManager.update(me);  //  update the plan , so does the user vvia update persist
	
	}
	
	@POST
	@Path("addFriendByEmail/{mynickname}/{friendemail}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean addFriendByEmail(Object o, @PathParam("mynickname") String mynickname, @PathParam("friendemail") String friendemail) {

		UserDao userManager = new UserDao(em);
		User me = userManager.getUserByNickName(mynickname);
		User mynewFriend = userManager.getUserByEmail(friendemail);
		
		me.addFriend(mynewFriend);
		mynewFriend.addFriend(me);
	
	    System.out.println("adding friend by mail fini ! ");
		return userManager.update(me);  //  update the plan , so does the user vvia update persist
	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateUser(User obj) {

		UserDao dataManager = new UserDao(em);
		boolean result = dataManager.update(obj);

		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteUser(@PathParam("id") int id) {

		UserDao dataManager = new UserDao(em);
		boolean result = dataManager.delete(id);

		return result;
	}
}
