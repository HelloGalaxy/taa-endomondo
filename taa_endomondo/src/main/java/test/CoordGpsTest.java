package test;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.database.CoordGPS;

public class CoordGpsTest {
	
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
				CoordGPS coord = new CoordGPS();
				em.persist(coord);

				coord.setAttitude(random.nextInt(8848));
				coord.setLatitude(random.nextInt(180) - 90);
				coord.setLongitude(random.nextInt(360) - 180);
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
