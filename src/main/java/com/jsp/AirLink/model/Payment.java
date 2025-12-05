package com.jsp.AirLink.model;

import java.time.LocalDateTime;

import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double paymentAmount;
	private LocalDateTime paymentDateTime;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@Enumerated(EnumType.STRING)
	private ModeOfPayment paymentMethod;
	
	@OneToOne
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	public Payment() {}

	public Payment(Integer id, Double paymentAmount, LocalDateTime paymentDateTime, PaymentStatus paymentStatus,
			ModeOfPayment paymentMethod) {
		super();
		this.id = id;
		this.paymentAmount = paymentAmount;
		this.paymentDateTime = paymentDateTime;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
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

	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
}
