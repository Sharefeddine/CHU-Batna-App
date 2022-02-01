package com.example.chu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class logincontroller {
    @FXML
    private TextField tusername;
    @FXML
    private PasswordField tpassword;
    @FXML
    private Label loginlabel;
    @FXML 
    private  Pane rootPane ;
     public  String doctorname ;
      public String username ;
      public String username_form ;
    public void reset () {
    	tusername.setText("");
    	tpassword.setText("");
    }
    public void login(ActionEvent event) throws SQLException, IOException {
    	String style = "-fx-border-color : red ;-fx-border-width:2px;";
    	 username_form = tusername.getText();
    	String password_form =tpassword.getText();
        if (username_form.isBlank() ==false && password_form.isBlank()==false) {
            Connect connectnow = new Connect();
            Connection connectDB = connectnow.getConnection();
            String verifyLogin = "SELECT * FROM user where username ='"+username_form+"' AND password='"+password_form+"' ";
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery(verifyLogin);
                    if (resultSet.next()) {
                        String type = resultSet.getString("type");
                    	if(type.equals("Doctor")) {
                    	    String query = "SELECT * FROM doctors where doctor_username='"+username_form+"'";
                    		ResultSet r= connectDB.createStatement().executeQuery(query);
                    		while(r.next()) {
                    			doctorname = r.getString("doctor_name") ;
                    			String service = r.getString("service");
                    			DoctorSession.setDoctorname(doctorname);
                    			DoctorSession.setService(service);
                    				}	
                    		Pane main_doctor =   FXMLLoader.load(getClass().getResource("main_page_doctor.fxml"));
                            rootPane.getChildren().setAll(main_doctor);
                    	} else if(type.equals("Agent")) {
                    		Pane main_agent =   FXMLLoader.load(getClass().getResource("main_page_agent.fxml"));
                            rootPane.getChildren().setAll(main_agent);
                    		AgentSession.setUsername(username_form);
                    	} else if(type.equals("Admin")) {
                    		Pane main_admin =   FXMLLoader.load(getClass().getResource("main_page_admin.fxml"));
                            rootPane.getChildren().setAll(main_admin);
                    	} 
                    }else {
                        Alert ale = new Alert(AlertType.ERROR) ;
                        ale.setContentText("User : " +username_form+" not found or password wrong");
                        ale.show() ;
                    }
        } else {
            if(username_form.isEmpty()) {
            	tusername.setStyle(style);
            }
            if(password_form.isEmpty()){
            	tpassword.setStyle(style);
            }
        }
    }
    public void reset_button(ActionEvent event) {
    	reset();
    }
}
