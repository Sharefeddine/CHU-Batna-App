package com.example.chu; 
import java.net.BindException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Show_Patient implements Initializable {
@FXML
private TableView<Patient> see_patient_table ;
@FXML
private TableColumn<Patient, String> patient_id_col ;
@FXML
private TableColumn<Patient, String> patient_fullname ;
@FXML
private TableColumn<Patient, String>patient_mobile_col ;
@FXML
private TableColumn<Patient, String> patient_genre_col  ;
@FXML
private TableColumn<Patient, String>patient_adress_col ;
@FXML
private TableColumn<Patient, String>patient_age_col ;
@FXML
private TableColumn<Patient, String>patient_sn_col ;
@FXML
private TableColumn<Patient, String>patient_blood_col ;
@FXML 
private TextField search ;
ObservableList<Patient> pliste = FXCollections.observableArrayList();
   @Override
public void initialize (URL location , ResourceBundle resources ) {
       try {
    	   Connect connectnow = new Connect();
           Connection connectDB = connectnow.getConnection();
		ResultSet rs = connectDB.createStatement().executeQuery("Select * from patient ");
		while(rs.next()) {
			pliste.add(new Patient(rs.getInt("age"), rs.getString("fullname"), rs.getString("adress"), rs.getInt("patient_id"), rs.getInt("SN"), rs.getInt("mobile"), rs.getString("gendre"), rs.getString("blood")));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   patient_id_col.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
	   patient_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
	   patient_mobile_col.setCellValueFactory(new PropertyValueFactory<>("mobile"));
	   patient_genre_col.setCellValueFactory(new PropertyValueFactory<>("gendre"));
	   patient_adress_col.setCellValueFactory(new PropertyValueFactory<>("adress"));
	   patient_age_col.setCellValueFactory(new PropertyValueFactory<>("age"));
	   patient_sn_col.setCellValueFactory(new PropertyValueFactory<>("SN"));
	   patient_blood_col.setCellValueFactory(new PropertyValueFactory<>("Blood"));
	   see_patient_table.setItems(pliste);
	    FilteredList<Patient> filtredata = new FilteredList<>(pliste,b->true);
	    search.textProperty().addListener((Observable, oldValue , newValue ) ->{
	    	filtredata.setPredicate(Patient ->{
	    		if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
	    			return true ;
	    		}
	    		String search = newValue.toLowerCase();
	    		if(Patient.getFullname().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		} else if (Patient.getAdress().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    			
	    		}else if (Patient.getGendre().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		} else if(Patient.getBlood().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		}
				return false;
	    	});
	    });
	    SortedList<Patient> sortedata = new SortedList<>(filtredata);
		sortedata.comparatorProperty().bind(see_patient_table.comparatorProperty());
		see_patient_table.setItems(sortedata);
}
}
