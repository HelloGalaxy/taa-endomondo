package model;

import javax.persistence.*;

@Entity
public class Weather {
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	// TODO: REF http://developer.yahoo.com/weather/ complet it today or tomorrow 2013-10-07, see more details about the temperature attributes

	private String location;
	private int temperature;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
}
