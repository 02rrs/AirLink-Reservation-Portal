package com.jsp.AirLink.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.AirLink.dto.BookingDto;
import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.model.Booking;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;

public interface BookingService {
	public ResponseEntity<ResponseStructure<Booking>> addBooking(BookingDto bookingDto);
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings();
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(int id);
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(int flightId);
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDate date);
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status);
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByBookingId(int bookingId);
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsByBookingId(int bookingId);
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(int id, BookingStatus status);
	public ResponseEntity<ResponseStructure<String>> deleteBookingById(int bookingId);

	

}
