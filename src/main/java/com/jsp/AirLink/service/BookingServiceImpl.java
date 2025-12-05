package com.jsp.AirLink.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.AirLink.dao.BookingDao;
import com.jsp.AirLink.dao.FlightDao;
import com.jsp.AirLink.dto.BookingDto;
import com.jsp.AirLink.dto.PassengerDto;
import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.exception.PassengerNotFoundException;
import com.jsp.AirLink.model.Booking;
import com.jsp.AirLink.model.Flight;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private FlightDao flightDao;
	
	@Override
	public ResponseEntity<ResponseStructure<Booking>> addBooking(BookingDto bookingDto) {
		Flight flight = flightDao.getFlightById(bookingDto.getFlightId());
		
		Booking booking = new Booking();

	    if (bookingDto.getPaymentStatus().equals(PaymentStatus.PAID)) {
	        booking.setStatus(BookingStatus.CONFIRMED);
	    } 
	    else if (bookingDto.getPaymentStatus().equals(PaymentStatus.PENDING)) {
	        booking.setStatus(BookingStatus.PENDING);
	    } 
	    else {
	        booking.setStatus(BookingStatus.CANCELLED);
	    }

	    booking.setFlight(flight);
	    
	    List<Passenger> passengers = new ArrayList<>();
	    if (bookingDto.getPassengers() == null || bookingDto.getPassengers().isEmpty()) {
	    	throw new PassengerNotFoundException("Booking cannot be procced");
	    }
	    
	    for (PassengerDto passengerDto : bookingDto.getPassengers()) {
	        Passenger passenger = new Passenger();
	        passenger.setName(passengerDto.getName());
	        passenger.setAge(passengerDto.getAge());
	        passenger.setGender(passengerDto.getGender());
	        passenger.setContactNo(passengerDto.getContactNo());
	        passenger.setSeatNo(passengerDto.getSeatNo());
	        passenger.setBooking(booking);

	        passengers.add(passenger);
	    }
	    
	    booking.setPassengers(passengers);
	    
	    double totalAmount = flight.getPrice() * passengers.size();
	    
	    Payment payment = new Payment();
	    payment.setPaymentAmount(totalAmount);
	    payment.setPaymentDateTime(LocalDateTime.now());
	    payment.setPaymentStatus(bookingDto.getPaymentStatus());
	    payment.setPaymentMethod(bookingDto.getPaymentMethod());
	    payment.setBooking(booking);

	    booking.setPayment(payment);
	    
	    Booking savedBooking = bookingDao.saveBooking(booking);
	    
	    ResponseStructure<Booking> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.CREATED.value());

	    if (booking.getStatus().equals(BookingStatus.CONFIRMED)) {
	        response.setMessage("Booking confirmed and payment successful.");
	    } 
	    else if (booking.getStatus().equals(BookingStatus.PENDING)) {
	        response.setMessage("Booking created, payment is pending.");
	    } 
	    else {
	        response.setMessage("Booking failed due to payment failure.");
	    }
	    response.setData(savedBooking);

	    return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {

	    List<Booking> bookings = bookingDao.getAllBookings();

	    ResponseStructure<List<Booking>> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("All bookings fetched successfully");
	    structure.setData(bookings);

	    return new ResponseEntity<ResponseStructure<List<Booking>>>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(int id) {

	    Booking booking = bookingDao.getBookingById(id);

	    ResponseStructure<Booking> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Booking details fetched successfully");
	    structure.setData(booking);

	    return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(int flightId) {

	    List<Booking> bookings = bookingDao.getBookingByFlight(flightId);

	    ResponseStructure<List<Booking>> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Bookings for flight id " + flightId + " fetched successfully");
	    structure.setData(bookings);

	    return new ResponseEntity<ResponseStructure<List<Booking>>>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDate date) {

	    List<Booking> bookings = bookingDao.getBookingByDate(date);

	    ResponseStructure<List<Booking>> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Bookings for date " + date + " fetched successfully");
	    structure.setData(bookings);

	    return new ResponseEntity<ResponseStructure<List<Booking>>>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status) {

	    List<Booking> bookings = bookingDao.getBookingByStatus(status);

	    ResponseStructure<List<Booking>> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Bookings with status " + status + " fetched successfully");
	    structure.setData(bookings);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByBookingId(int bookingId) {

	    List<Passenger> passengers = bookingDao.getPassengersByBookingId(bookingId);

	    ResponseStructure<List<Passenger>> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Passengers fetched successfully for booking id: " + bookingId);
	    structure.setData(passengers);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(int id, BookingStatus status) {

	    Booking booking = bookingDao.getBookingById(id); 
	    booking.setStatus(status); 

	    Booking updatedBooking = bookingDao.saveBooking(booking);
	    
	    ResponseStructure<Booking> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Booking status updated successfully");
	    structure.setData(updatedBooking);
	    
	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsByBookingId(int bookingId) {

	    Payment payment = bookingDao.getPaymentDetailsByBookingId(bookingId);

	    ResponseStructure<Payment> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Payment details fetched successfully for booking id: " + bookingId);
	    structure.setData(payment);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<String>> deleteBookingById(int bookingId) {

	    bookingDao.deleteBookingById(bookingId);

	    ResponseStructure<String> structure = new ResponseStructure<>();
	    structure.setStatus(HttpStatus.OK.value());
	    structure.setMessage("Booking deleted successfully");
	    structure.setData("Success");

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}


}
