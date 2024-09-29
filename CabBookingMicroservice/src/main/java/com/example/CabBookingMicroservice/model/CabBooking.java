package com.example.CabBookingMicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CabBooking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String fromLocation;
	private String toLocation;
	private String cabType;
	private Double fare;
	
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}	
	
	public CabBooking(Integer id, String fromLocation, String toLocation, String cabType, Double fare) {
		super();
		this.id = id;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.cabType = cabType;
		this.fare = fare;
	}
	public CabBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	@Override
	public String toString() {
		return "CabBooking [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", cabType="
				+ cabType + ", fare=" + fare + "]";
	}
	
}
