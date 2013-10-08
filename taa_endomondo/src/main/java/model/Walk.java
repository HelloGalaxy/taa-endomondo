package model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Walk {
   
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany
	private Collection<CoordGPS> coordListGps;
	
	public Walk(){
		coordListGps =  new LinkedList<CoordGPS>();	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<CoordGPS> getCoordListGps() {
		return coordListGps;
	}
	public void setCoordListGps(Collection<CoordGPS> coordListGps) {
		this.coordListGps = coordListGps;
	}
	
}
