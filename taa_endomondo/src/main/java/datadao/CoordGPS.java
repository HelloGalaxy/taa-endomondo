package datadao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class CoordGPS {
	
	EntityManager em;
	
	public Collection<String> getAllUsers() {

		try {

			String queryString = "SELECT * FROM  User ";
			Query query = em.createQuery(queryString);

			List<String> li = query.getResultList();
			for (int i = 0; i < li.size(); i++) {
				System.out.println(li.get(i));
			}
			return li;
		} catch (RuntimeException e) {
			System.out.println("Exception caught while trying to in USERDao");
		}
		return null;
	}
}
