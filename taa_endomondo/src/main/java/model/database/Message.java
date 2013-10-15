package model.database;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	// @JoinColumn(name="fromf")
	private User fromwho;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	// @JoinColumn(name="tof")
	private User towho;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromWho() {
		return fromwho;
	}

	public void setFromWho(User fromwho) {
		this.fromwho = fromwho;
	}

	public User getToWho() {
		return towho;
	}

	public void setToWho(User to) {
		this.towho = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}