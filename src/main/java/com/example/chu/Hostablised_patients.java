package com.example.chu;

import java.util.Date;

public class Hostablised_patients extends Visite {
	int hostablied_id;
	String room ;
	Date Exite_date;
	public int getHostablied_id() {
		return hostablied_id;
	}
	public void setHostablied_id(int hostablied_id) {
		this.hostablied_id = hostablied_id;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Date getExite_date() {
		return Exite_date;
	}
	public void setExite_date(Date exite_date) {
		Exite_date = exite_date;
	}
	public Hostablised_patients(int visite_id , String patient, String type, String service, String doctor,
			String status, Date Entrey_date, int hostablied_id, String room, Date exite_date) {
		super(visite_id, patient, type, service, doctor, status, Entrey_date);
		this.hostablied_id = hostablied_id;
		this.room = room;
		Exite_date = exite_date;
	}
	
}
