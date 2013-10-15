package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.CoordGPS;
import model.database.Route;

public class RouteDao extends DataDao {

	public RouteDao(EntityManager em) {
		super(em);
	}

	public Route getRouteById(int id) {

		Route model =  em.find(Route.class, id);
		return model;
	}

	public Collection<Route> getAllRoutes() {

		try {

			String queryString = "select obj from Route as obj";
			Query query = em.createQuery(queryString);

			List<Route> results = query.getResultList();

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}
	
	public boolean create(Route obj) {

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

	public boolean update(Route obj) {

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
			Route obj = getRouteById(id);

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
