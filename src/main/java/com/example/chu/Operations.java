package com.example.chu;
import java.util.Date;
public class Operations extends Visite {
	int operation_id;
	String opeations_heur ;
	Date operation_date ;
	String operation_room ;
	public String getOperation_doctor() {
		return operation_doctor;
	}
	public void setOperation_doctor(String operation_doctor) {
		this.operation_doctor = operation_doctor;
	}
	String operation_doctor ;
	public int getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}
	public String getOpeations_heur() {
		return opeations_heur;
	}
	public void setOpeations_heur(String opeations_heur) {
		this.opeations_heur = opeations_heur;
	}
	public Date getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(Date operation_date) {
		this.operation_date = operation_date;
	}
	public String getOperation_room() {
		return operation_room;
	}
	public void setOperation_room(String operation_room) {
		this.operation_room = operation_room;
	}
	public Operations(int visite_id, String patient, String type, String service, String doctor, String status,
			Date entrey_date, int operation_id, String opeations_heur, Date operation_date, String operation_room,
			String operation_doctor) {
		super(visite_id, patient, type, service, doctor, status, entrey_date);
		this.operation_id = operation_id;
		this.opeations_heur = opeations_heur;
		this.operation_date = operation_date;
		this.operation_room = operation_room;
		this.operation_doctor = operation_doctor;
	}

	
}
