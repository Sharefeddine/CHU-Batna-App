package com.example.chu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class functions {
	String username ;
public static String Today () {
	Calendar calendar =  Calendar.getInstance();
	Date day =calendar.getTime();
String Day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(day.getTime());
return Day;
}
public static String get_total_patients () {
    Connect connectnow = new Connect();
    Connection connectDB = connectnow.getConnection();
	int nombre=0 ;
	String query = "Select * from patient";	  
	try {
		Statement statement = connectDB.createStatement();
		 ResultSet resultSet = statement.executeQuery(query);
		 while(resultSet.next()) {
				nombre++;
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	String nombre_total = String.valueOf(nombre);
	return nombre_total;
}
public static String getTotalDoctors () {
	Integer totaldoctors =0 ;
	Connect connectnow = new Connect();
    Connection connectDB = connectnow.getConnection();
    String query = "SELECT * FROM doctors " ;
    try {
		ResultSet rs = connectDB.createStatement().executeQuery(query);
		while(rs.next()) {
			totaldoctors++ ;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return totaldoctors.toString();
}
public static String getVisite_agent() {
	Integer visitetotal = 0  ;
	Connect connectnow = new Connect();
    Connection connectDB = connectnow.getConnection();
    String date =  java.time.LocalDate.now().toString();
    String query = "SELECT * FROM visite where status='waiting' and EntreyDate='"+date+"' " ;
    try {
		ResultSet rs = connectDB.createStatement().executeQuery(query);
		while(rs.next()) {
			visitetotal++ ;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return visitetotal.toString() ;
}
public static String getTotalRooms_Admin(String type) {
	 Integer rooms = 0 ;
		Connect connectnow = new Connect();
	    Connection connectDB = connectnow.getConnection();
	    String query = "SELECT * FROM room where type='"+type+"' " ;
	    try {
			ResultSet rs = connectDB.createStatement().executeQuery(query);
			while(rs.next()) {
				rooms++ ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 return rooms.toString();
}
public static String getOccupied_Rooms_Admin(String type) {
	 Integer rooms = 0 ;
		Connect connectnow = new Connect();
	    Connection connectDB = connectnow.getConnection();
	    String query = "SELECT * FROM room where type='"+type+"' and status='used' " ;
	    try {
			ResultSet rs = connectDB.createStatement().executeQuery(query);
			while(rs.next()) {
				rooms++ ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 return rooms.toString();
}
public static String getOccupied_OBRooms_Admin() {
	 Integer rooms = 0 ;
		Connect connectnow = new Connect();
	    Connection connectDB = connectnow.getConnection();
	    String date =  java.time.LocalDate.now().toString();
	    String query = "SELECT * FROM room , operations where room.room=operations.room and operations.date='"+date+"' " ;
	    try {
			ResultSet rs = connectDB.createStatement().executeQuery(query);
			while(rs.next()) {
				rooms++ ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 return rooms.toString();
}
public static String getWaitingPatients_Doctor() {
	Connect connectnow = new Connect();
    Connection connectDB = connectnow.getConnection();
    String date =  java.time.LocalDate.now().toString();
    Integer patienttotal = 0 ;
   String CurrentDoctor = DoctorSession.getDoctorname();
    String query = "SELECT * FROM visite where status='waiting' and Doctor='"+CurrentDoctor+"' and EntreyDate='"+date+"' " ;
    try {
		ResultSet rs = connectDB.createStatement().executeQuery(query);
		while(rs.next()) {
			patienttotal++ ;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
  String patient  =  patienttotal.toString() ;
	return patient  ; 
}
public  static String getHostabiledPatients_Doctor() {
	Connect connectnow = new Connect();
    Connection connectDB = connectnow.getConnection();
    Integer patienttotal = 0 ;
   String CurrentDoctor = DoctorSession.getDoctorname();
    String query = "SELECT * FROM visite,hostablied_patients where visite.status='Hostabiled Patient ' and visite.Doctor='"+CurrentDoctor+"' and visite.visite_id=hostablied_patients.visite_id" ;
    try {
		ResultSet rs = connectDB.createStatement().executeQuery(query);
		while(rs.next()) {
			patienttotal++ ;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
  String patient  =  patienttotal.toString() ;
	return patient  ; 
}
}
