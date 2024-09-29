package com.example.CabBookingMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CabBookingMicroservice.model.CabBooking;
import com.example.CabBookingMicroservice.service.CabBookingService;

@Controller
public class CabBookingViewController {
	@Autowired
	private CabBookingService cabBookingService;
	/**This is the end point to get to the home page in the navigation bar.
	 * This method returns index.html as the view.
	 * @return
	 */
	@GetMapping("/")
	public String home() {
		return "index";
	}
	/**This end point is to navigate to book-cab.html page from the navigation bar.
	 * This method uses CabBooking entity bean as model and loads the page with the
	 * corresponding matching fields using thymeleaf template.
	 * @param model
	 * @return
	 */
	@GetMapping("/cabBooking")
	public String showCabBookingForm(Model model) {
		model.addAttribute("cabBooking", new CabBooking());
		return "book-cab";
	}
	/**This End point is to get to the calcuate-fare.html in the navigation bar.
	 * This method returns calculate-fare.html as view.
	 * @return
	 */
	@GetMapping("/calculateAllCabsFare")
	public String showAllCabsFareForm() {		
		return "calculate-fare";
	}
	/**This end point is to create CabBooking object in the database.
	 * This method returns resultsview.html as the view with the created cab booking information.
	 * @param cabBooking
	 * @param model
	 * @return
	 */
	@PostMapping("/addCabBooking")
    public String addCabBooking(@ModelAttribute CabBooking cabBooking, Model model) {    	
        CabBooking bookedCab = cabBookingService.bookCab(cabBooking);        
        model.addAttribute("bookingDetails", bookedCab);        
        return "resultview"; // Redirect to result view page
    }

}
