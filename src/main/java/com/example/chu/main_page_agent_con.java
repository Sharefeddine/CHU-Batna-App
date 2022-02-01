package com.example.chu;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class main_page_agent_con  implements Initializable {
@FXML
Label patientnombr ;
@FXML
Label visitenombre;
@FXML
TableView<Doctors> see_doctors_table;
@FXML
TableColumn<Doctors, String> doctor_id_col ;
@FXML
TableColumn<Doctors, String> name_col ;
@FXML
TableColumn<Doctors, String> adress_col ;
@FXML
TableColumn<Doctors, String>service_col ;
@FXML
TableColumn<Doctors, String>heur_col ;
@FXML
TableColumn<Doctors, String>type_col ;
@FXML
TextField search ;
@FXML
Label username_label ;
ObservableList<Doctors> dliste = FXCollections.observableArrayList();
	@Override
	public void initialize (URL location , ResourceBundle resources ) {
		 String Current_user = AgentSession.getUsername();
		 username_label.setText(Current_user);
		 DoctorToday();
		patientnombr.setText(functions.get_total_patients());
		visitenombre.setText(functions.getVisite_agent());
	}
    public void DoctorToday() {
    	try  {
    	String day = functions.Today();
    	Connect connectnow = new Connect();
        Connection connectDB = connectnow.getConnection();
		ResultSet rs = connectDB.createStatement().executeQuery("Select * from doctors where day='"+day+"' ");
		while(rs.next()) {
			dliste.add(new Doctors(rs.getInt("age"),rs.getString("doctor_name"),rs.getString("adress"),"", "",null , null ,rs.getInt("doctor_id"), rs.getString("service"), rs.getString("day"), rs.getString("heur"),rs.getString("type")));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		doctor_id_col.setCellValueFactory(new PropertyValueFactory<>("doctorid"));
		name_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		adress_col.setCellValueFactory(new PropertyValueFactory<>("adress"));
		service_col.setCellValueFactory(new PropertyValueFactory<>("service"));
		heur_col.setCellValueFactory(new PropertyValueFactory<>("heur"));
		type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
		see_doctors_table.setItems(dliste);
	    FilteredList<Doctors> filtredata = new FilteredList<>(dliste,b->true);
	    search.textProperty().addListener((Observable, oldValue , newValue ) ->{
	    	filtredata.setPredicate(Doctors ->{
	    		if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
	    			return true ;
	    		}
	    		String search = newValue.toLowerCase();
	    		if(Doctors.getDoctorid().toString().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		} else if (Doctors.getFullname().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    			
	    		}else if (Doctors.getService().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    			
	    		}else if (Doctors.getHeur().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		} else if(Doctors.getType().toLowerCase().indexOf(search)>-1) {
	    			return true ;
	    		}
				return false;
	    	});
	    });
	    SortedList<Doctors> sortedata = new SortedList<>(filtredata);
		sortedata.comparatorProperty().bind(see_doctors_table.comparatorProperty());
		see_doctors_table.setItems(sortedata);
    }
    public String getusername(String username) {
    	username_label.setText(username);
		return username;
    }
}
