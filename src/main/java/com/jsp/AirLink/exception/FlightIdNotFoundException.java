package com.jsp.AirLink.exception;

public class FlightIdNotFoundException extends RuntimeException{
	public FlightIdNotFoundException(String message) {
		super(message);
	}
}
