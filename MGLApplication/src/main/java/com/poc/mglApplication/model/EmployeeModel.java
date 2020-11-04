package com.poc.mglApplication.model;

public class EmployeeModel {

	private String empName;
	private String emailId; 
	private String designation;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public EmployeeModel(String empName, String emailId, String designation) {
		this.empName = empName;
		this.emailId = emailId;
		this.designation = designation;
	}
	
}
