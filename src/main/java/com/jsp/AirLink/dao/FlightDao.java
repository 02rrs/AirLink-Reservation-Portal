package com.jsp.AirLink.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.AirLink.exception.FlightNotFoundException;
import com.jsp.AirLink.model.Flight;
import com.jsp.AirLink.repository.FlightRepository;

@Repository
public class FlightDao {
	@Autowired
	private FlightRepository flightRepository;
	
	public Flight saveFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
	public Flight getFlightById(int id) {
		Optional<Flight> opt=flightRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new FlightNotFoundException("Flight not available");
		}
	}
	
	public List<Flight> getFlightBySrcAndDest(String src,String dest){
		List<Flight> flights=flightRepository.findBySourceAndDestination(src, dest);
		if (flights.isEmpty()) {
	        throw new FlightNotFoundException("No flights available from " + src + " to " + dest);
		}
		else {
			return flights;
		}
	}
	
	public Optional<Flight> getFlightByName(String name){
		Optional<Flight> opt=flightRepository.findByFlightName(name);
		
		if(opt.isPresent()) {
			return opt;
		}
		else {
			throw new FlightNotFoundException("No flight by name "+name);
		}
	}
	
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public void deleteFlight(Flight flight) {
		flightRepository.delete(flight);;
	}
	
	public Page<Flight> getAllFLight(int page, int size, String sortByField){
		return flightRepository.findAll(PageRequest.of(page, size, Sort.by(sortByField).ascending()));
	}
	
	

}