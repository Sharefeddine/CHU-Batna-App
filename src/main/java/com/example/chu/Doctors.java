package com.example.chu;

public class Doctors extends Employer {
	Integer doctorid;
	String service ;
	String day ;
	String heur ;
	String Type ;
	public Integer getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHeur() {
		return heur;
	}
	public void setHeur(String heur) {
		this.heur = heur;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Doctors(Integer age, String firstname, String lastname, String fullname, String adress, String username,
			String password, Integer doctorid, String service, String day, String heur, String type) {
		super(age, firstname, lastname, fullname, adress, username, password);
		this.doctorid = doctorid;
		this.service = service;
		this.day = day;
		this.heur = heur;
		Type = type;
	}
	
}
