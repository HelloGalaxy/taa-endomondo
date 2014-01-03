package service;

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
 * getPlanById
 * getAllPlans
 * getPlansByDate -> start date
 * getRoute
 * getAllParticipant // world is not right, correct by dic
 * getPlansBySportType
 * post create
 * put update 
 * delete delete
 */
@Path("/plan")
public class PlanManager {

	protected EntityManagerFactory emf;
	protected EntityManager em;

	public PlanManager() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}

	@GET
	@Path("/allPlans")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Plan> listPlans() {

		Collection<Plan> p = new PlanDao(em).getAllPlans();
		return p;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Plan findPlanById(@PathParam("id") int arg0) {

		Plan pl = new PlanDao(em).getPlanById(arg0);
		return pl;
	}


	@POST
	@Path("{usernickname}/createplan")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Plan createPlan(Plan p, @PathParam("usernickname") String user_nickname) {

		PlanDao dataManager = new PlanDao(em);
		p.setId(0);
		
		System.out.println(" Creating plan : title"+ p.getTitle() + " note"+p.getNote()+"...");
		dataManager.createPlan(p);

		// getting the user how wants to create the plan
		UserDao usrdao = new UserDao(em);
		User us = usrdao.getUserByNickName(user_nickname);
		if(us==null){  System.out.println("user not found !");  return null ; }
		else{
			System.out.println("user  found !, nickname was "+user_nickname);
		}
		
		//  faire l'association
		us.addPlan(p);
		p.addUser(us);
		dataManager.updatePlan(p);
		usrdao.update(us);
		
		return p;
	}

	/**  add a user to a given plan */
	@POST
	@Path("{id}/addUser/{iduser}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean addUser(Object o, @PathParam("id") int id, @PathParam("iduser") int iduser) {

		PlanDao planManager = new PlanDao(em);
		Plan p = planManager.getPlanById(id); 
		// get the plan
		UserDao userManager = new UserDao(em);
		User us = userManager.getUserById(iduser); 
		p.addUser(us);
		us.addPlan(p);		
		return planManager.updatePlan(p);  //  update the plan , so does the user vvia update persist
	}
	
	
	@POST
	@Path("{id}/addUserByNickname/{usernickname}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean addUserByNickName(Object o, @PathParam("id") int id, @PathParam("usernickname") String nickname) {

		PlanDao planManager = new PlanDao(em);
		Plan p = planManager.getPlanById(id); 
		// get the plan
		UserDao userManager = new UserDao(em);
		User us = userManager.getUserByNickName(nickname); 
		p.addUser(us);
		us.addPlan(p);		
		return planManager.updatePlan(p);  //  update the plan , so does the user vvia update persist
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updatePlan(Plan p) {

		PlanDao dataManager = new PlanDao(em);
		boolean result = dataManager.updatePlan(p);

		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deletePlan(@PathParam("id") int id) {

		PlanDao dataManager = new PlanDao(em);
		boolean result = dataManager.deletePlan(id);

		return result;
	}
}
