package service;

import java.util.Collection;

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
public class CoordGpsManager  {

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public CoordGPS getCoordById(@PathParam("id") int id) {


		CoordGpsDao dataManager = new CoordGpsDao(BasicManager.em);
		CoordGPS coord = dataManager.getCoordGPSById(id);

		return coord;

	}

	@GET
	@Path("/getAllCoords")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<CoordGPS> getAllCoords() {


		CoordGpsDao dataManager = new CoordGpsDao(BasicManager.em);
		Collection<CoordGPS> coords = dataManager.getAllCoordGPSs();

		return coords;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createCoord(CoordGPS gps) {

	
		CoordGpsDao dataManager = new CoordGpsDao(BasicManager.em);
		gps.setId(0);
		boolean result = dataManager.createCoordGPS(gps);

		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateCoord(CoordGPS gps) {


		CoordGpsDao dataManager = new CoordGpsDao(BasicManager.em);
		boolean result = dataManager.updateCoordGPS(gps);

		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteCoord(@PathParam("id") int id) {


		CoordGpsDao dataManager = new CoordGpsDao(BasicManager.em);
		boolean result = dataManager.deleteCoordGPS(id);


		return result;
	}

}
