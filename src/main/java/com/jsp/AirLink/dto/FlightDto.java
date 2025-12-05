package com.jsp.AirLink.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FlightDto {
	 private Integer id;
	 private String flightName;
	 private String source;
	 private String destination;
	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime departureDateTime;
	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime arrivalDateTime;
	 private Double price;

	 public FlightDto() {}

	public FlightDto(Integer id, String flightName, String source, String destination, LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime, Double price) {
		super();
		this.id=id;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	 
	 
}
