package com.example.chu;

public class Employer extends Person {
String username ;
String Password ;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public Employer(Integer age, String firstname, String lastname, String fullname, String adress,
		String username, String password) {
	super(age, firstname, lastname);
	this.username = username;
	Password = password;
}

}
