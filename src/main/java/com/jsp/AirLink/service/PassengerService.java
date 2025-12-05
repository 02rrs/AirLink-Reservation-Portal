package com.jsp.AirLink.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.AirLink.dto.PassengerDto;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.resource.ResponseStructure;

public interface PassengerService {
	
	ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers();
	ResponseEntity<ResponseStructure<Passenger>> getPassengerById(int id);
	ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByContact(long contactNo);
	ResponseEntity<ResponseStructure<Passenger>> updatePassenger(int id, PassengerDto passengerDto);
	ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(int flightId);


}
