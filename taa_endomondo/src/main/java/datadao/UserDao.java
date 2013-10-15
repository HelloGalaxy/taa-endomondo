package datadao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.User;

public class UserDao extends DataDao {


	public UserDao(EntityManager entman) {
		super(entman);
	}

	/**
	 * This method return the list of names (first name) of all the users
	 * 
	 * @boussad
	 * */
	public Collection<User> getAllUsers() {

		try {

			String queryString = "SELECT us FROM User  as  us ";
			Query query = em.createQuery(queryString);
			// get the list of all the users
			Collection<User> resluts = query.getResultList();

			return resluts;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
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
	public User getUserById(int iduser) {

		String queryString1 = "SELECT  usr  FROM  User AS usr where usr.id=:idf  ";

		try {
			Query query1 = em.createQuery(queryString1).setParameter("idf",
					iduser);
			User usr = (User) query1.getSingleResult();
			return usr;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	/**
	 * @B Search first name and the last name of the user give the email of the
	 *    user
	 * */

	public User getUserByEmail(String _mail) {

		String queryString1 = "SELECT  usr  FROM  User AS usr where usr.email=:mail  ";

		try {
			Query query1 = em.createQuery(queryString1).setParameter("mail",
					_mail);
			User usr = (User) query1.getSingleResult();
			return usr;

		} catch (Exception e) {
			System.out.println(e.toString());

		}

		return null;
	}

	public boolean create(User obj) {

		tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(obj);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean update(User obj) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			em.merge(obj);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean delete(int id) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			User obj = getUserById(id);

			if (obj == null)
				return false;

			em.remove(obj);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}
}
