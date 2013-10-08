package test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Sex;
import model.User;

public class ModelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    //Use The persistence.xml  configuration 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("endomondo");
		// Retrieve an entity manager
		EntityManager em = emf.createEntityManager();
		// work with entity manager
		
		EntityTransaction tx = null;
		try{
			
			tx = em.getTransaction();
			tx.begin();
		    
			 // Do some stuff 
			  
			//1: create  a first user ...
			
			
			User us1 = new model.User(); 
			us1.setEmail("us1@yahoo.fr");em.persist(us1);
			Date joind = new Date();joind.setDate(9/7/2010);
			Date birth = new Date();birth.setDate(9/7/1985);
			us1.setBirthday(birth); us1.setJoindate(joind);
			us1.setSex(Sex.Male);
			us1.setFacebook("facebook/us1.fr");
			us1.setHeight((float)1.85);
			us1.setNickname("us1NickName");
			us1.setFirstname("Marc"); us1.setLastname("dupon");
			
			//create a second user 
			
			User us2 = new model.User(); 
			us2.setEmail("us2@gmail.fr");em.persist(us2);
			Date joind2 = new Date();joind.setDate(9/7/2011);
			Date birth2 = new Date();birth.setDate(9/7/1984);
			us2.setBirthday(birth); us2.setJoindate(joind);
			us2.setSex(Sex.Male);
			us2.setFacebook("facebook/us2.fr");
			us2.setHeight((float)1.87);
			us2.setNickname("us2NickName");
			us2.setFirstname("Jacky"); us2.setLastname("chen");
			 // commit the transaction
			
			
			
			
			//  Ready to commit 
			tx.commit();
			
			
		
			 		
		}catch (RuntimeException e ){
			
			if(tx!=null && tx.isActive())
			  tx.rollback();
			throw e;
		}finally {
			em.close();
		}
		emf.close();
		
		
	}
	
	

}