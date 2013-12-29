package model.database;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class CoordGPS implements Serializable {

	private static final long serialVersionUID = -4769391454179097556L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double longitude;
	private double latitude;
	private double attitude;

	// @OneToOne
	// @JsonBackReference
	// private CoordGPS coordGPS;
	
	@ManyToOne (cascade = CascadeType.ALL)
	
	@JsonBackReference private Route route;
	
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

	public void setRoute(Route route){
		this.route=route;
	}
	// public CoordGPS getCoordGPS() {
	// return coordGPS;
	// }
	//
	// public void setCoordGPS(CoordGPS coordGPS) {
	// this.coordGPS = coordGPS;
	// }

}
