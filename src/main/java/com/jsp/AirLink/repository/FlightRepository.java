package com.jsp.AirLink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.AirLink.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Integer>{
	
	List<Flight> findBySourceAndDestination(String source, String destination);
	
	Optional<Flight> findByFlightName(String name);
}