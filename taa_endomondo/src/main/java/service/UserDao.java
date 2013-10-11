package service;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.database.User;


public class UserDao {

	EntityManager _em; 
	
	UserDao (EntityManager entman){
		_em=entman;
	}
	/**
	 * This  method return the list of names (first name) of  all the users
	 * @boussad
	 * */
	public Collection<String> getAllUsers(){
	
		try{

			String queryString = "SELECT firstname  FROM  User ";
			 Query query = _em.createQuery(queryString);
			// get the list of all the users
			 ArrayList<String> li = (ArrayList<String>) query.getResultList();
			for(int i=0; i< li.size(); i++){
				System.out.println (li.get(i));
			}	 			
			return li;	
		}catch (RuntimeException e ){
		   System.out.println("Exception caught while trying to in USERDao");
		}
		return null;
	}
	
	/** Give an id of the expected result this method return the first and the last name 
	 * if found otherwise an empty response is returned 
	 * otherwise it throws an error
	 * 
	 * @author : boussad
	 * */	
	public String userById(int iduser ){
		
		 String queryString1 = "SELECT  usr  FROM  User AS usr where usr.id=:idf  ";
		 
		 try{
		     Query query1 = _em.createQuery(queryString1).setParameter("idf",iduser);
		     User usr = (User) query1.getSingleResult();
		     return (usr.getLastname()+" "+usr.getFirstname());
		     
		 }catch(NoResultException notfound){
			 System.out.println("User returened is null  .... ");
			 return "";
		 }
	}	
}
