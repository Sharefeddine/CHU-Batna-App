package com.example.chu;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class main_page_doctor implements Initializable {
	@FXML
	Label username_label ;
    @FXML
    Label hostabiled_patient_total ;
   @FXML
    Label waiting_patient_total ;
	@Override
	public void initialize(URL location , ResourceBundle resources) {
		String CurerentDoctor = DoctorSession.getDoctorname();
		username_label.setText("Welcome Doctor : "+CurerentDoctor);
		hostabiled_patient_total.setText("You have : "+functions.getHostabiledPatients_Doctor()+ " Hostabiled patients ");
		waiting_patient_total.setText("You have : "+functions.getWaitingPatients_Doctor()+" Waiting patients");		
	}
}