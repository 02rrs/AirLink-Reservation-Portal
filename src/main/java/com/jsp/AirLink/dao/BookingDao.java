package com.jsp.AirLink.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.exception.BookingNotFoundException;
import com.jsp.AirLink.exception.FlightNotFoundException;
import com.jsp.AirLink.model.Booking;
import com.jsp.AirLink.model.Passenger;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.repository.BookingRepository;

@Repository
public class BookingDao {
	
	@Autowired
	private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking getBookingById(int id) {
        Optional<Booking> opt = bookingRepository.findById(id);
        if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new FlightNotFoundException("Flight not available");
		}
    }
    
    public List<Booking> getBookingByFlight(int flightId) {
    	List<Booking> bookings = bookingRepository.findByFlight_Id(flightId);
        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("No bookings found for flight ID " + flightId);
        }
        return bookings;
    }
    
    public List<Booking> getBookingByDate(LocalDate date) {

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Booking> bookings = bookingRepository.findByBookingDateTimeBetween(startOfDay, endOfDay);

        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("No bookings found on date: " + date);
        }

        return bookings;
    }
    
    public List<Booking> getBookingByStatus(BookingStatus status) {

        List<Booking> bookings = bookingRepository.findByStatus(status);

        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("No bookings found with status: " + status);
        }

        return bookings;
    }
    
    public List<Passenger> getPassengersByBookingId(int bookingId) {

        Booking booking = getBookingById(bookingId);

        List<Passenger> passengers = booking.getPassengers();

        if (passengers == null || passengers.isEmpty()) {
            throw new BookingNotFoundException("No passengers found for booking ID " + bookingId);
        }

        return passengers;
    }
    
    public Payment getPaymentDetailsByBookingId(int bookingId) {

        Booking booking = getBookingById(bookingId);

        Payment payment = booking.getPayment();
        if (payment == null) {
            throw new BookingNotFoundException("Payment details not found for booking ID " + bookingId);
        }

        return payment;
    }
    
    public void deleteBookingById(int bookingId) {
        Booking booking = getBookingById(bookingId);
        
        if (booking == null) {
            throw new BookingNotFoundException("No bookings found for "+bookingId+"");
        }
        
        bookingRepository.delete(booking);           
    }


}
