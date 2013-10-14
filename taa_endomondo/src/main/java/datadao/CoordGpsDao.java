package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.CoordGPS;

public class CoordGpsDao {

	EntityManager em;

	public CoordGpsDao(EntityManager em) {
		this.em = em;
	}

	public CoordGPS getCoordGPSById(int id) {

		String queryString = "select gps from CoordGPS as gps where gps.id=:itsID";
		Query query = em.createQuery(queryString).setParameter("itsID", id);
		CoordGPS coord = (CoordGPS) query.getSingleResult();

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
}
