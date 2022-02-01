package com.example.chu;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class hostablized_patients_controle  implements Initializable {
	String username ;
	@FXML
	TextField search ;
	@FXML
	private TableView<Hostablised_patients> see_hostablized_patients_table ;
	@FXML
	private TableColumn<Hostablised_patients, String>num_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>hostablized_id_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>visite_id_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>patient_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>Entrey_Date_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>Exite_Date_col ;
	@FXML
	private TableColumn<Hostablised_patients, String>patient_status_col ;
	@FXML
	private  TableColumn<Hostablised_patients, String>room_col ;
	ObservableList<Hostablised_patients> vliste = FXCollections.observableArrayList();
	public void initialize (URL location , ResourceBundle resources ) {
		String CurerentDoctor = DoctorSession.getDoctorname();
	    try {
	 	   Connect connectnow = new Connect();
	        Connection connectDB = connectnow.getConnection();
			ResultSet rs = connectDB.createStatement().executeQuery("Select * from hostablied_patients , visite where visite.visite_id= hostablied_patients.visite_id and  hostablied_patients.Exite_date!='not defined' and visite.Doctor='"+CurerentDoctor+"' ");
			while(rs.next()) {
				vliste.add(new Hostablised_patients(rs.getInt("visite.visite_id"), rs.getString("visite.patient"), null, null, null, rs.getString("visite.status"), rs.getDate("visite.EntreyDate"), rs.getInt("hostablied_patients.hostablied_id"), rs.getString("hostablied_patients.room"), rs.getDate("hostablied_patients.Exite_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    visite_id_col.setCellValueFactory(new PropertyValueFactory<>("visite_id"));
	    hostablized_id_col.setCellValueFactory(new PropertyValueFactory<>("hostablied_id"));
	    patient_col.setCellValueFactory(new PropertyValueFactory<>("patient"));
	    Entrey_Date_col.setCellValueFactory(new PropertyValueFactory<>("Entrey_date"));
	    Exite_Date_col.setCellValueFactory(new PropertyValueFactory<>("Exite_date"));
	    room_col.setCellValueFactory(new PropertyValueFactory<>("room"));
	    patient_status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
	    see_hostablized_patients_table.setItems(vliste);
	    FilteredList<Hostablised_patients> filtredata = new FilteredList<>(vliste,b->true);
	    search.textProperty().addListener((Observable, oldValue , newValue ) ->{
	    	filtredata.setPredicate(Hostablised_patients ->{
	    		if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
	    			return true ;
	    		}
	    		String search = newValue.toLowerCase();
	    		if(Hostablised_patients.getPatient().toString().toLowerCase().indexOf(search)>-1) {
	    			
	    		} else if(Hostablised_patients.getEntrey_date().toString().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		} else if (Hostablised_patients.getRoom().toString().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		}		
				return false;
	    	});
	    });
	    SortedList<Hostablised_patients> sortedata = new SortedList<>(filtredata);
		sortedata.comparatorProperty().bind(see_hostablized_patients_table.comparatorProperty());
		see_hostablized_patients_table.setItems(sortedata);
	}

}
