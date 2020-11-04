package com.poc.integrationService.model;

public class CreateLoanModel {
	
	private String customerName; 
	private String customerDob;
	private String customerMobileNo; 
	private String customerIdNo;
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
	@Override
	public String toString() {
		return "CreateLoanModel [customerName=" + customerName + ", customerDob=" + customerDob + ", customerMobileNo="
				+ customerMobileNo + ", customerIdNo=" + customerIdNo + "]";
	}
	
}
