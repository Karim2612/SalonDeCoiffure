package com.coiffure.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private String message = "";
	private List<Rdv> rdv = new ArrayList<Rdv>();
	private List<Servise> servise = new ArrayList<Servise>();
	private List<User> useres = new ArrayList<User>();
	private String error = "";

	// Message, Error and OneToMany
	
	public String getMessage() { return this.message; }
	public void setMessage(String message) { this.message = message; }
	
	public String getError() { return this.error; }
	public void setError(String error) { this.error = error; }
	
	public List<User> getUsers() {
		return this.useres;
	}public void setUsers(ArrayList<User> users) {
		this.useres = users;
	}
	

	public List<Rdv> getRdv() { return rdv; }
	public void setRdv(List<Rdv> rdv) { this.rdv = rdv; }

    public List<Servise> getServise() { return servise; }
	public void setServise(List<Servise> servise) { this.servise = servise; }

	// Tables
	
	public List<User> getUseres() {
		return useres;
	}

	public void setUseres(List<User> useres) {
		this.useres = useres;
	}


	// Constructors
	
	public Message(String message,  List<User> useres,List<Rdv> rdv, List<Servise> servise,  String error) {
		this.message = message;
		this.rdv = rdv;
		this.servise = servise;
		this.useres = useres;
		this.error = error;
	}
	
	// public Message(String message, List<Rdv> rdv, List<Servise> servise, List<User> useres, String error) {
	// 	this.message = message;
	// 	this.rdv = rdv;
	// 	this.servise = servise;
	// 	this.useres = useres;
	// 	this.error = error;
	// }

}
