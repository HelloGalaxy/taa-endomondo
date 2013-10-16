package model.database;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<CoordGPS> coordGpsList;

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

	public void setCoordListGps(Collection<CoordGPS> coordGpsList) {
		this.coordGpsList = coordGpsList;
	}

}
