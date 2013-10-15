package service;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.Weather;
import datadao.WeatherDao;

/*
 * getWheatherById
 * getAllWheathers
 * getWheathersByCity
 * C - U - D
 */

@Path("/weather")
public class WeatherManager extends BasicManager {

	@GET
	@Path("/getWeatherById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Weather getWeatherById(@PathParam("id") int id) {


		WeatherDao dataManager = new WeatherDao(em);
		Weather model = dataManager.getWeatherById(id);


		return model;

	}

	@GET
	@Path("/getAllWeathers")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Weather> getWeathers(@PathParam("id") int id) {

	
		WeatherDao dataManager = new WeatherDao(em);
		Collection<Weather> models = dataManager.getAllWeathers();


		return models;

	}

	@GET
	@Path("/getWeathersByCity/{city}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Weather> getWheathersByCity(@PathParam("city") String city) {


		WeatherDao dataManager = new WeatherDao(em);
		Collection<Weather> models = dataManager.getWheathersByCity(city);


		return models;

	}
}
