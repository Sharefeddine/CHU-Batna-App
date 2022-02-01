package com.example.chu;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class Add_patient_controller implements Initializable {
	@FXML
	private TextField tfirstname ; 
	@FXML
	private TextField tlastname ;
@FXML
ComboBox<String> blood ;
String[]  blood_array = {"Select Blood type","A+","A-","AB+","AB-","B+","B-","O+","O-"};
	@FXML
	private DatePicker tbirthday ;
	@FXML
	private TextField adress ; 
	@FXML
	private TextField mobile ; 
	@FXML
	private TextField sn ;
	@FXML
	private RadioButton male , female ;
	private String gendre ;
	@Override
	public void initialize(URL location , ResourceBundle resources) {
		blood.getItems().addAll(blood_array);
	}
	public void rest() {
		tfirstname.setText("");
		tlastname.setText("");
        blood.setValue("Select Blood type");
		tbirthday.setValue(null);
		adress.setText("");
		mobile.setText("");
		sn.setText("");
		male.setSelected(false);
		female.setSelected(false);		
	}
	public void reset_action(ActionEvent event) {
		rest();
	}
	public void add_patient_button( ActionEvent event ) throws SQLException {
		Connect connectnow = new Connect();
	    Connection connectDB = connectnow.getConnection();
	    LocalDate birthdate = tbirthday.getValue();
        LocalDate nowdate = java.time.LocalDate.now();
		String style = "-fx-border-color : red ;-fx-border-width:2px;";
		String tfirstname_string= tfirstname.getText();
		String tlastname_string= tlastname.getText();
		int  age = 0 ;
		String tbirthday_string= tbirthday.getValue().toString();
		String adress_string= adress.getText();
		String mobile_string= mobile.getText();
		String sn_string= sn.getText();
		String blood_string = blood.getSelectionModel().getSelectedItem();
		gendre = "" ;
	    if(male.isSelected()) {
			gendre="Male";
		}
	        else if(female.isSelected()) {
		gendre="Female";
	    }
		if(tfirstname_string.isEmpty()||tlastname_string.isEmpty() ||tbirthday_string.isEmpty() || adress_string.isEmpty() || mobile_string.isEmpty() || mobile.getLength()!=10 || sn_string.isEmpty() || sn.getLength()!=10 || gendre=="") {
			if(tfirstname_string.isEmpty()) {
				tfirstname.setStyle(style);
			}
	        if(tlastname_string.isEmpty()) {
	        	tlastname.setStyle(style);
			}
	if(tbirthday_string.isEmpty()) {
		tbirthday.setStyle(style);
	}
	if(adress_string.isEmpty()) {
		adress.setStyle(style);	
	}
	if(mobile_string.isEmpty() || mobile.getLength()!=10) {
		mobile.setStyle(style);
	}
	if(sn_string.isEmpty() || sn.getLength()!=10) {
		sn.setStyle(style);
	}
	if(gendre=="") {
		male.setStyle(style);
		female.setStyle(style);
	}
		}  else {
	String select_patient = "select * from patient";
	Statement statement = connectDB.createStatement();
	ResultSet resultSet = statement.executeQuery(select_patient);
	if(resultSet.next()) {
		String firstname_db = resultSet.getString("firstname");
		String lastname_db = resultSet.getString("lastname");
		String fullname_string =tfirstname_string  + " "+ tlastname_string ;
		age = Period.between(birthdate,nowdate).getYears();
		if(firstname_db==tfirstname_string && lastname_db==tlastname_string   ) {
			tfirstname.setStyle(style);
			tlastname.setStyle(style);
		} else {
		           PreparedStatement pst ;
		           pst = connectDB.prepareStatement("INSERT INTO `patient`(`firstname`, `lastname`,fullname, `age`, `gendre`,  `birthdate`,  `adress`, `mobile`, `SN`,`Blood`) VALUES(?,?,?,?,?,?,?,?,?,?)   ");
		           pst.setString(1, tfirstname_string);
		           pst.setString(2, tlastname_string);
		           pst.setString(3, fullname_string);
				   pst.setInt(4,age);
		           pst.setString(5,gendre);
		           pst.setString(6, tbirthday_string);
		           pst.setString(7,adress_string);
		           pst.setString(8, mobile_string);
		           pst.setString(9, sn_string);
		           pst.setString(10, blood_string);
		         int status =pst.executeUpdate();
		         if(status==1) {
		        	 Alert added = new Alert(AlertType.CONFIRMATION);
						added.setHeaderText("Patient "+fullname_string+ " Added succfuly");
						added.show();
						rest();
		         } else {
		        	 Alert notadded = new Alert(AlertType.ERROR);
						notadded.setHeaderText("Patient "+fullname_string+" not added");
						notadded.show();
						rest();
		         }              
		}
	}
}
	}

}
