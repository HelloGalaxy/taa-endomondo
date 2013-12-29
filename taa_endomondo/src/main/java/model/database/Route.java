package model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="route" ,orphanRemoval = true)
	
	@JsonManagedReference("route") private Collection<CoordGPS> coordGpsList;

	public Route() {
		coordGpsList = new LinkedList<CoordGPS>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<CoordGPS> getCoordGpsList() {
		return coordGpsList;
	}

	public void addCoordinate(CoordGPS coord){
		this.coordGpsList.add(coord);
		
	}
	public void setCoordListGps(Collection<CoordGPS> coordGpsList) {
		
		for(CoordGPS cord:coordGpsList ){
			this.coordGpsList.add(cord);	
		}
		//this.coordGpsList = coordGpsList;
	}

}
