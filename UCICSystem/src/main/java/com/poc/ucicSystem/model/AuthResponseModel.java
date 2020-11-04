package com.poc.ucicSystem.model;

public class AuthResponseModel {
	
	public boolean success;
    public String statusCode;
    public String authToken;
    public String messages;
	
    public AuthResponseModel() {
    	
    }

	public AuthResponseModel(boolean success, String statusCode, String authToken, String messages) {
		this.success = success;
		this.statusCode = statusCode;
		this.authToken = authToken;
		this.messages = messages;
	}

}
