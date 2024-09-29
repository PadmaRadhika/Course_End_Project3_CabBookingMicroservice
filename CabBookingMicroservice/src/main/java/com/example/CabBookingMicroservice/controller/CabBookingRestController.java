package com.example.CabBookingMicroservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.CabBookingMicroservice.config.FeignService;
import com.example.CabBookingMicroservice.model.CabBooking;
import com.example.CabBookingMicroservice.service.CabBookingService;

@Controller
@RequestMapping(value = "/cabBooking")
@Configuration
public class CabBookingRestController {

	@Autowired
	private FeignService feignService;
	@Autowired
	private CabBookingService cabBookingService;
	
	
	/**This method returns distance, duration and cab fare for the selected cab type/
	 * This method sends this information upon selecting a cab type radio button in
	 * book-cab.html to show the fare information to the user.
	 * @param formData
	 * @return
	 */
	@PostMapping("/cabType") // spring created
	public ResponseEntity<Map<String, String>> getCabType(@RequestBody Map<String, String> formData) {
		String fromLocation = formData.get(CabBookingService.FROM_LOCATION);
		String toLocation = formData.get(CabBookingService.To_LOCATION);
		String cabType = formData.get("cabType");
		ResponseEntity<Map<String, String>> response = feignService.calculateFare(fromLocation, toLocation, cabType);				
		return response;
	}
	
	
    /**REST API to book a cab (for API consumers)
     * This method is to test in postman if cabbookingservice method of bookCab is 
     * saving data in database.
     * @param cabBooking
     * @return
     */
    @PostMapping("/api/cabBooking")
    @ResponseBody
    public ResponseEntity bookCabApi(@RequestBody CabBooking cabBooking) {
        CabBooking cabSavedInDB = cabBookingService.bookCab(cabBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(cabSavedInDB);
    }
    /**This end point is  called from calcuate-fare.html to display all cab types' fare information
     * for the entered from and to locations.
     * @param formData
     * @return
     */
    @PostMapping("/displayFare")
    public ResponseEntity<Map<String, String>> getAllCabsFare(@RequestBody Map<String, String> formData){    	
    	String fromLocation = formData.get(CabBookingService.FROM_LOCATION);
		String toLocation = formData.get(CabBookingService.To_LOCATION);		
		ResponseEntity<Map<String, String>> response = feignService.displayAllCabsFare(fromLocation, toLocation);
		return response;
    }
    

	
}
