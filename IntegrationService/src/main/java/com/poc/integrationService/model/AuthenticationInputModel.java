package com.poc.integrationService.model;

public class AuthenticationInputModel {

	private String appUserName;
	private String appPass;
	private String mpNo;
	private String fdglCode; 
	private String empName;
	private String emailId; 
	private String designation;
	
	public String getAppUserName() {
		return appUserName;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public String getAppPass() {
		return appPass;
	}
	public void setAppPass(String appPass) {
		this.appPass = appPass;
	}
	public String getMpNo() {
		return mpNo;
	}
	public void setMpNo(String mpNo) {
		this.mpNo = mpNo;
	}
	public String getFdglCode() {
		return fdglCode;
	}
	public void setFdglCode(String fdglCode) {
		this.fdglCode = fdglCode;
	}
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
	public AuthenticationInputModel(String appUserName, String appPass, String mpNo, String fdglCode, String empName,
			String emailId, String designation) {
		this.appUserName = appUserName;
		this.appPass = appPass;
		this.mpNo = mpNo;
		this.fdglCode = fdglCode;
		this.empName = empName;
		this.emailId = emailId;
		this.designation = designation;
	}
	public AuthenticationInputModel() {

	}
}
