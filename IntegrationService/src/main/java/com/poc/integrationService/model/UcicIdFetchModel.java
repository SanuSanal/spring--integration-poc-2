package com.poc.integrationService.model;

public class UcicIdFetchModel {

	@Override
	public String toString() {
		return "UcicIdFetchModel [mpNo=" + mpNo + ", fdglCode=" + fdglCode + ", empName=" + empName + ", emailId="
				+ emailId + ", designation=" + designation + ", customerName=" + customerName + ", customerDob="
				+ customerDob + ", customerMobileNo=" + customerMobileNo + ", customerIdNo=" + customerIdNo + "]";
	}
	private String mpNo;
	private String fdglCode; 
	private String empName;
	private String emailId; 
	private String designation;
	private String customerName; 
	private String customerDob;
	private String customerMobileNo; 
	private String customerIdNo;
	
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerDob() {
		return customerDob;
	}
	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}
	public String getCustomerMobileNo() {
		return customerMobileNo;
	}
	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}
	public String getCustomerIdNo() {
		return customerIdNo;
	}
	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}
	
}
