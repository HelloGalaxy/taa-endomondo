package model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("users")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String note;
	@OneToOne(cascade = CascadeType.ALL)
	private Route route;
	@OneToOne
	private Weather weather;
	@Enumerated(EnumType.STRING)
	private SportType sportType;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "plans")
	
	private Collection<User> users;

	private float avgHeartRate;

	public Plan(){
		users = new ArrayList<User>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public SportType getSportType() {
		return sportType;
	}

	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}

	public float getAvgHeartRate() {
		return avgHeartRate;
	}

	public void setAvgHeartRate(float avgHeartRate) {
		this.avgHeartRate = avgHeartRate;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void addUser(User us){
		this.users.add(us);
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}
