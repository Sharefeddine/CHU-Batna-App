package com.example.chu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class admin_menu_con {
@FXML
Pane rootpane;
@FXML
Button add_doctor_button;
@FXML
Button see_doctor_button;
public void add_doctor(ActionEvent event) throws Exception {
	Parent root  = FXMLLoader.load(getClass().getResource("add_doctor.fxml"));
	Stage windows = (Stage)add_doctor_button.getScene().getWindow();
	windows.setScene(new Scene(root));
}
@FXML
public void see_doctor(ActionEvent event) throws Exception {
	Parent root  = FXMLLoader.load(getClass().getResource("see_doctors.fxml"));
	Stage windows = (Stage)see_doctor_button.getScene().getWindow();
	windows.setScene(new Scene(root));
}
@FXML
public void logout (ActionEvent event) throws Exception {
	Parent root  = FXMLLoader.load(getClass().getResource("sign.fxml"));
	Stage windows = (Stage)add_doctor_button.getScene().getWindow();
	windows.setScene(new Scene(root));
}
}
