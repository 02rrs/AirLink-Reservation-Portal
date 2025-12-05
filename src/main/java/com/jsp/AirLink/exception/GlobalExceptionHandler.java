package com.jsp.AirLink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.AirLink.resource.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleFlightNotFoundException(FlightNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Flight not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FlightIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleFlightIdNotFoundException(FlightIdNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Flight not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBookingNotFoundException(BookingNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Booking not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePassengerNotFoundException(PassengerNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Passenger not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengerIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePassengerIdNotFoundException(PassengerIdNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Passenger id not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePaymentNotFoundException(PaymentNotFoundException e){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Payment not found");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
}
