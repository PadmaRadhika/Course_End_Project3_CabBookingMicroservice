package com.example.CabBookingMicroservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CabBookingMicroservice.dao.BookingCabRepository;
import com.example.CabBookingMicroservice.model.CabBooking;

@Service
public class CabBookingServiceImpl implements CabBookingService{
	
	@Autowired
	private BookingCabRepository bookingCabRespository;

	/** This method saves cab booking information in the database. 
	 *
	 */
	@Override
	public CabBooking bookCab(CabBooking cab) {
		// TODO Auto-generated method stub
		return bookingCabRespository.save(cab);
	}

	/** This method gets cab booking information using the id passed as parameter.
	 *
	 */
	@Override
	public Optional<CabBooking> getCabBookingById(Integer id) {
		// TODO Auto-generated method stub
		return bookingCabRespository.findById(id);
	}

	/**This method retrieves all the cab booking records from the database.
	 *
	 */
	@Override
	public List<CabBooking> getAllBookedCabs() {
		// TODO Auto-generated method stub
		return new ArrayList<CabBooking>((Collection<? extends CabBooking>) bookingCabRespository.findAll());
	}

	/** This method deletes a cab booking record from database using the id passed as a parameter.
	 *
	 */
	@Override
	public void deleteCabBooking(Integer id) {
		// TODO Auto-generated method stub
		bookingCabRespository.deleteById(id);
	}	

}
