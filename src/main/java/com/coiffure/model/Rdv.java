package com.coiffure.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.List;
import java.util.List;


@Entity
@Table(name = "rdv")
public class Rdv {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date date_heur_rdv;

	@Column(columnDefinition = "varchar(1000)")
	private String description_rdv;

	@Column(nullable = false)
	private Date date_creation_rdv;

	
	// Relations
	
	//@JsonManagedReference
	//@JsonBackReference
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "servise_id")
	//private Servise servise;

	
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_id")
	// private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("serviseId")
	@JoinColumn(name = "servise_id")
	 Servise servise;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;


	// public Servise getServise() {return servise;}
	// public void setServise(Servise servise) {this.servise = servise;}

	// public User getUser() {return user;}
	// public void setUser(User user) {this.user = user;}
	

	// Constructors

	public Rdv() {
	}

	public Rdv(Long id, String description_rdv, Date date_heur_rdv,
			Date date_creation_rdv,
			 User user) {

		this.id = id;
		this.date_heur_rdv = date_heur_rdv;
		this.description_rdv = description_rdv;
		this.date_creation_rdv = date_creation_rdv;
		this.user = user;
		
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_heur_rdv() {
		return date_heur_rdv;
	}

	public void setDate_heur_rdv(Date date_heur_rdv) {
		this.date_heur_rdv = date_heur_rdv;
	}

	public String getDescription_rdv() {
		return description_rdv;
	}

	public void setDescription_rdv(String description_rdv) {
		this.description_rdv = description_rdv;
	}

	public Date getDate_creation_rdv() {
		return date_creation_rdv;
	}

	public void setDate_creation_rdv(Date date_creation_rdv) {
		this.date_creation_rdv = date_creation_rdv;
	}

	@Override
	public String toString() {
		return "Rdv " +
				"[id=" + id +
				", description_rdv=" + description_rdv +
				", date_heur_rdv=" + date_heur_rdv +
				", date_creation_rdv=" + date_creation_rdv +
				"]";
	}
}
