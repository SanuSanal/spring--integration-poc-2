package com.poc.mglApplication.integration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.client.RestTemplate;


@Configuration
public class FetchUcicDetails {

	@Bean
	public MessageChannel fetchUcicIdTransInpChannel() {
		return new DirectChannel();
	}

	@Bean
	@Transformer(inputChannel = "fetchUcicIdTransInpChannel", outputChannel = "fetchUcicIdTransOptChannel")
	public ObjectToJsonTransformer objToJsonAuthTknInput() {
		return new ObjectToJsonTransformer();
	}

	@Bean
	public MessageChannel fetchUcicIdTransOptChannel() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "fetchUcicIdTransOptChannel")
	@Bean
	public HttpRequestExecutingMessageHandler getAuthToken() {
		HttpRequestExecutingMessageHandler handler = new HttpRequestExecutingMessageHandler("http://localhost:8082/api/v1/fetchUcic");
		handler.setExpectedResponseType(String.class);
		handler.setHttpMethod(HttpMethod.POST);
		return handler;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
//	RABBITMQ SERVER POC
	
	@Bean
    public MessageChannel loanRequest() {
        return new DirectChannel();
    }
	
	@Bean
    @Transformer(inputChannel = "loanRequest", outputChannel = "toRabbit")
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer();
    }
	
	@Bean
    public SubscribableChannel toRabbit() {
        return new DirectChannel();
    }
	
	@Bean
    public EventDrivenConsumer rabbitConsumer(@Qualifier("toRabbit") SubscribableChannel channel, @Qualifier("rabbitOutboundEndpoint") MessageHandler handler) {
        return new EventDrivenConsumer(channel, handler);
    }
	
	@Bean
    public AmqpOutboundEndpoint rabbitOutboundEndpoint(AmqpTemplate amqpTemplate) {
        AmqpOutboundEndpoint adapter = new AmqpOutboundEndpoint(amqpTemplate);
        adapter.setRoutingKey("mgl.fincore.mock");
        return adapter;
    }
	
}
