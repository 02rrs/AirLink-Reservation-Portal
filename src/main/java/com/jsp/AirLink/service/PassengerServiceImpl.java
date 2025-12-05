package com.jsp.AirLink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.AirLink.dao.PassengerDao;
import com.jsp.AirLink.dto.PassengerDto;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.resource.ResponseStructure;


@Service
public class PassengerServiceImpl implements PassengerService{
	
	@Autowired
	private PassengerDao passengerDao;
	
    @Override
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengers() {

        List<Passenger> passengers = passengerDao.getAllPassenger();

        ResponseStructure<List<Passenger>> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("All passengers fetched successfully");
        structure.setData(passengers);

        return new ResponseEntity<ResponseStructure<List<Passenger>>>(structure, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(int id) {
    	ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
    	
    	response.setStatus(HttpStatus.OK.value());
        response.setMessage("Passenger data for ID " + id + " retrieved successfully");
        response.setData(passengerDao.getPassengerById(id));
        return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByContact(long contactNo) {

        List<Passenger> passengers = passengerDao.getPassengerByContact(contactNo);

        ResponseStructure<List<Passenger>> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Passengers fetched successfully using contact number");
        structure.setData(passengers);

        return new ResponseEntity<ResponseStructure<List<Passenger>>>(structure, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(int id, PassengerDto passengerDto) {

        Passenger existingPassenger = passengerDao.getPassengerById(id);

        existingPassenger.setName(passengerDto.getName());
        existingPassenger.setAge(passengerDto.getAge());
        existingPassenger.setGender(passengerDto.getGender());
        existingPassenger.setContactNo(passengerDto.getContactNo());
        existingPassenger.setSeatNo(passengerDto.getSeatNo());

        Passenger updatedPassenger = passengerDao.updatePassenger(existingPassenger);

        ResponseStructure<Passenger> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Passenger information updated successfully");
        structure.setData(updatedPassenger);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(int flightId) {

        List<Passenger> passengers = passengerDao.getPassengersByFlightId(flightId);

        ResponseStructure<List<Passenger>> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Passengers fetched successfully for flight id: " + flightId);
        structure.setData(passengers);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

}
