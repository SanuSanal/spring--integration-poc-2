package com.poc.mglApplication.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.poc.mglApplication.model.UcicIdFetchModel;

@MessagingGateway(defaultRequestChannel = "fetchUcicIdTransInpChannel")
public interface UcicIdFetchGateway {

	@Gateway
	public Message<String> fetchUcicId(Message<UcicIdFetchModel> message);
}
