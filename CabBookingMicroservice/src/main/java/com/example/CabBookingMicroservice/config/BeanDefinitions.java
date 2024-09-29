package com.example.CabBookingMicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.CabBookingMicroservice.model.CabBooking;
@Configuration
public class BeanDefinitions {
	@Bean
	public CabBooking cabBooking() {
		return new CabBooking();
	}

}
