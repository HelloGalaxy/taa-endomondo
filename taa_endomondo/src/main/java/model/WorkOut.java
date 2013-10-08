package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class WorkOut {
   
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	@Temporal(TemporalType.DATE)
	private Date startTime;
	@Temporal(TemporalType.DATE)
    private Date endTime;
    private String note;
    @OneToOne
    private Walk walk;
    @OneToOne
    private Weather weather;
    
    private float avgHeartRate;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Walk getWalk() {
		return walk;
	}
	public void setWalk(Walk walk) {
		this.walk = walk;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public float getAvgHeartRate() {
		return avgHeartRate;
	}
	public void setAvgHeartRate(float avgHeartRate) {
		this.avgHeartRate = avgHeartRate;
	}
    
    
}
