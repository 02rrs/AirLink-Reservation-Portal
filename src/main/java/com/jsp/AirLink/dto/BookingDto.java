package com.jsp.AirLink.dto;

import java.util.List;

import com.jsp.AirLink.enums.BookingStatus;
import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;

public class BookingDto {
	
	private Integer flightId;
	private Double paymentAmount;
	private Integer id;
	private BookingStatus status;
	private Double amount;
	private PaymentStatus paymentStatus;
	private ModeOfPayment paymentMethod;
	
	private List<PassengerDto> passengersDto;
	
	
	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public ModeOfPayment getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(ModeOfPayment paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<PassengerDto> getPassengers() {
		return passengersDto;
	}

	public void setPassengers(List<PassengerDto> passengersDto) {
		this.passengersDto = passengersDto;
	}
	
}
