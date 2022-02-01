package com.example.chu;
public class Patient extends  Person{
	Integer patient_id ;
	Integer SN;
    Integer mobile ;
    String gendre ;
    String blood ;
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public Integer getSN() {
		return SN;
	}
	public void setSN(Integer sN) {
		SN = sN;
	}
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	public String getGendre() {
		return gendre;
	}
	public void setGendre(String gendre) {
		this.gendre = gendre;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public Patient(Integer age, String fullname, String adress, Integer patient_id, Integer sN, Integer mobile,
			String gendre, String blood) {
		super(age, fullname, adress);
		this.patient_id = patient_id;
		SN = sN;
		this.mobile = mobile;
		this.gendre = gendre;
		this.blood = blood;
	}

}
