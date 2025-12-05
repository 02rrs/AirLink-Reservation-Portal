package com.jsp.AirLink.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.AirLink.exception.PassengerIdNotFoundException;
import com.jsp.AirLink.exception.PassengerNotFoundException;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.repository.PassengerRepository;

@Repository
public class PassengerDao {
	@Autowired
	private PassengerRepository passengerRepository;

	public List<Passenger> getAllPassenger() {
		return passengerRepository.findAll();
	}

	public Passenger getPassengerById(int id) {
		Optional<Passenger> opt = passengerRepository.findById(id);

		if (opt.isPresent()) {
			return opt.get();
		} 
		else {
			throw new PassengerIdNotFoundException("Passenger id not found!!");
		}
	}

	public List<Passenger> getPassengerByContact(long contactNo) {
		List<Passenger> passengers = passengerRepository.findByContactNo(contactNo);

		if (passengers.isEmpty()) {
			throw new PassengerNotFoundException("No passengers found with contact number: " + contactNo);
		} 
		else {
			return passengers;
		}
	}

	public Passenger updatePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
   	
	public List<Passenger> getPassengersByFlightId(int flightId) {
	    List<Passenger> passengers = passengerRepository.findByBooking_Flight_Id(flightId);

	    if (passengers.isEmpty()) {
	        throw new PassengerNotFoundException("No passengers found for flight id: " + flightId);
	    } 
	    else {
	        return passengers;
	    }
	}

}
