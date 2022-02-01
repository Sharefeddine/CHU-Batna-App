package com.example.chu;

public class Person {
    Integer age ;
    String fullname;
    String adress ;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Person(Integer age, String fullname, String adress) {
		super();
		this.age = age;
		this.fullname = fullname;
		this.adress = adress;
	}
	
}
