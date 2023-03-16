package com.coiffure.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "servise")
public class Servise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nom_servise")
	private String nom_servise;

	@Column(name = "prix_servise")
	private Float prix_servise;

	@Column(columnDefinition = "varchar(1000)")
	private String description_servise;

	@Column(name = "date_creation_servise")
	private Date date_creation_servise;



	// Relations

	//@JsonBackReference
	//@JsonManagedReference
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "servise")
	// private List<Rdv> rdvs;

	@OneToMany(mappedBy = "servise")
	Set<Rdv> rdvs;

	// Constructors

	public Servise() {
	}

	public Servise(Long id, String description_servise, String nom_servise, Float prix_servise,
			Date date_creation_servise) {

		this.id = id;
		this.nom_servise = nom_servise;
		this.prix_servise = prix_servise;
		this.description_servise = description_servise;
		this.date_creation_servise = date_creation_servise;

		
		
	}

	// Getters and Setters

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom_servise() {
		return nom_servise;
	}

	public void setNom_servise(String nom_servise) {
		this.nom_servise = nom_servise;
	}

	public Float getPrix_servise() {
		return prix_servise;
	}

	public void setPrix_servise(Float prix_servise) {
		this.prix_servise = prix_servise;
	}

	public String getDescription_servise() {
		return description_servise;
	}

	public void setDescription_servise(String description_servise) {
		this.description_servise = description_servise;
	}

	public Date getDate_creation_servise() {
		return date_creation_servise;
	}

	public void setDate_creation_servise(Date date_creation_servise) {
		this.date_creation_servise = date_creation_servise;
	}

	@Override
	public String toString() {
		return "Servise " +
				"[id=" + id +
				", description_servise=" + description_servise +
				", nom_servise=" + nom_servise +
				", prix_servise=" + prix_servise +
				", date_creation_servise=" + date_creation_servise +
				"]";
	}
}
