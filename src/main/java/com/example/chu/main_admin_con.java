package com.example.chu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class main_admin_con {
	@FXML
	Label admin ;
	@FXML
	Label total_hostabiled_rooms ;
	@FXML
	Label total_operations_rooms ;
	@FXML
	Label patients_total ;
	@FXML 
	Label visite_total ;
	@FXML
	Label doctors_total ;
public void initialize(URL location , ResourceBundle resources) {
	admin.setText("Welcome : Admin");
	patients_total.setText("You have : "+functions.get_total_patients()+ " patients");
	visite_total.setText("You have : "+functions.getVisite_agent());
	total_hostabiled_rooms.setText(" You have " +functions.getOccupied_Rooms_Admin("hostabiled")+" rooms from " + functions.getTotalRooms_Admin("hostabiled")+" rooms");
	total_operations_rooms.setText(" You have " +functions.getOccupied_OBRooms_Admin()+" rooms from " + functions.getTotalRooms_Admin("operation")+" rooms");
	doctors_total.setText("You have "+functions.getTotalDoctors()+"doctors");
}
}
