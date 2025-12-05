package com.jsp.AirLink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.AirLink.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	List<Passenger> findByContactNo(Long contactNo);
    List<Passenger> findByBooking_Flight_Id(Integer flightId);
    
    @Query("SELECT p.seatNo FROM Passenger p WHERE p.booking.flight.id = :flightId")
    public List<String> findBookedSeatsByFlight(Integer flightId);
}