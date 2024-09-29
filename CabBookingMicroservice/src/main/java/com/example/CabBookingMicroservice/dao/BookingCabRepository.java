package com.example.CabBookingMicroservice.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.CabBookingMicroservice.model.CabBooking;

public interface BookingCabRepository extends CrudRepository<CabBooking, Integer>{

}
