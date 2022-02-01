package com.example.chu;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
public class add_doctor_con  implements Initializable{
@FXML
TextField name ;
@FXML
TextField age ;
@FXML
TextField adress ;
@FXML
TextField heur ;
@FXML
TextField username ;
@FXML
TextField password;
@FXML
ChoiceBox<String> service ;
String[] service_choices = {"Select","Cardiology","Gynecology","Orthopedics","Pediatrics","l'ORL","CAC"};
@FXML
ChoiceBox<String> days ;
String[] days_choices = {"Select","Sunday","Monday","Theusday","Wednesday","Thursday","Friday","Saturday"};
@FXML
Label Result ;
@FXML 
private  Pane rootPane ;
@FXML 
ComboBox type ;
String[] type_choices = {"Select Type","Surgeon","Doctor"};
@Override
public void  initialize (URL location , ResourceBundle resources ) {
	 service.getItems().addAll(service_choices);
	 service.setValue("Select");
	 days.getItems().addAll(days_choices);
	 days.setValue("Select");
	 type.getItems().addAll(type_choices);
	 type.setValue("Select Type");
}
public void rest() {
	days.setValue("Select");
	service.setValue("Select");
	 type.setValue("Select Type");
	name.setText("");
	age.setText("");
	adress.setText("");
	heur.setText("");
	username.setText("");
	password.setText("");
}
public void add(ActionEvent event) throws IOException, SQLException {
	String style = "-fx-border-color : red ;-fx-border-width:2px;";
	Connect connectnow = new Connect();
	 Connection connectDB = connectnow.getConnection();
	 String name_string = name.getText();
	 String age_string = age.getText();
	 Integer age_int = Integer.valueOf(age_string);
	 String adress_string = adress.getText();
	 String heur_string = heur.getText();
	 Integer heur_int = Integer.valueOf(heur_string);
	 String username_string = username.getText();
	 String password_string =password.getText();
	 String type_doctor = "Doctor";
	 if(name_string.isEmpty() || age_string.isEmpty() || adress_string.isEmpty() || adress_string.isEmpty() ||heur_string.isEmpty() || username_string.isEmpty() || password_string.isEmpty() ||service.getSelectionModel().getSelectedItem().equals("Select") ||days.getSelectionModel().getSelectedItem().equals("Select") || type.getSelectionModel().getSelectedItem().equals("Select Type") ) {
		 if(name_string.isEmpty()) {
				name.setStyle(style);
			} 
		  if(age_string.isEmpty()) {
				age.setStyle(style);
			}  
		  if(adress_string.isEmpty()) {
				adress.setStyle(style);
			} 
		  if(heur_string.isEmpty() ) {
			heur.setStyle(style);
			} 
		  if(username_string.isEmpty()) {
			 username.setStyle(style);
		}			
		  if(password_string.isEmpty()) {
			 password.setStyle(style);
		} 			
		  if(service.getSelectionModel().getSelectedItem().equals("Select")) {
			 service.setStyle(style);
		 }		
		  if(days.getSelectionModel().getSelectedItem().equals("Select")) { 
			 days.setStyle(style);
		 } 
		  if(type.getSelectionModel().getSelectedItem().equals("Select Type")) {
			  type.setStyle(style);
		  }
	 } else {
		 if(age_int<30 || age_int>60) {
			 age.setStyle(style);
		 } else {
			 String select_doctor ="select * from doctors";				
			 String service_string = service.getSelectionModel().getSelectedItem().toString();
			 String days_string = days.getSelectionModel().getSelectedItem().toString();
			 String type_string = type.getSelectionModel().getSelectedItem().toString();
			Statement statement = connectDB.createStatement();
			ResultSet resultSet = statement.executeQuery(select_doctor);
			if(resultSet.next() ) {
				String doctorname_db = resultSet.getString("doctor_name");
				String doctorusername_db = resultSet.getString("doctor_username");
				 if(doctorname_db==name_string  || doctorusername_db==username_string ) {
					 if(doctorname_db==name_string) {
						name.setStyle(style); 
					 } if (doctorusername_db==username_string) {
						 username.setStyle(style);
					 }
				 } else {
					 PreparedStatement psd ;
					 String doctor_query = "INSERT INTO doctors(doctor_name,doctor_username,age,adress,service,day,heur,type) VALUES(?,?,?,?,?,?,?,?)";
						psd = connectDB.prepareStatement(doctor_query);
						psd.setString(1, name_string);
						psd.setString(2,username_string);
						psd.setString(3, age_string);
						psd.setString(4, adress_string);
						psd.setString(5, service_string);
						psd.setString(6, days_string);
						psd.setString(7, heur_string);
						psd.setString(8, type_string);
						int insert1 = psd.executeUpdate();
						if(insert1 ==1) {
							Alert added = new Alert(AlertType.CONFIRMATION);
							added.setHeaderText(name_string+ "Doctor Added succfuly");
						    added.show();
						} else {
							Alert notadded = new Alert(AlertType.ERROR);
							notadded.setHeaderText("Doctor not added");
							notadded.show();
							
						}			
					 PreparedStatement psu ;
					 String user_query="INSERT INTO user(username,password,type) VALUES(?,?,?)";	
						psu = connectDB.prepareStatement(user_query);
						psu.setString(1, username_string);
						psu.setString(2, password_string);
						psu.setString(3, type_doctor);
					    psu.executeUpdate();
					    rest();   
				} 
		 }
			}
		} 
 }	 
}

