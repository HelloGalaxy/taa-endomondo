package service;

import java.util.Collection;

import javax.swing.text.StyledEditorKit.BoldAction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	@Path("{id}")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createCoord(CoordGPS gps) {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		gps.setId(0);
		boolean result = dataManager.createCoordGPS(gps);
		endPersitence();
		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateCoord(CoordGPS gps) {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		boolean result = dataManager.updateCoordGPS(gps);
		endPersitence();
		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteCoord(@PathParam("id") int id) {

		beginPersitence();
		CoordGpsDao dataManager = new CoordGpsDao(em);
		boolean result = dataManager.deleteCoordGPS(id);
		endPersitence();

		return result;
	}

}
