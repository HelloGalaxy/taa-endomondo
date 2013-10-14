package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.Weather;

public class WeatherDao {

	EntityManager em;

	public WeatherDao(EntityManager em) {
		this.em = em;
	}

	public Weather getWeatherById(int id) {

		String queryString = "select obj from Weather as obj where obj.id=:itsID";
		Query query = em.createQuery(queryString).setParameter("itsID", id);
		Weather model = (Weather) query.getSingleResult();

		return model;
	}

	public Collection<Weather> getAllWeathers() {

		try {

			String queryString = "select obj from Weather as obj";
			Query query = em.createQuery(queryString);
			List<Weather> results = query.getResultList();

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public Collection<Weather> getWheathersByCity(String city) {

		try {

			String queryString = "select obj from Weather as obj where obj.city like '%"
					+ city + "%'";
			Query query = em.createQuery(queryString);
			//query.setParameter(0, city);
			List<Weather> results = query.getResultList();

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}
}
