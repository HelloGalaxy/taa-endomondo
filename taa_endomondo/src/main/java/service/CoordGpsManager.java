package service;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.CoordGPS;
import datadao.CoordGpsDao;

/*
 * getCoordByID
 * getAllCoords
 * ....
 */

@Path("/gps")
public class CoordGpsManager extends BasicManager {

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
	public Collection<CoordGPS> getAllCoords() {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		Collection<CoordGPS> coords = dataManager.getAllCoordGPSs();
		endPersitence();
		return coords;
	}

	@POST
	@Path("/createCoord")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCoord(CoordGPS gps) {

		System.err.println(gps);
		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		gps.setId(0);
		dataManager.createCoordGPS(gps);
		endPersitence();
	}

	@PUT
	@Path("/updateCoord")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCoord(CoordGPS gps) {

		System.err.println(gps);
		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		dataManager.updateCoordGPS(gps);
		endPersitence();
	}

}
