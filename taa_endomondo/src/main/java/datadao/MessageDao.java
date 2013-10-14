package datadao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Message;
import model.database.Plan;
import model.database.User;

public class MessageDao {

	EntityManager em;

	public MessageDao(EntityManager em) {
		this.em = em;
	}

	public Message getMessageById(int id) {

		String queryString = "select obj from Message as obj where obj.id=:itsID";
		Query query = em.createQuery(queryString).setParameter("itsID", id);
		Message model = (Message) query.getSingleResult();

		return model;
	}

	public Collection<Message> getAllMessages() {

		try {

			// join fetch obj.from.friends
			// join fetch obj.from.friends f join fetch obj.from.plans as p
			String queryString = "select obj from Message as obj ";
			Query query = em.createQuery(queryString);
			List<Message> results = query.getResultList();
			
			for (Message m : results){
				m.getFrom().setPlans(new ArrayList<Plan>());
				m.getFrom().setFriends(new ArrayList<User>());
				m.getTo().setPlans(new ArrayList<Plan>());
				m.getTo().setFriends(new ArrayList<User>());
			}
			
			
			
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
}
