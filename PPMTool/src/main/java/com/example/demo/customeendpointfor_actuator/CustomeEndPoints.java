package com.example.demo.customeendpointfor_actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


// this we have to add in application.property in expouse ponits 
@Endpoint(id="healthendpoint")
@Component
public class CustomeEndPoints {
	@ReadOperation
	@Bean
	public String hi() {
		return "hi I am able to access you";
	}

}
