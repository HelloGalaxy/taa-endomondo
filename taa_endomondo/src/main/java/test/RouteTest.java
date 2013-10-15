package test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.database.CoordGPS;
import model.database.Route;
import datadao.CoordGpsDao;

public class RouteTest {
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
				Route model = new Route();
				em.persist(model);

				
				// We should create a new object, if not, it can not be persisted in DB
//				CoordGpsDao dataManager = new CoordGpsDao(em);
//				List<CoordGPS> coords = (List<CoordGPS>) dataManager
//						.getAllCoordGPSs();
//
//				if (coords.size() > 10) {
//					for (int j = 0; j < 10; j++) {
//						Collection<CoordGPS> newCoords = new LinkedList<CoordGPS>();
//						newCoords.add(coords.get(random.nextInt(coords.size())));
//						model.setCoordListGps(newCoords);
//					}
//				} else {
//					model.setCoordListGps(coords);
//				}
				
				Collection<CoordGPS> newCoords = new LinkedList<CoordGPS>();
				for (int j = 0; j < 10; j++) {				
					
					CoordGPS coord = new CoordGPS();
					em.persist(coord);

					coord.setAttitude(random.nextInt(8848));
					coord.setLatitude(random.nextInt(180) - 90);
					coord.setLongitude(random.nextInt(360) - 180);
					
					newCoords.add(coord);
				}
				
				
				model.setCoordListGps(newCoords);
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null && tx.isActive())
				tx.rollback();
			System.out.println(e);
		} finally {
			em.close();
		}
		emf.close();

	}
}
