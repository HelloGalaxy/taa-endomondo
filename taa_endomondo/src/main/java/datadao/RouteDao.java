package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Route;

public class RouteDao {
	EntityManager em;

	public RouteDao(EntityManager em) {
		this.em = em;
	}

	public Route getRouteById(int id) {

		String queryString = "select obj from Route as obj where obj.id=:itsID";
		Query query = em.createQuery(queryString).setParameter("itsID", id);
		Route model = (Route) query.getSingleResult();

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
}
