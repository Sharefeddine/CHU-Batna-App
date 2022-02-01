package com.example.chu;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
public class agent_menu  {
	@FXML
	private  Pane rootPane ;
	@FXML
	private Label Ltodayis ;
	@FXML
	Button  home_button ;
	@FXML
	Button  add_patient_button ;
	@FXML
	Button  see_patient_button ;
	@FXML
	Button  add_visite_button ;
	@FXML
	Button  see_vistie_button ;
	@FXML
	Button  see_operation_button ;
	@FXML
	Button  pay_facture_button ;
	@FXML
	Button  Logout_button ;
@FXML
public void  Home(ActionEvent event) throws IOException {
		Parent root  = FXMLLoader.load(getClass().getResource("main_page_agent.fxml"));
		Stage windows = (Stage)home_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
    public void add_patient(ActionEvent event) throws IOException {
 		Parent root  = FXMLLoader.load(getClass().getResource("add_patient.fxml"));
 		Stage windows = (Stage)add_patient_button.getScene().getWindow();
 		windows.setScene(new Scene(root));
}
public void see_patient (ActionEvent event) throws IOException {
	Parent root  = FXMLLoader.load(getClass().getResource("see_patient.fxml"));
		Stage windows = (Stage)see_patient_button.getScene().getWindow();
		windows.setScene(new Scene(root));
}
    public void add_visite (ActionEvent event) throws IOException {
    	Parent root  = FXMLLoader.load(getClass().getResource("add_visite.fxml"));
		Stage windows = (Stage)add_visite_button.getScene().getWindow();
		windows.setScene(new Scene(root));
    }
    public void see_vistie (ActionEvent event) throws IOException {
    	Parent root  = FXMLLoader.load(getClass().getResource("see_visite_agent.fxml"));
		Stage windows = (Stage)see_vistie_button.getScene().getWindow();
		windows.setScene(new Scene(root));
    }
    public void see_operation (ActionEvent event) throws IOException {
    	Parent root  = FXMLLoader.load(getClass().getResource("see_operations.fxml"));
		Stage windows = (Stage)see_operation_button.getScene().getWindow();
		windows.setScene(new Scene(root));
    }
    public void pay_facture (ActionEvent event) throws IOException {
    	Parent root  = FXMLLoader.load(getClass().getResource("pay_facture.fxml"));
		Stage windows = (Stage)pay_facture_button.getScene().getWindow();
		windows.setScene(new Scene(root));
    }
    public void Logout (ActionEvent event) throws IOException {
    	Parent root  = FXMLLoader.load(getClass().getResource("sign.fxml"));
		Stage windows = (Stage)Logout_button.getScene().getWindow();
		windows.setScene(new Scene(root));
    }
    }

