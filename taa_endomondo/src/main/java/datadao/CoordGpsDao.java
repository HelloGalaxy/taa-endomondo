package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.CoordGPS;

public class CoordGpsDao extends DataDao {

	public CoordGpsDao(EntityManager em) {
		super(em);
	}

	public CoordGPS getCoordGPSById(int id) {
		
		CoordGPS coord = em.find(CoordGPS.class, id);

		return coord;
	}

	public Collection<CoordGPS> getAllCoordGPSs() {

		try {

			String queryString = "select crd from  CoordGPS as crd";
			Query query = em.createQuery(queryString);

			List<CoordGPS> results = query.getResultList();

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public int createCoordGPS(CoordGPS gps) {

		tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(gps);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return gps.getId();
		}

		return gps.getId();
	}

	public boolean updateCoordGPS(CoordGPS gps) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			em.merge(gps);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean deleteCoordGPS(int id) {

		tx = null;
		try {

			tx = em.getTransaction();
			tx.begin();
			CoordGPS gps = getCoordGPSById(id);

			if (gps == null)
				return false;

			em.remove(gps);
			tx.commit();
			tx = null;

		} catch (RuntimeException e) {
			System.out.println(e.toString());
			return false;
		}

		return true;
	}
}
