package model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class CoordGPS {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double longitude;
	private double latitude;
	private double attitude;
	
	@OneToOne
	@JsonBackReference
	private CoordGPS coordGPS;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAttitude() {
		return attitude;
	}

	public void setAttitude(double attitude) {
		this.attitude = attitude;
	}

	public CoordGPS getCoordGPS() {
		return coordGPS;
	}

	public void setCoordGPS(CoordGPS coordGPS) {
		this.coordGPS = coordGPS;
	}
	
	

}
