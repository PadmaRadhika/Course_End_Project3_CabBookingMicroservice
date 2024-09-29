package com.example.CabBookingMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.example.CabBookingMicroservice.model.CabBooking;

public interface CabBookingService {
	public static final String FROM_LOCATION = "fromLocation";
	public static final String To_LOCATION = "toLocation";
	public CabBooking bookCab(CabBooking cab);
	public Optional<CabBooking> getCabBookingById(Integer id);
	public List<CabBooking> getAllBookedCabs();
	public void deleteCabBooking(Integer id);	 
}
