package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Message;
import model.database.Route;

public class MessageDao extends DataDao {

	public MessageDao(EntityManager em) {
		super(em);
	}

	public Message getMessageById(int id) {

		//String queryString = "select obj from Message as obj where obj.id=:itsID";
		//Query query = em.createQuery(queryString).setParameter("itsID", id);
		Message model = em.find(Message.class, id);//(Message) query.getSingleResult();

		return model;
	}

	public Collection<Message> getAllMessages() {

		try {

			// join fetch obj.from.friends
			// join fetch obj.from.friends f join fetch obj.from.plans as p
			String queryString = "select obj from Message as obj ";
			Query query = em.createQuery(queryString);
			List<Message> results = query.getResultList();

			// for (Message m : results){
			// m.getFrom().setPlans(new ArrayList<Plan>());
			// m.getFrom().setFriends(new ArrayList<User>());
			// m.getTo().setPlans(new ArrayList<Plan>());
			// m.getTo().setFriends(new ArrayList<User>());
			// }

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public Collection<Message> getMessagesByUserId(int id) {
		String queryString = "select obj from Message as obj where obj.from=:itsID or obj.to=:itsID";
		Query query = em.createQuery(queryString).setParameter("itsID", id);
		Message model = (Message) query.getSingleResult();
		return null;
	}
	
	public boolean create(Message obj) {

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

	public boolean update(Message obj) {

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
			Message obj = getMessageById(id);

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
