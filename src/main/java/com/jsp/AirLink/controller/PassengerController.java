 package com.jsp.AirLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.AirLink.dto.PassengerDto;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.resource.ResponseStructure;
import com.jsp.AirLink.service.PassengerServiceImpl;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
    private PassengerServiceImpl passengerService;
	
	@GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable int id){
		return passengerService.getPassengerById(id);
	}
	
	@GetMapping("/cont/{contactNo}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByContact(@PathVariable long contactNo) {
	    return passengerService.getPassengerByContact(contactNo);
	}
	
	@PutMapping("/updte/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> updatePassengerInfo(@PathVariable int id, @RequestBody PassengerDto passengerDto) {
	    return passengerService.updatePassenger(id, passengerDto);
	}
	
	@GetMapping("/fgt/{flightId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(@PathVariable int flightId) {
	    return passengerService.getPassengersByFlight(flightId);
	}

}
