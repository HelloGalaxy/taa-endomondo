package model;

import java.util.Collection;
import java.util.Date;

public class User {
	
	private int id;
	
	private String email;

	private String firstname;
	private String lastname;
	private String nickname;
	private Sex sex;
	private Date birthday;
	private float weight;
	private float height;
	private String avatar;
	private String facebook;
	private Date joindate;
	
	Collection<User> friends;
}
