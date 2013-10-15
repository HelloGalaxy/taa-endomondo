package datadao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.database.CoordGPS;
import model.database.Route;
import model.database.Weather;

public class WeatherDao extends DataDao {

	public WeatherDao(EntityManager em) {
		super(em);
	}

	public Weather getWeatherById(int id) {

		Weather model = em.find(Weather.class, id);

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
			List<Weather> results = query.getResultList();

			return results;
		} catch (RuntimeException e) {
			System.out.println(e.toString());
		}

		return null;
	}
	
	public boolean create(Weather obj) {

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

	public boolean update(Weather obj) {

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
			Weather obj = getWeatherById(id);

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
