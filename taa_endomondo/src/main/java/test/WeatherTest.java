package test;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.database.Weather;

public class WeatherTest {
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
				Weather model = new Weather();
				em.persist(model);
				model.setCity("FF " + i + " city");

				model.setDirectionOfWind("East");
				model.setHumidity(random.nextInt(50));
				model.setPressure(random.nextInt(50) + 400);
				model.setSpeedOfWind(random.nextInt(100));

				String state = "";

				int index = random.nextInt(3);

				switch (index) {
				case 0:
					state = "Sunny";
					break;
				case 1:
					state = "Cloudy";
					break;
				case 2:
					state = "Rainy";
					break;
				}

				model.setStateOfWtr(state);
				model.setTemperature(random.nextInt(40));
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
