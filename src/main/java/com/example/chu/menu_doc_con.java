package com.example.chu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class menu_doc_con {
	@FXML 
	Pane rootPane ;
	@FXML
	Button Logout_button;
	@FXML
	Button Home_button ;
	@FXML
	Button add_h_patient_button ;
	@FXML
	Button see_h_patient_button ;
	@FXML
	Button see_w_patient_button ;
	@FXML
	Button add_opeartions_button ;
	@FXML
	Button edite_visite_button ;
	@FXML
	Button see_opeartions_button;
	public void home(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("main_page_doctor.fxml"));
		Stage windows = (Stage)Home_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void add_h_patient(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("add_h_patient.fxml"));
		Stage windows = (Stage)add_h_patient_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void see_h_patient(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("see_h_patients.fxml"));
		Stage windows = (Stage)see_h_patient_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void see_w_patient(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("see_w_patients.fxml"));
		Stage windows = (Stage)see_w_patient_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void add_opeartions(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("add_opeartions.fxml"));
		Stage windows = (Stage)add_opeartions_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void edite_visite(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("edite-visite_doctor.fxml"));
		Stage windows = (Stage)edite_visite_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void see_opeartions(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("see_operations_doctor.fxml"));
		Stage windows = (Stage)see_opeartions_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
public void logout(ActionEvent event) throws IOException {
	Parent root  = FXMLLoader.load(getClass().getResource("sign.fxml"));
	Stage windows = (Stage)Logout_button.getScene().getWindow();
	windows.setScene(new Scene(root));
}
}
