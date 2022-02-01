package com.example.chu;
import java.sql.*;
public class Connect {
    public  Connection Databaselink ;
    public  Connection getConnection() {
    String Db_user = "root";
    String Db_pass = "";
    String Db_name ="chu1";
    String Db_link = "jdbc:mysql://localhost/" + Db_name;
        try {
      Databaselink = DriverManager.getConnection(Db_link,Db_user,Db_pass);
        }catch (SQLException e) {
        e.printStackTrace();
        }
        return Databaselink;
    }
}
