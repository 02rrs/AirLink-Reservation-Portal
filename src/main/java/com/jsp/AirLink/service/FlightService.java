package com.jsp.AirLink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.jsp.AirLink.dto.FlightDto;
import com.jsp.AirLink.model.Flight;
import com.jsp.AirLink.resource.ResponseStructure;

public interface FlightService {
	public ResponseEntity<ResponseStructure<Flight>> addFlight(FlightDto flightDto); 
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights();
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(int id);
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySrcAndDest(String src, String dest);
	public ResponseEntity<ResponseStructure<Optional<Flight>>> getFlightByName(String name);
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(FlightDto flightDto);
	public ResponseEntity<ResponseStructure<String>> deleteFlightById(int id);
	public ResponseEntity<ResponseStructure<Page<Flight>>> getAllFlights(int page, int size, String sortByField);
	
}