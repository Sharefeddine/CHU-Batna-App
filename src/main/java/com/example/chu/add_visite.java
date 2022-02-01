package com.example.chu;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import np.com.ngopal.control.AutoFillTextBox;
public class add_visite implements Initializable {
	@FXML 
	AutoFillTextBox<String> patient ;
ArrayList<String> autocomplte ; 
ObservableList<String> patient_liste = FXCollections.observableArrayList();
@FXML
   ComboBox<String> type ;
  String [] type_choices = {"Select","Controle","Consulation"};
  @FXML 
  ComboBox<String> doctor_combox ;
  ObservableList<String> doctor_liste = FXCollections.observableArrayList();
 @Override
 public void  initialize (URL location , ResourceBundle resources ) {
	 type.getItems().addAll(type_choices);
	 Connect connectnow = new Connect();
	 Connection connectDB = connectnow.getConnection();
	String date = functions.Today();
	 try {
			Statement ps  ;
			  String Query1 = "SELECT * from doctors where day='"+date+"' and type='Doctor' ";
			 ps =  connectDB.prepareStatement(Query1);
			ResultSet rs = ps.executeQuery(Query1);
			while(rs.next()) {
				doctor_liste.add(rs.getString("doctor_name"));
			}
			doctor_combox.setItems(doctor_liste);	
		} catch (SQLException e) {
			System.out.print(false);
			e.printStackTrace();
		}	
	 try {
			Statement ps  ;
			  String Query1 = "SELECT * from patient , visite where patient.fullname!=visite.patient ";
			 ps =  connectDB.prepareStatement(Query1);
			ResultSet rs = ps.executeQuery(Query1);
			while(rs.next()) {
				patient_liste.add(rs.getString("patient.fullname"));
			}
			patient.setData(patient_liste);
	          patient.setListLimit(10);
	          patient.setFilterMode(true);
	          patient.getListview().setVisible(true);
	          ListView list = new ListView();
	          list.itemsProperty().bind(patient.getListview().itemsProperty());
		} catch (SQLException e) {
			e.printStackTrace();
		}	   
 }
 public void reset() {
	 type.setValue("Select");
	 doctor_combox.setValue("Select");
 }
public void add_visite(ActionEvent event) throws SQLException {
	String style = "-fx-border-color : red ;-fx-border-width:2px;";
 if(patient.getText().isEmpty() || type.getSelectionModel().getSelectedItem().equals("Select") ||doctor_combox.getSelectionModel().getSelectedItem().equals("Select")) {
		 if(patient.getText().isEmpty()) {
			 patient.setStyle(style);
		 }
		 if(type.getSelectionModel().getSelectedItem().equals("Select")) {
			 type.setStyle(style);
		 }
		 if(doctor_combox.getSelectionModel().getSelectedItem().equals("Select")) {
			 doctor_combox.setStyle(style);
		 }
	 } else {
		 String styleSucces = "-fx-border-color : green ;-fx-border-width:2px;";
		 patient.setStyle(styleSucces);
		 Connect connectnow = new Connect();
		 Connection connectDB = connectnow.getConnection();
		 String queryselectvalidate="Select * from visite where status!='complete'";
		 ResultSet rs0 = connectDB.createStatement().executeQuery(queryselectvalidate);
		 if(rs0.next()){
			 String patient_DB = rs0.getString("patient");
			 String patient_form = patient.getText();
			 if(patient_form.equals(patient_DB)) {
				 patient.setStyle(style);
				 Alert ale = new Alert(AlertType.ERROR) ;
				 ale.setContentText("Patient : " +patient_form+" alredy have visite");
				 ale.show();
			 }  else {
				 String patient_string = patient.getText();
				 String date =  java.time.LocalDate.now().toString();
				  String Type_DB= type.getSelectionModel().getSelectedItem().toString();
				  String doctor= doctor_combox.getSelectionModel().getSelectedItem().toString();
				 String Queryselectservice ="SELECT service from doctors where doctor_name= '"+doctor+"' ";
				 ResultSet rs = connectDB.createStatement().executeQuery(Queryselectservice);
					 if(rs.next()) {
						 String service = rs.getString("service");
						 String status ="waiting";
					      PreparedStatement psv ;
					         String queryinsert1 = "INSERT INTO `visite`(`patient`, `type`, `EntreyDate`,   `service`,   `Doctor`, `status`) VALUES(?,?,?,?,?,?)";
					         psv = connectDB.prepareStatement(queryinsert1);
					         psv.setString(1,patient_string);
					         psv.setString(2,Type_DB);
					         psv.setString(3,date);
					         psv.setString(4,service);
					         psv.setString(5, doctor);
					         psv.setString(6, status);
					       int insert1 =psv.executeUpdate();
					       if(insert1==1) {
					    	   Alert added = new Alert(AlertType.CONFIRMATION);
								added.setHeaderText("Visite for patient : "+patient_string+ " added succfuly");
								added.show();
					       } else {
					    	   Alert added = new Alert(AlertType.ERROR);
								added.setHeaderText("Visite for patient : "+patient_string+ "not  added ");
								added.show();
					       }
						 PreparedStatement psi ;
						 String querystring2 = "INSERT INTO `invoice`(patient,`Medical_Supplies_Medicines`, `Doctors_burden`, `lab_test`,   `xray`,   `hostablised`, `operations_cost`,  `somme`) VALUES(?,?,?,?,?,?,?,?)";
							 psi = connectDB.prepareStatement(querystring2);
							 psi.setString(1,patient_string);
							psi.setString(2, "0");
							psi.setString(3, "0");
							psi.setString(4, "0");
							psi.setString(5, "0");
							psi.setString(6, "0");
							psi.setString(7, "0");
							psi.setString(8, "0");
						    psi.executeUpdate();
					 }	  
			 } }
		 }} } 
			 