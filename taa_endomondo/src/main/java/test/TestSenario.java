package test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.database.*;

public class TestSenario {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Use The persistence.xml configuration
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("endomondo");
		// Retrieve an entity manager
		EntityManager em = emf.createEntityManager();
		// work with entity manager

		EntityTransaction tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();

			// Do some stuff

			// 1: create a first user ...
			User us1 = new model.database.User();
			us1.setEmail("us1@yahoo.fr");
			em.persist(us1);
			Date joind = new Date();
			joind.setDate(9 / 7 / 2010);
			Date birth = new Date();
			birth.setDate(9 / 7 / 1985);
			us1.setBirthday(birth);
			us1.setJoindate(joind);
			us1.setSex(Sex.Male);
			us1.setFacebook("facebook/us1.fr");
			us1.setHeight((float) 1.85);
			us1.setNickname("us1NickName");
			us1.setFirstname("Marc");
			us1.setLastname("dupon");

			// create a second user
			User us2 = new model.database.User();
			us2.setEmail("us2@gmail.fr");
			em.persist(us2);
			Date joind2 = new Date();
			joind.setDate(9 / 7 / 2011);
			Date birth2 = new Date();
			birth.setDate(9 / 7 / 1984);
			us2.setBirthday(birth);
			us2.setJoindate(joind);
			us2.setSex(Sex.Male);
			us2.setFacebook("facebook/us2.fr");
			us2.setHeight((float) 1.87);
			us2.setNickname("us2NickName");
			us2.setFirstname("Jacky");
			us2.setLastname("chen");
            // create a thir user 
			User us3 = new model.database.User();
			us3.setEmail("us3@gmail.fr");
			em.persist(us3);
			Date joind3 = new Date();
			joind.setDate(9 / 7 / 2011);
			Date birth3 = new Date();
			birth.setDate(9 / 7 / 1984);
			us3.setBirthday(birth);
			us3.setJoindate(joind);
			us3.setSex(Sex.Male);
			us3.setFacebook("facebook/us3.fr");
			us3.setHeight((float) 1.72);
			us3.setNickname("us3NickName");
			us3.setFirstname("Alan");
			us3.setLastname("michel");
			
			// creation of plans
			  // first plan
			Plan plan1 = new Plan();
			em.persist(plan1);
			plan1.setAvgHeartRate(0);
			plan1.setEndDate(new Date(2013, 10, 20, 15, 0));
			plan1.setNote("Running in the centre place");
			plan1.setStartDate(new Date(2013, 10, 20, 10, 40));
			plan1.setTitle("Running seance");
			plan1.setSportType(SportType.Running);
			
			 //second plan
			
			Plan plan2 = new Plan();
			em.persist(plan2);
			plan2.setAvgHeartRate(0);
			plan2.setEndDate(new Date(2013, 10, 20, 15, 0));
			plan2.setNote("Swiming in ville jean");
			plan2.setStartDate(new Date(2013, 10, 20, 10, 40));
			plan2.setTitle("swimming seance");
			plan2.setSportType(SportType.Swimming);
			
			
			// fix the friend ship relations
			
			us1.addFriend(us2);
            us1.addFriend(us3);
			us2.addFriend(us1);
			us3.addFriend(us1);
			
			// set the plans 
			
			// fixe les plans pour les utilisateurs  
			 us1.addPlan(plan1);
			 us2.addPlan(plan1);
			 us3.addPlan(plan2);
			// commit the transaction

			// Ready to commit
			
			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
		emf.close();

	}

}
