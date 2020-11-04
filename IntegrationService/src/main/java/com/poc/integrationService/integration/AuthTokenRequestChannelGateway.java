package com.poc.integrationService.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.poc.integrationService.model.AuthResponseModel;
import com.poc.integrationService.model.AuthenticationInputModel;

@MessagingGateway(defaultRequestChannel = "authTokenRequestChannel")
public interface AuthTokenRequestChannelGateway {

	@Gateway
	public Message<AuthResponseModel> fetchAuthToken(Message<AuthenticationInputModel> message);
}
