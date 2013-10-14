package service;

import java.util.Collection;

import javax.ws.rs.GET;
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
	@Path("/getRouteById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Route getRouteById(@PathParam("id") int id) {

		beginPersitence();
		RouteDao dataManager = new RouteDao(em);
		Route model = dataManager.getRouteById(id);
		endPersitence();

		return model;

	}

	@GET
	@Path("/getAllRoutes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Route> getRoutes(@PathParam("id") int id) {

		beginPersitence();
		RouteDao dataManager = new RouteDao(em);
		Collection<Route> model = dataManager.getAllRoutes();
		endPersitence();

		return model;

	}
}
