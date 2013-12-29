package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import datadao.UserDao;

import model.database.CoordGPS;
import model.database.Plan;
import model.database.Route;
import model.database.Sex;
import model.database.SportType;
import model.database.User;

public class PlanTest {

	static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("endomondo");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) throws Exception {

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			// creation of plan
			Plan plan1 = new Plan();
			em.persist(plan1);
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			plan1.setAvgHeartRate(0);
			plan1.setEndDate(sdf.parse("01/10/2013 12:48:45"));
			plan1.setNote("Running in the centre place");
			
			Route r = new Route();
			em.persist(r);
			r.setCoordListGps(createCoordGPS());
			plan1.setRoute(r);
			
			plan1.setStartDate(sdf.parse("01/10/2013 10:48:45"));
			plan1.setTitle("Test1...");
			plan1.setSportType(SportType.Running);

//			User us1 = new model.database.User();
//			us1.setEmail("us1@yahoo.fr");
//			em.persist(us1);
//			Date joind = new Date();
//			joind.setDate(9 / 7 / 2010);
//			Date birth = new Date();
//			birth.setDate(9 / 7 / 1985);
//			us1.setBirthday(birth);
//			us1.setJoindate(joind);
//			us1.setSex(Sex.Male);
//			us1.setFacebook("facebook/us1.fr");
//			us1.setHeight((float) 1.85);
//			us1.setNickname("us1NickName");
//			us1.setFirstname("Marc");
//			us1.setLastname("dupon");
			
			User  u = new User();
			u.setFirstname("TOTO");
			Set<User>  lisUser = new HashSet<User>();  
			lisUser.add(u);
			plan1.setUsers(lisUser);
            u.addPlan(plan1);
			
			
			tx.commit();
		} catch (Exception e) {

			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
		emf.close();

	}

	private static Collection<CoordGPS> createCoordGPS() {

		int count = 2;

		Random random = new Random();
		Collection<CoordGPS> Coords = new LinkedList<CoordGPS>();
		for (int i = 0; i < count; i++) {
			CoordGPS coord = new CoordGPS();
			em.persist(coord);

			coord.setAttitude(random.nextInt(8848));
			coord.setLatitude(random.nextInt(180) - 90);
			coord.setLongitude(random.nextInt(360) - 180);
			Coords.add(coord);
		}
		return Coords;
	}

}
