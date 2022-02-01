package com.example.chu;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import np.com.ngopal.control.AutoFillTextBox;

public class edite_visite_doctor_controle implements Initializable {
	 Connect connectnow = new Connect();
	Connection connectDB = connectnow.getConnection(); 
	String Doctor ;
	String medical_suppley_string  ;
	String lab_string ;
	String hopitale_room_string , nombre_days_string ;
	String x_ray_string ;
	String operation_date_string ,operation_heur_string , operation_room_string;
	String Doctors_burden_string = "200";
	int doctor_int = 200  , medical_int , lab_int , x_ray_int , nombre_price_int , opearation_cost_int = 2000;
@FXML
AutoFillTextBox<String> patient ;
ObservableList<String> patient_liste = FXCollections.observableArrayList();
// Status
@FXML
CheckBox waiting ;
@FXML
CheckBox compelted ;
// medical_suppley
@FXML
Label medical_label ;
@FXML
CheckBox medical_suppley_yes ;
@FXML
CheckBox medical_suppley_no ;
@FXML
TextField medical_suppley_price ;
// Lab test 
@FXML
Label lab_label ;
@FXML
CheckBox lab_yes ;
@FXML
CheckBox lab_no ;
@FXML
TextField lab_price ;
//Hostabiled
@FXML
Label h_status_label;
@FXML
Label hostablied_label ;
@FXML
CheckBox h_yes ;
@FXML
CheckBox h_no ;
@FXML
Label h_room_label ;
@FXML
ComboBox<String> h_room ;
ObservableList<String> h_room_liste = FXCollections.observableArrayList();
@FXML
Label nombre_label ;
@FXML
TextField nombre ;
@FXML
Label noroom ;
@FXML
CheckBox r_yes ;
@FXML
CheckBox r_no ;
// Opeartions
@FXML
Label Surgeon_label ;
@FXML
ComboBox Surgeon_doctor ;
ObservableList<String> s_doctor_liste = FXCollections.observableArrayList();
@FXML
Label o_status_label;
@FXML
Label o_label ;
@FXML
CheckBox o_yes ;
@FXML
CheckBox o_no ;
@FXML 
Label date_label ;
@FXML
DatePicker o_date ;
@FXML
Label heur_label ;
@FXML
TextField o_heur;
@FXML
Label o_room_label ;
@FXML
ComboBox<String> o_room;
ObservableList<String> o_room_liste = FXCollections.observableArrayList();
// x_ray
@FXML
Label x_ray_label;
@FXML
CheckBox x_ray_yes ;
@FXML
CheckBox x_ray_no ;
@Override
public void initialize (URL location , ResourceBundle resources ) {
	 Connect connectnow = new Connect();
     Connection connectDB = connectnow.getConnection();	
     getPatient();
     try {
			Statement ps  ;
			  String Query1 = "SELECT * from room where type='operation' and status='not_used' ";
			 ps =  connectDB.prepareStatement(Query1);
			ResultSet rs = ps.executeQuery(Query1);
			while(rs.next()) {
				o_room_liste.add(rs.getString("room_name"));
			}
			o_room.setItems(o_room_liste);	
		} catch (SQLException e) {
			System.out.print(false);
			e.printStackTrace();
		}	
     try {
			Statement ps  ;
			String Query1 = "SELECT * from room where type='hostablied' and status='not_used' ";
			 ps =  connectDB.prepareStatement(Query1);
			ResultSet rs = ps.executeQuery(Query1);
			while(rs.next()) {
				h_room_liste.add(rs.getString("room_name"));
			}
			h_room.setItems(h_room_liste);	
		} catch (SQLException e) {
			System.out.print(false);
			e.printStackTrace();
		}	
     getDoctors();
}
public void getPatient() {
	String CurrentDoctor = DoctorSession.getDoctorname();
	 try {
			Statement ps  ;
			  String Query1 = "SELECT * from visite where Doctor='"+CurrentDoctor+"' and status!='completed'";
			 ps =  connectDB.prepareStatement(Query1);
			ResultSet rs = ps.executeQuery(Query1);
			while(rs.next()) {
				patient_liste.add(rs.getString("patient"));
			}
			patient.setData(patient_liste);
	          patient.setListLimit(10);
	          patient.setFilterMode(true);
	          patient.getListview().setVisible(true);
	          //Here we are using external Listview instead of AutoFillTextBox's
	          ListView list = new ListView();
	          list.itemsProperty().bind(patient.getListview().itemsProperty());
		} catch (SQLException e) {
			System.out.print(false);
			e.printStackTrace();
		}	   
}
public void getDoctors() {
	String service = DoctorSession.getService();
	String CurrentDoctor = DoctorSession.getDoctorname();
	s_doctor_liste.add(CurrentDoctor);
	String query_getDoctor_information ="SELECT * FROM Doctors where service='"+service+"' and type='Surgeon' " ;
	ResultSet rs;
	try {
		rs = connectDB.createStatement().executeQuery(query_getDoctor_information);
		while(rs.next()) {
			s_doctor_liste.add(rs.getString("doctor_name"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 Surgeon_doctor.setItems(s_doctor_liste);
}
public void reset() {
	 medical_label.setVisible(false);
   	 medical_suppley_yes.setVisible(false);
   	 medical_suppley_no.setVisible(false);
   	 lab_yes.setVisible(false);
   	 lab_no.setVisible(false);
   	 lab_label.setVisible(false);
   	 x_ray_yes.setVisible(false);
   	 x_ray_no.setVisible(false);
   	 x_ray_label.setVisible(false);
   	medical_suppley_price.setVisible(false);
    lab_price.setVisible(false);
    o_label.setVisible(false);
    o_yes.setVisible(false);
    o_no.setVisible(false);
    o_status_label.setVisible(false);
		 h_no.setVisible(false);
		 h_yes.setVisible(false);
		 h_status_label.setVisible(false);
		 hostablied_label.setVisible(false);
		 o_date.setVisible(false);
		o_heur.setVisible(false);
         o_room.setVisible(false);
         o_room_label.setVisible(false);
         o_heur.setVisible(false);
         heur_label.setVisible(false);
         date_label.setVisible(false);
		 h_room.setVisible(false);
			 h_room_label.setVisible(false);
			nombre_label.setVisible(false);
			nombre.setVisible(false);
            Surgeon_doctor.setVisible(false);
            Surgeon_label.setVisible(false);
}
public int checkRoomDisp () throws SQLException {
	int count = 0;
	Statement ps  ;
	  String selecthrooms = "SELECT * from room where type='hostablied' and status='not_used' ";
		ps =  connectDB.prepareStatement(selecthrooms);
		ResultSet selecthrooms_rs = ps.executeQuery(selecthrooms);
		int i = 0 ;
		while(selecthrooms_rs.next()) {
			i++ ;
		}
		count = i ;
	return count ;
}
public void checkstatus (ActionEvent event) throws SQLException {
	 Connect connectnow = new Connect();
     Connection connectDB = connectnow.getConnection();	
	 int count = checkRoomDisp() ;
    if(waiting.isSelected()) {
    	compelted.setSelected(false);
   	 medical_label.setVisible(true);
   	 medical_suppley_yes.setVisible(true);
   	 medical_suppley_no.setVisible(true);
   	 lab_yes.setVisible(true);
   	 lab_no.setVisible(true);
   	 lab_label.setVisible(true);
   	 x_ray_yes.setVisible(true);
   	 x_ray_no.setVisible(true);
   	 x_ray_label.setVisible(true);
   	 if(medical_suppley_yes.isSelected()) {
   		 medical_suppley_price.setVisible(true);
   	 }
   	 if(lab_yes.isSelected()) {
   		 lab_price.setVisible(true);
	     o_label.setVisible(true);
	     o_yes.setVisible(true);
	     o_no.setVisible(true);
	     o_status_label.setVisible(true);
   		 h_no.setVisible(true);
   		 h_yes.setVisible(true);
   		 h_status_label.setVisible(true);
   		 hostablied_label.setVisible(true);
   		 if(o_yes.isSelected()) {
   			 o_date.setVisible(true);
   			 o_heur.setVisible(true);
             o_room.setVisible(true);
             o_room_label.setVisible(true);
             o_heur.setVisible(true);
             heur_label.setVisible(true);
             date_label.setVisible(true);
             Surgeon_doctor.setVisible(true);
             Surgeon_label.setVisible(true);
   		 }
   		 if(h_yes.isSelected()) {
   			 if(count == 0) {
   				 noroom.setVisible(true);
   			 } else {
   				 h_room.setVisible(true);
   	   			 h_room_label.setVisible(true);
   	   			nombre_label.setVisible(true);
   	   			nombre.setVisible(true);
   			 }
   		 } else if(h_no.isSelected()) {
  			 if(count == 0) {
  				 noroom.setVisible(false);
  			 } else {
  				 h_room.setVisible(false);
  	   			 h_room_label.setVisible(false);
  	   			nombre_label.setVisible(false);
  	   			nombre.setVisible(false);
  			 }
   		 }
   	 } else if (lab_no.isSelected()) {
   		 lab_price.setVisible(false);
	     o_label.setVisible(false);
	     o_yes.setVisible(false);
	     o_no.setVisible(false);
	     o_status_label.setVisible(false);
   		 h_no.setVisible(false);
   		 h_yes.setVisible(false);
   		 h_status_label.setVisible(false);
   		 hostablied_label.setVisible(false);
   		h_room.setVisible(false);
			 h_room_label.setVisible(false);
			nombre_label.setVisible(false);
			nombre.setVisible(false);
			o_date.setVisible(false);
  			 o_heur.setVisible(false);
            o_room.setVisible(false);
            o_room_label.setVisible(false);
            o_heur.setVisible(false);
            heur_label.setVisible(false);
            date_label.setVisible(false);
   	 }
    } 
    if(compelted.isSelected()) {
    	waiting.setSelected(false);
	   	 medical_label.setVisible(false);
	   	 medical_suppley_yes.setVisible(false);
	   	 medical_suppley_no.setVisible(false);
	   	 lab_yes.setVisible(false);
	   	 lab_no.setVisible(false);
	   	 lab_label.setVisible(false);
	   	 x_ray_yes.setVisible(false);
	   	 x_ray_no.setVisible(false);
	   	 x_ray_label.setVisible(false);
	   	medical_suppley_price.setVisible(false);
	    lab_price.setVisible(false);
	    o_label.setVisible(false);
	    o_yes.setVisible(false);
	    o_no.setVisible(false);
	    o_status_label.setVisible(false);
			 h_no.setVisible(false);
			 h_yes.setVisible(false);
			 h_status_label.setVisible(false);
			 hostablied_label.setVisible(false);
			 o_date.setVisible(false);
			o_heur.setVisible(false);
	         o_room.setVisible(false);
	         o_room_label.setVisible(false);
	         o_heur.setVisible(false);
	         heur_label.setVisible(false);
	         date_label.setVisible(false);
			 h_room.setVisible(false);
				 h_room_label.setVisible(false);
				nombre_label.setVisible(false);
				nombre.setVisible(false);
	             Surgeon_doctor.setVisible(false);
	             Surgeon_label.setVisible(false);
	    }
}
public void updateroom (String roomname) throws SQLException {
	String rhuQuery = "UPDATE `room` SET `status` = ? WHERE room_name = ?";
	PreparedStatement rhu ;
	rhu =  connectDB.prepareStatement(rhuQuery);
	rhu.setString(1, "used");
	rhu.setString(2, roomname);
	int rhue = rhu.executeUpdate();
	if(rhue==1) {
		System.out.print("Room updated\n");
		} else {
			System.out.print("Room  not updated\n");	
		}
}
public void upateStatus (String status) throws SQLException {
	String patient_string  = patient.getText();
	String selectvisite ="SELECT * from visite where patient= '"+patient_string+"' and status!='completed' ";
		ResultSet rs_visite = connectDB.createStatement().executeQuery(selectvisite);
		while(rs_visite.next()) {
			String visite_id = rs_visite.getString("visite_id");
			String queryupdate = "Update visite SET status=? where visite_id =?";
			PreparedStatement ps = connectDB.prepareStatement(queryupdate);
			ps.setString(1, status);
			ps.setString(2, visite_id);
			int exe = ps.executeUpdate();
			if( exe == 1) {
			Alert ale = new Alert(AlertType.CONFIRMATION)	;
			ale.setHeaderText(status);
			ale.setContentText(status+"Updated Succfuly for patient : "+patient_string + "And visite :" +visite_id);
			ale.show();
			} else {
				Alert ale = new Alert(AlertType.ERROR)	;
				ale.setHeaderText(status);
				ale.setContentText(status+"Updated failed for patient : "+patient_string + "And visite :" +visite_id);
				ale.show();
			}
		}
}
public void edite_visite(ActionEvent event) throws SQLException, ParseException {
	String CurrentDoctor = DoctorSession.getDoctorname();
	String status ;
	String patient_string  = patient.getText();
	String selectvisite ="SELECT * from visite where patient= '"+patient_string+"' and status!='completed' ";
	if(waiting.isSelected()) {
		ResultSet rs_visite = connectDB.createStatement().executeQuery(selectvisite);
		while(rs_visite.next()) {
			String visite_id = rs_visite.getString("visite_id");
			 String Queryinvoice = "UPDATE invoice SET Medical_Supplies_Medicines=? , Doctors_burden=?, lab_test=? , xray=? , hostablised=? , operations_cost=? ,somme=? where invoice_id=?";
				String medical_suppley_string, lab_string,   Doctors_burden_string = "50" ,nombre_string = "0" , opera_string = "0" , x_ray_string;
				Integer medical_suppley_int=0 , x_ray_int=0, lab_int=0 , host_int = 0 , opera_int = 0 , doctor=50 ;	
				 PreparedStatement psi ;
					psi = connectDB.prepareStatement(Queryinvoice);
			if(medical_suppley_yes.isSelected()) {
				medical_suppley_string = medical_suppley_price.getText();
				medical_suppley_int = Integer.valueOf(medical_suppley_string);
			} else {
				 medical_suppley_int = 0;
				 medical_suppley_string = "0";
			}
			if(x_ray_yes.isSelected()) {
				  x_ray_int = 1000;
				  x_ray_string = "1000";
			} else {
				x_ray_int = 0;
				x_ray_string= "0";
			}
			if(lab_yes.isSelected()) {
				lab_string = lab_price.getText();
				lab_int = Integer.valueOf(lab_string);
				if(o_yes.isSelected()) {
					opera_int = 20000;
					opera_string = "20000";
					PreparedStatement pso ;
					ResultSet rs = connectDB.createStatement().executeQuery(selectvisite);
					while(rs.next()) {
						String patient = rs.getString("patient");
						String opeartion_heur_string = o_heur.getText();
						String opeartion_date_string = o_date.getValue().toString();
						String operationstatus = "not finished";
						String opeartion_room_string = o_room.getSelectionModel().getSelectedItem().toString();
						String query_insert_operation = "insert into operations(visite_id,patient,opeations_heur,operation_date,operation_status,room,Doctor) values(?,?,?,?,?,?,?)";
						pso = connectDB.prepareStatement(query_insert_operation);
						pso.setString(1, visite_id);
						pso.setString(2, patient);
						pso.setString(3, opeartion_heur_string);
						pso.setString(4, opeartion_date_string);
						pso.setString(5, operationstatus);
						pso.setString(6, opeartion_room_string);
						pso.setString(7, CurrentDoctor);
						int psoi = pso.executeUpdate();
						if(psoi==1 ) {
							Alert ale =  new Alert(AlertType.CONFIRMATION);
							ale.setHeaderText("Opeartion");
							ale.setContentText("Operation for patient"+patient+" added succfuly");
							ale.show();
						} else {
							Alert ale =  new Alert(AlertType.ERROR);
							ale.setHeaderText("Opeartion");
							ale.setContentText("Operation for patient"+patient+" add failed");
							ale.show();
						}
						updateroom(opeartion_room_string);
					}
				} else {
					opera_int = 0;
					opera_string = "0";
				}
				if(h_yes.isSelected()) {
					 ResultSet rs = connectDB.createStatement().executeQuery(selectvisite);
					 while(rs.next()) {
				String EntreyDate = rs.getString("EntreyDate");
				String patient_name = rs.getString("patient");
				int count = checkRoomDisp();
				if(count==0) {
					status = "waiting for room ";
					upateStatus(status);
				} else {
					nombre_string = nombre.getText();   
					host_int = Integer.valueOf(nombre_string) * 200;
					nombre_string =  host_int.toString();
					String query_hostablied_insert = "insert into hostablied_patients(visite_id,patient,Entrey_Date,Exite_date,patient_status,room,Doctor) VALUES(?,?,?,?,?,?)";
					 PreparedStatement psh ;
					 String patient_status = "waiting";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						int nombre_int = Integer.valueOf(nombre.getText());
					    c.setTime(sdf.parse(EntreyDate));
						c.add(Calendar.DAY_OF_MONTH,nombre_int);  
						String Exite_date = sdf.format(c.getTime());
						String  room = h_room.getSelectionModel().getSelectedItem().toString();
						 psh = connectDB.prepareStatement(query_hostablied_insert);
						psh.setString(1,visite_id);
						psh.setString(2,patient_name);
						psh.setString(3,EntreyDate);
						psh.setString(4,Exite_date);
						psh.setString(5,patient_status);
						psh.setString(6,room);
						int pshi = psh.executeUpdate();
						if(pshi==1) {
							Alert ale =  new Alert(AlertType.CONFIRMATION);
							ale.setHeaderText("Hostabiled Patient");
							ale.setContentText("Hostablied patient"+patient_name+" added succfuly");
							ale.show();
						} else {
							Alert ale =  new Alert(AlertType.ERROR);
							ale.setHeaderText("Hostabiled Patient");
							ale.setContentText("Hostablied patient"+patient_name+" add failed");
							ale.show();
						}
						updateroom(room);	
				}
				 }
				} else {
					host_int = 0 ;
					nombre_string="0";
				}
			} else {
				lab_int = 0 ;
				lab_string ="0";
			}
			Integer somme_int = medical_suppley_int + doctor + lab_int + x_ray_int+ host_int + opera_int ;
			String somme_string = somme_int.toString();
				psi.setString(1, medical_suppley_string);
				psi.setString(2, Doctors_burden_string);
				psi.setString(3, lab_string);
				psi.setString(4, x_ray_string);
				psi.setString(5, nombre_string);
				psi.setString(6, opera_string);
				psi.setString(7, somme_string);
				psi.setString(8, patient.getText());
				int psii = psi.executeUpdate();
				if(psii==1) {
					Alert ale =  new Alert(AlertType.CONFIRMATION);
					ale.setHeaderText("Invoice");
					ale.setContentText("Invoice updated Succfuly");
					ale.show();
				} else {
					Alert ale =  new Alert(AlertType.ERROR);
					ale.setHeaderText("Invoice");
					ale.setContentText("Invoice update failed");
					ale.show();
			}
				}
			} else {
				status = " completed";
				upateStatus(status);
			}
	}
}
