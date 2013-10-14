package datadao;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.database.User;

public class UserDao {

	EntityManager _em;

	public UserDao(EntityManager entman) {
		_em = entman;
	}

	/**
	 * This method return the list of names (first name) of all the users
	 * 
	 * @boussad
	 * */
	public Collection<String> getAllUsers() {

		try {

			String queryString = "SELECT us FROM User  as  us ";
			Query query = _em.createQuery(queryString);
			// get the list of all the users
			ArrayList<User> liusr = (ArrayList<User>) query.getResultList();
			ArrayList<String>  liusrStr= new ArrayList();
			for (int i = 0; i < liusr.size(); i++) {
				User user =  liusr.get(i);
				String onusr = String.valueOf(i)+" : "+user.getFirstname()+" : "+user.getLastname()+" : "+user.getEmail()+" : "+user.getFacebook()+"\n";
			    liusrStr.add(onusr); 	
			}
			
			return liusrStr;
	
		} catch (RuntimeException e) {
			System.out.println("Exception caught while trying to in USERDao");
		}
		return null;
	}

	/**
	 * Give an id of the expected result this method return the first and the
	 * last name if found otherwise an empty response is returned otherwise it
	 * throws an error
	 * 
	 * @author : boussad
	 * */
	public String userById(int iduser) {

		String queryString1 = "SELECT  usr  FROM  User AS usr where usr.id=:idf  ";

		try {
			Query query1 = _em.createQuery(queryString1).setParameter("idf",
					iduser);
			User usr = (User) query1.getSingleResult();
			return (usr.getLastname() + " " + usr.getFirstname());

		} catch (NoResultException notfound) {
			System.out.println("User returened is null  .... ");
			return "";
		}
	}
	
	/**
	 * @B
	 *   Search   first name and the last name of the user give the email of the user 
	 * */
	
	public String userByEmail(String _mail) {

		String queryString1 = "SELECT  usr  FROM  User AS usr where usr.email=:mail  ";

		try {
			Query query1 = _em.createQuery(queryString1).setParameter("mail",
			 		_mail);
			User usr = (User) query1.getSingleResult();
		   	return (usr.getLastname() + " " + usr.getFirstname());

	    	} catch (NoResultException notfound) {
	 		System.out.println("User returened when fatching by mail is null  .... ");
			return "";
		}
		
	}
	
}
