package com.jsp.AirLink.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.AirLink.dto.BookingDto;
import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.model.Booking;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;
import com.jsp.AirLink.service.BookingService;

@RestController
@RequestMapping("booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/crte")
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody BookingDto bookingDto){
		return bookingService.addBooking(bookingDto);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {
	    return bookingService.getAllBookings();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable int id) {
	    return bookingService.getBookingById(id);
	}
	
	@GetMapping("/flight/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(@PathVariable int flightId) {
	    return bookingService.getBookingByFlight(flightId);
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable String date) {

	    LocalDate bookingDate = LocalDate.parse(date); //String->LocalDate

	    return bookingService.getBookingByDate(bookingDate);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable BookingStatus status) {
	    return bookingService.getBookingByStatus(status);
	}
	
	@GetMapping("/{bookingId}/passengers")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByBookingId(@PathVariable int bookingId) {
	    return bookingService.getPassengersByBookingId(bookingId);
	}

	@PutMapping("/status/{id}")
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(@PathVariable int id, @RequestParam BookingStatus status) {
	    return bookingService.updateBookingStatus(id, status);
	}
	
	@GetMapping("/{bookingId}/payment")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsByBookingId(@PathVariable int bookingId) {
	    return bookingService.getPaymentDetailsByBookingId(bookingId);
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable int bookingId) {
	    return bookingService.deleteBookingById(bookingId);
	}

}
