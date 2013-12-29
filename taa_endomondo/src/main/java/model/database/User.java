package model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String firstname;
	private String lastname;
	private String nickname;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private float weight;
	private float height;
	private String avatar;
	private String facebook;
	@Temporal(TemporalType.DATE)
	private Date joindate;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			   name = "friends")
	@JsonIgnore
	private Collection<User> friends ; //= new ArrayList<User>();
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Collection<Plan> plans ;//= new ArrayList<Plan>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
		friends = new ArrayList<User>();
		plans = new ArrayList<Plan>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Collection<User> getFriends() {
		return friends;
	}
	
    public void addFriend(User friend){
	   this.friends.add(friend);
    }
    
	public void setFriends(Collection<User> friends) {
		this.friends = friends;
	}

	public Collection<Plan> getPlans() {
		return plans;
	}
	
    public void addPlan(Plan p){
    	this.plans.add(p);
    }
    
	public void setPlans(Collection<Plan> plans) {
		this.plans = plans;
	}

}
