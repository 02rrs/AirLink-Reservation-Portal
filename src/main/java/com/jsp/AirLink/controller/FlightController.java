package com.jsp.AirLink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.AirLink.dto.FlightDto;
import com.jsp.AirLink.model.Flight;
import com.jsp.AirLink.resource.ResponseStructure;
import com.jsp.AirLink.service.FlightServiceImpl;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightServiceImpl flightService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody FlightDto flightDto) {
		return flightService.addFlight(flightDto);
	}
	
	@GetMapping("/gtal")
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		return flightService.getAllFlights();
	}
	
	@GetMapping("/gt/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable int id) {
		return flightService.getFlightById(id);
	}
	
	@GetMapping("/gtby/{src}/{dest}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySrcAndDest(@PathVariable String src, @PathVariable String dest) {
		return flightService.getFlightBySrcAndDest(src, dest);
	}
	
	@GetMapping("/gtnm/{name}")
	public ResponseEntity<ResponseStructure <Optional<Flight>>> getFlightByName(@PathVariable String name) {
		return flightService.getFlightByName(name);
	}
	
	@PutMapping("/updt/{id}")
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@PathVariable int id, @RequestBody FlightDto flightDto) {
		flightDto.setId(id);
		return flightService.updateFlight(flightDto);
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable int id) {
		return flightService.deleteFlightById(id);
	}
	
	@GetMapping("page/{page}/{size}/{field}")
	public ResponseEntity<ResponseStructure<Page<Flight>>> getAllFlight(@PathVariable int page, @PathVariable int size, @PathVariable String field) {
		return flightService.getAllFlights(page, size, field);
	}
}