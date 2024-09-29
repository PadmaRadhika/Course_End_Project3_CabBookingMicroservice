package com.example.CabBookingMicroservice.config;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CALCULATEFAREMICROSERVICE", url= "http://localhost:8084")
public interface FeignService {
	 @GetMapping("/calculateFare")
	    ResponseEntity<Map<String, String>> calculateFare(@RequestParam String fromLocation,
	            @RequestParam String toLocation, @RequestParam String cabType);
	 
	 @PostMapping("/displayFare")
	 ResponseEntity<Map<String, String>> displayAllCabsFare(@RequestParam String fromLocation,
	            @RequestParam String toLocation);
}
