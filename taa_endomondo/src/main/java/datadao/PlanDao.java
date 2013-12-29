package datadao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Plan;
import model.database.Route;
import model.database.User;

public class PlanDao extends DataDao {

	public PlanDao(EntityManager em) {
		super(em);
	}

	public Collection<Plan> getAllPlans() {

		String queryString = "select obj from Plan as obj ";

		try {
			System.out.println(queryString);
			Query query1 = em.createQuery(queryString);
			System.out.println(query1);
			List<Plan> li = query1.getResultList();
			//clear the users from each plan
			clearUsers((ArrayList<Plan>) li); 
			return li;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public Plan getPlanById(int Id) {

		Plan pl = em.find(Plan.class, Id);
		// clear the user
     	pl.setUsers(new ArrayList());
		return pl;

	}

	/*
	public Collection<Plan> getPlansByDate(Date startDate) {

		String queryString = "select p from Plan as p where startDate=:startDate";

		try {
			Query query1 = em.createQuery(queryString).setParameter(
					"startDate", startDate);
			Collection<Plan> li = query1.getResultList();

			return li;

		} catch (Exception e) {

			System.out.println("Exception caught while trying to in PlanDao");
		}
		return null;
	}

	public Route getRouteById(int Id) {

		String queryString = "Select p from Plan as p where p.id=:IdPlan";

		try {
			Query query1 = em.createQuery(queryString).setParameter("IdPlan",
					Id);
			Plan r = (Plan) query1.getSingleResult();
			return r.getRoute();

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	public Collection<Plan> getPlansBySportType(String sportType) {

		String querystring = "Select p from Plan as p where p.sportType=:sportType";

		try {
			Query query1 = em.createQuery(querystring).setParameter(
					"sportType", sportType);
			Collection<Plan> pls = query1.getResultList();

			return pls;

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	public Collection<User> getAllUsersById(int Id) {

		String querystring = "Select p from Plan as p where p.id=IdPlan";

		try {
			Query query1 = em.createQuery(querystring).setParameter("IdPlan",
					Id);
			Plan pl = (Plan) query1.getSingleResult();
			return pl.getUsers();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}*/

	public int createPlan(Plan p) {

		tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(p);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return 0;
		}

		return p.getId();
	}

	public boolean updatePlan(Plan p) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			em.merge(p);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean deletePlan(int id) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			Plan p = getPlanById(id);

			if (p == null)
				return false;

			em.remove(p);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}
	
  public void clearUsers(ArrayList<Plan> liplan){
	  for(int i=0 ; i< liplan.size(); i++){
		  liplan.get(i).setUsers(null);
	  }
  }
	
}
