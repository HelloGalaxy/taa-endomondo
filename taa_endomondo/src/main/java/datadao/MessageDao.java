package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Message;

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

			String queryString = "select obj from Message as obj";
			Query query = em.createQuery(queryString);
			List<Message> results = query.getResultList();

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
