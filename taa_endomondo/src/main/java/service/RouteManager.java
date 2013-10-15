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

import model.database.Route;
import datadao.RouteDao;

/*
 * getRouteById
 * getAllRoutes
 * 
 * 
 */
@Path("/route")
public class RouteManager extends BasicManager {

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Route getRouteById(@PathParam("id") int id) {

		RouteDao dataManager = new RouteDao(em);
		Route model = dataManager.getRouteById(id);


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
