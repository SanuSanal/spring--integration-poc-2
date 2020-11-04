package com.poc.integrationService.integration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.poc.integrationService.model.AuthResponseModel;
import com.poc.integrationService.model.AuthenticationInputModel;
import com.poc.integrationService.model.CreateLoanModel;
import com.poc.integrationService.model.UcicFetchModel;
import com.poc.integrationService.model.UcicIdFetchModel;

@Configuration
public class UcicFtech {

	@Value( "${mgl.appUserName}" )
	private String appUserName;
	@Value( "${mgl.appPass}" )
	private String appPass;
	
	@Bean
	public MessageChannel ucicFetchChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public HttpRequestHandlingMessagingGateway handleUcicFetchCall() {
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway();
		gateway.setRequestMapping(requestmapping());
		gateway.setRequestChannelName("ucicFetchChannel");
		gateway.setRequestPayloadTypeClass(UcicIdFetchModel.class);
		return gateway;
	}

	private RequestMapping requestmapping() {
		RequestMapping mapping = new RequestMapping();
		mapping.setPathPatterns("/api/v1/fetchUcic");
		mapping.setMethods(HttpMethod.POST);
		return mapping;
	} 
	
	@Bean
	public MessageChannel authTokenRequestChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Transformer(inputChannel = "authTokenRequestChannel", outputChannel = "objectToJsonAuthTknReqOutput")
	public ObjectToJsonTransformer objToJsonAuthTknInput() {
		return new ObjectToJsonTransformer();
	}
	
	@Bean
	public MessageChannel objectToJsonAuthTknReqOutput() {
		return new DirectChannel();
	}
	
	@ServiceActivator(inputChannel = "objectToJsonAuthTknReqOutput")
	@Bean
	public HttpRequestExecutingMessageHandler getAuthToken() {
		HttpRequestExecutingMessageHandler handler = new HttpRequestExecutingMessageHandler("http://localhost:8083/api/v1/authenticate");
		handler.setExpectedResponseType(AuthResponseModel.class);
		handler.setHttpMethod(HttpMethod.POST);
		return handler;
	}
	
	@Bean
	public MessageChannel ucicIdFetchChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Transformer(inputChannel = "ucicIdFetchChannel", outputChannel = "objectToJsonUcicIdReqOutput")
	public ObjectToJsonTransformer objToJsonUcicReqInput() {
		return new ObjectToJsonTransformer();
	}
	
	@Bean
	public MessageChannel objectToJsonUcicIdReqOutput() {
		return new DirectChannel();
	}
	
	@Bean
	@ServiceActivator(inputChannel = "objectToJsonUcicIdReqOutput")
	public HttpRequestExecutingMessageHandler getUcicId() {
		HttpRequestExecutingMessageHandler handler = new HttpRequestExecutingMessageHandler("http://localhost:8083/api/v1/fetchUcicId");
		handler.setExpectedResponseType(String.class);
		handler.setHttpMethod(HttpMethod.POST);
		return handler;
	}
	
	@Autowired
	AuthTokenRequestChannelGateway authTokenReqGateway;
	
	@Autowired
	UcicIdFetchChannelGateway ucicIdFetchGateway;
	
	@ServiceActivator(inputChannel = "ucicFetchChannel")
	public String returnMsg(Message<?> message) {
		UcicIdFetchModel idFetchModel = (UcicIdFetchModel) message.getPayload();
		
		AuthenticationInputModel authenticationInputModel = new AuthenticationInputModel(appUserName, appPass, 
				idFetchModel.getMpNo(), idFetchModel.getFdglCode(), idFetchModel.getEmpName(), idFetchModel.getEmailId(), idFetchModel.getDesignation());
		Message<AuthResponseModel> authTokenResponse = authTokenReqGateway.fetchAuthToken(MessageBuilder.withPayload(authenticationInputModel).build());
		AuthResponseModel responseModel = authTokenResponse.getPayload();
		UcicFetchModel fetchModel = new UcicFetchModel(responseModel.authToken, idFetchModel.getCustomerName(), idFetchModel.getCustomerDob(), 
				idFetchModel.getCustomerMobileNo(), idFetchModel.getCustomerIdNo());
		
		Message<String> ucicId = ucicIdFetchGateway.fetchUcicId(MessageBuilder.withPayload(fetchModel).build());
		
		return ucicId.getPayload();
	}
	
//	RABBITMQ POC RECIEVE
	@Bean
	public AbstractMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
		messageListenerContainer.setQueueNames("mgl.fincore.mock");
		return messageListenerContainer;
	}
	
	@Bean
	public AmqpInboundChannelAdapter inboundChannelAdapter(AbstractMessageListenerContainer messageListenerContainer) {
		AmqpInboundChannelAdapter amqpInboundChannelAdapter = new AmqpInboundChannelAdapter(messageListenerContainer);
		amqpInboundChannelAdapter.setOutputChannelName("fromRabbit");
		return amqpInboundChannelAdapter;
	}
	
	@Bean
	public MessageChannel fromRabbit() {
		return new DirectChannel();
	}
	
	@Bean
	@Transformer(inputChannel = "fromRabbit", outputChannel = "createLoanRequest")
	public JsonToObjectTransformer jsonToObjectTransformer() {
		return new JsonToObjectTransformer(CreateLoanModel.class);
	}
	
	@Bean
	public MessageChannel createLoanRequest() {
		return new DirectChannel();
	}
	
	@ServiceActivator(inputChannel = "createLoanRequest")
	public void processLoanReq(Message<CreateLoanModel> message) {
		System.out.println(message.getPayload());
	}
}
