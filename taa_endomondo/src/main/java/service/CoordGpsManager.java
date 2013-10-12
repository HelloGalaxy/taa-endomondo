package service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.CoordGPS;
import datadao.CoordGpsDao;
import datadao.UserDao;

/*
 * getCoordByID
 * getAllCoords
 * ....
 */

@Path("/gps")
public class CoordGpsManager {
	EntityManagerFactory emf;
	EntityManager em;

	private void beginPersitence() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}

	private void endPersitence() {
		em.close();
		emf.close();
	}

	@GET
	@Path("/getCoordById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public CoordGPS getCoordById(@PathParam("id") int id) {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		CoordGPS coord = dataManager.getCoordGPSById(id);
		endPersitence();

		return coord;

	}

	@GET
	@Path("/getAllCoords")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<CoordGPS> getAllCoords(@PathParam("id") int id) {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		Collection<CoordGPS> coords = dataManager.getAllCoordGPSs();
		endPersitence();

		return coords;

	}

}
