package test;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.database.Message;
import model.database.User;
import datadao.UserDao;

public class MessageTest {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("endomondo");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {

			tx = em.getTransaction();
			tx.begin();

			int count = 10;

			Random random = new Random();

			for (int i = 0; i < count; i++) {
				Message model = new Message();
				em.persist(model);

				model.setContent(random.nextInt(10) > 5 ? "Hello" : "Bonjour");
				UserDao dao = new UserDao(em);

				String queryString = "SELECT obj  FROM  User obj ";
				Query query = em.createQuery(queryString);
				List<User> us = query.getResultList();

				model.setFromWho(us.get(random.nextInt(us.size())));
				model.setToWho(us.get(random.nextInt(us.size())));
			}

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
