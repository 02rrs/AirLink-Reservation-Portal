package com.jsp.AirLink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.AirLink.dao.FlightDao;
import com.jsp.AirLink.dto.FlightDto;
import com.jsp.AirLink.exception.FlightNotFoundException;
import com.jsp.AirLink.model.Flight;
import com.jsp.AirLink.resource.ResponseStructure;




@Service
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDao flightDao;
	
	@Override
	public ResponseEntity<ResponseStructure<Flight>> addFlight(FlightDto flightDto){
		Flight flight = new Flight();
        flight.setFlightName(flightDto.getFlightName());
        flight.setSource(flightDto.getSource());
        flight.setDestination(flightDto.getDestination());
        flight.setDepartureDateTime(flightDto.getDepartureDateTime());
        flight.setArrivalDateTime(flightDto.getArrivalDateTime());
        flight.setPrice(flightDto.getPrice());
        
        
        Flight savedFlight = flightDao.saveFlight(flight);

        ResponseStructure<Flight> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Flight added successfully");
        response.setData(savedFlight);

        return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights() {
		List<Flight> flights=flightDao.getAllFlight();
		
		 if(flights.isEmpty()) {
		        throw new FlightNotFoundException("No Flights Available");
		 }
		
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("All Flights fetched successfully");
	    response.setData(flights);

	    return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(int id) {
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		
		response.setStatus(HttpStatus.OK.value());
        response.setMessage("Flight data for ID " + id + " retrieved successfully");
        response.setData(flightDao.getFlightById(id));
        return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySrcAndDest(String src, String dest) {
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		
		response.setStatus(HttpStatus.OK.value());
        response.setMessage("Flight information for " + src + " to "+dest+" is fetched");
        response.setData(flightDao.getFlightBySrcAndDest(src, dest));
        return new ResponseEntity<ResponseStructure<List<Flight>>>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Optional<Flight>>> getFlightByName(String name) {
		ResponseStructure<Optional<Flight>> response=new ResponseStructure<Optional<Flight>>();
		
		response.setStatus(HttpStatus.OK.value());
        response.setMessage("Flight information for "+name+" is fetched");
        response.setData(flightDao.getFlightByName(name));
        return new ResponseEntity<ResponseStructure<Optional<Flight>>>(response,HttpStatus.OK);
	
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(FlightDto flightDto) {
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();

		Flight existingFlight = flightDao.getFlightById(flightDto.getId());

	    existingFlight.setFlightName(flightDto.getFlightName());
	    existingFlight.setSource(flightDto.getSource());
	    existingFlight.setDestination(flightDto.getDestination());
	    existingFlight.setDepartureDateTime(flightDto.getDepartureDateTime());
	    existingFlight.setArrivalDateTime(flightDto.getArrivalDateTime());
	    existingFlight.setPrice(flightDto.getPrice());
	    
	    Flight updatedFlight = flightDao.updateFlight(existingFlight);

	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("Flight updated successfully");
	    response.setData(updatedFlight);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<String>> deleteFlightById(int id) {
		Flight existingflight = flightDao.getFlightById(id);
		
		flightDao.deleteFlight(existingflight);

	    ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("Flight deleted successfully");
	    response.setData("Success");

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Page<Flight>>> getAllFlights(int page, int size, String sortByField){
		ResponseStructure<Page<Flight>> response=new ResponseStructure<Page<Flight>>();
		
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Flights fectched successfully for PageNumber "+page+" SizeOfPage "+size);
		response.setData(flightDao.getAllFLight(page, size, sortByField));
		
		return new ResponseEntity<ResponseStructure<Page<Flight>>>(response, HttpStatus.OK);
	}
}