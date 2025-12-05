package com.jsp.AirLink.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public List<Booking> findByFlight_Id(Integer flightId);
	public List<Booking> findByBookingDateTimeBetween(LocalDateTime start, LocalDateTime end);
	public List<Booking> findByStatus(BookingStatus status);
   
}
