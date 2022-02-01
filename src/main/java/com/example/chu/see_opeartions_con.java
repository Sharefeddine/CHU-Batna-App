package com.example.chu;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
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
public class see_opeartions_con implements Initializable {
@FXML
private TableView<Operations> see_opeartions_table ;
@FXML
private TableColumn<Operations, String>opeartion_id_col;
@FXML
private TableColumn<Operations, String>patient_id_col;
@FXML
private TableColumn<Operations, String>Date_col;
@FXML
private TableColumn<Operations, String>heur_col;
@FXML
private TableColumn<Operations, String>salle_col;
@FXML
private TableColumn<Operations, String>doctor_col;
@FXML 
TextField search ;
@FXML
ObservableList<Operations> oliste = FXCollections.observableArrayList();
@Override
public void initialize (URL location , ResourceBundle resources ) {
	String type ="type" ;
	String service = "Service";
	String status = "";
	String doctor = "";
    try {
  	   Connect connectnow = new Connect();
         Connection connectDB = connectnow.getConnection();
 		ResultSet rs = connectDB.createStatement().executeQuery("Select * from operations ");
 		while(rs.next()) {
 			oliste.add(new Operations(rs.getInt("visite_id"), rs.getString("patient"), type, service, rs.getString("Doctor"), status , null, rs.getInt("operation_id"), rs.getString("opeations_heur"), rs.getDate("operation_date") , rs.getString("room") , doctor));
 		}
 	} catch (SQLException e) {
 		e.printStackTrace();
 	}
    opeartion_id_col.setCellValueFactory(new PropertyValueFactory<>("operation_id"));
    patient_id_col.setCellValueFactory(new PropertyValueFactory<>("patient"));
    Date_col.setCellValueFactory(new PropertyValueFactory<>("operation_date"));
    heur_col.setCellValueFactory(new PropertyValueFactory<>("opeations_heur"));
    salle_col.setCellValueFactory(new PropertyValueFactory<>("operation_room"));   
    see_opeartions_table.setItems(oliste);
    FilteredList<Operations> filtredata = new FilteredList<>(oliste ,b->true);
    search.textProperty().addListener((Observable, oldValue , newValue ) ->{
    	filtredata.setPredicate(Operations ->{
    		if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
    			return true ;
    		}
    		String search = newValue.toLowerCase();
    		if(Operations.getOperation_date().toString().toLowerCase().indexOf(search) >-1) {
    			return true ;
    		} else if (Operations.getOperation_room().toLowerCase().indexOf(search) >-1) {
    			return true ;
    		}  else if(Operations.getOperation_doctor().toLowerCase().indexOf(search)>-1) {
    			return true ;
    		} else if(Operations.getPatient().toLowerCase().indexOf(search)>-1) {
    			return true ;
    		} else if(Operations.getOpeations_heur().toLowerCase().indexOf(search)>-1) {
    			return true ;
    		} else if(Operations.getService().toLowerCase().indexOf(search)>-1) {
    			return true ;
    		}
			return false;
    	});
    });
    SortedList<Operations> sortedata = new SortedList<>(filtredata);
	sortedata.comparatorProperty().bind(see_opeartions_table.comparatorProperty());
	see_opeartions_table.setItems(sortedata);	   
}
}
