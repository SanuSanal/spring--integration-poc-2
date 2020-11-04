package com.poc.mglApplication.model;

public class BranchModel {

	private String mpNo;
	private String fdglCode;
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
	public BranchModel(String mpNo, String fdglCode) {
		this.mpNo = mpNo;
		this.fdglCode = fdglCode;
	} 
	
}
