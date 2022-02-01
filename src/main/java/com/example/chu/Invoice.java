package com.example.chu;

public class Invoice {
	int invoice_id;
    int Medical_Supplies;
    int Doctors_burden;
    int lab_test;
    int xray;
    int hostablised;
    int operations_cost;
    int somme;
    String status ;
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getMedical_Supplies() {
		return Medical_Supplies;
	}
	public void setMedical_Supplies(int medical_Supplies) {
		Medical_Supplies = medical_Supplies;
	}
	public int getDoctors_burden() {
		return Doctors_burden;
	}
	public void setDoctors_burden(int doctors_burden) {
		Doctors_burden = doctors_burden;
	}
	public int getLab_test() {
		return lab_test;
	}
	public void setLab_test(int lab_test) {
		this.lab_test = lab_test;
	}
	public int getXray() {
		return xray;
	}
	public void setXray(int xray) {
		this.xray = xray;
	}
	public int getHostablised() {
		return hostablised;
	}
	public void setHostablised(int hostablised) {
		this.hostablised = hostablised;
	}
	public int getOperations_cost() {
		return operations_cost;
	}
	public void setOperations_cost(int operations_cost) {
		this.operations_cost = operations_cost;
	}
	public int getSomme() {
		return somme;
	}
	public void setSomme(int somme) {
		this.somme = somme;
	}
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Invoice(int invoice_id, int medical_Supplies, int doctors_burden, int lab_test, int xray, int hostablised,
			int operations_cost, int somme, String status) {
		super();
		this.invoice_id = invoice_id;
		Medical_Supplies = medical_Supplies;
		Doctors_burden = doctors_burden;
		this.lab_test = lab_test;
		this.xray = xray;
		this.hostablised = hostablised;
		this.operations_cost = operations_cost;
		this.somme = somme;
		this.status = status;
	}

}
