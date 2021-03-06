package service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import model.database.Route;
import datadao.CoordGpsDao;
import datadao.RouteDao;

/*
 * getRouteById
 * getAllRoutes
 * 
 * 
 */
@Path("/route")
public class RouteManager {
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public RouteManager() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Route getRouteById(@PathParam("id") int id) {

		RouteDao dataManager = new RouteDao(em);
		Route model = dataManager.getRouteById(id);
        if(model==null)  return null;
		return model;
	}

	@GET
	@Path("/getAllRoutes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Route> getRoutes(@PathParam("id") int id) {

		RouteDao dataManager = new RouteDao(em);
		Collection<Route> model = dataManager.getAllRoutes();

		return model;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createCoord(Route obj) {

		RouteDao dataManager = new RouteDao(em);
		obj.setId(0);
		boolean result = dataManager.create(obj);

		return result;
	}

	/* Ajouter une coordonnée pour cette route , identifiée par id */
	
	@POST
	@Path("{id}/addCoord")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean addCoordinateToRoute(CoordGPS obj,@PathParam("id") int id ) {

		RouteDao dataManager = new RouteDao(em);
		Route Rt = dataManager.getRouteById(id);
		//Collection<CoordGPS> newCoords = new ArrayList<CoordGPS>();
		//newCoords.add(obj);
		obj.setRoute(Rt);
		//Rt.setCoordListGps(newCoords);
		Rt.addCoordinate(obj);
		//boolean result = dataManager.create(Rt);
		return dataManager.update(Rt);
	}
	
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateCoord(Route obj) {

		RouteDao dataManager = new RouteDao(em);
		boolean result = dataManager.update(obj);

		return result;
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteCoord(@PathParam("id") int id) {
		RouteDao dataManager = new RouteDao(em);
		boolean result = dataManager.delete(id);
		return result;
	}
}
