package com.example.chu;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class waiting_patient_con   implements Initializable{
	    String Doctor ;
		@FXML
		private TableView<Visite> see_visite_table ;
		@FXML
		private TableColumn<Visite, String> visite_id_col ;
		@FXML
		private TableColumn<Visite, String>type_col ;
		@FXML
		private TableColumn<Visite, String>patient_col ;
		@FXML
		private TableColumn<Visite, String> status_col  ;
		ObservableList<Visite> vliste = FXCollections.observableArrayList();
		@FXML
		TextField search ;
		@Override
		public void initialize (URL location , ResourceBundle resources ) {
			String CurerentDoctor = DoctorSession.getDoctorname();
			String date =  java.time.LocalDate.now().toString();
			String query = "Select * from visite where status='waiting' and EntreyDate='"+ date+"' and Doctor='"+CurerentDoctor+"'" ;
		    try {
		 	   Connect connectnow = new Connect();
		        Connection connectDB = connectnow.getConnection();
				ResultSet rs = connectDB.createStatement().executeQuery(query);
				while(rs.next()) {
					vliste.add(new Visite(rs.getInt("visite_id"),rs.getString("patient"), rs.getString("Type"),rs.getString("service"),rs.getString("Doctor"), rs.getString("status") , rs.getDate("EntreyDate")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    visite_id_col.setCellValueFactory(new PropertyValueFactory<>("visite_id"));
		    patient_col.setCellValueFactory(new PropertyValueFactory<>("patient"));
		    type_col.setCellValueFactory(new PropertyValueFactory<>("Type"));
		    status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
			 see_visite_table.setItems(vliste);   
			 FilteredList<Visite> filtredata = new FilteredList<>(vliste,b->true);
			    search.textProperty().addListener((Observable, oldValue , newValue ) ->{
			    	filtredata.setPredicate(Visite ->{
			    		if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
			    			return true ;
			    		}
			    		String search = newValue.toLowerCase();
			    		if(Visite.getPatient().toString().toLowerCase().indexOf(search)>-1) {
			    			
			    		} else if(Visite.getType().toString().toLowerCase().indexOf(search)>-1) {
			    			return true ;
			    		} 	
						return false;
			    	});
			    });
			    SortedList<Visite> sortedata = new SortedList<>(filtredata);
				sortedata.comparatorProperty().bind(see_visite_table.comparatorProperty());
				see_visite_table.setItems(sortedata);
		}
}
