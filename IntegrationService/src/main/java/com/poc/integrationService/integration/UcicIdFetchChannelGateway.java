package com.poc.integrationService.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.poc.integrationService.model.UcicFetchModel;

@MessagingGateway(defaultRequestChannel = "ucicIdFetchChannel")
public interface UcicIdFetchChannelGateway {

	@Gateway
	public Message<String> fetchUcicId(Message<UcicFetchModel> message);
}
