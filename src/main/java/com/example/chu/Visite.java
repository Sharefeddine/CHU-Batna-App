package com.example.chu;
import java.util.Date;
public class Visite   {
int visite_id ;
String patient;
String Type ;
String service ;
String Doctor ;
String status ;
Date Entrey_date ;
public int getVisite_id() {
	return visite_id;
}
public void setVisite_id(int visite_id) {
	this.visite_id = visite_id;
}
public String getPatient() {
	return patient;
}
public void setPatient(String patient) {
	this.patient = patient;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public String getService() {
	return service;
}
public void setService(String service) {
	this.service = service;
}
public String getDoctor() {
	return Doctor;
}
public void setDoctor(String doctor) {
	Doctor = doctor;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getEntrey_date() {
	return Entrey_date;
}
public void setEntrey_date(Date entrey_date) {
	Entrey_date = entrey_date;
}
public Visite(int visite_id, String patient, String type, String service, String doctor, String status,
		Date entrey_date) {
	super();
	this.visite_id = visite_id;
	this.patient = patient;
	Type = type;
	this.service = service;
	Doctor = doctor;
	this.status = status;
	Entrey_date = entrey_date;
}


}
