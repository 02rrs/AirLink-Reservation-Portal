package com.jsp.AirLink.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.AirLink.dto.PaymentDto;
import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;

public interface PaymentService {

	ResponseEntity<ResponseStructure<Payment>> recordPayment(PaymentDto paymentDto);

	ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments();

	ResponseEntity<ResponseStructure<Payment>> getPaymentById(int id);

	ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(PaymentStatus status);

	ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(ModeOfPayment method);

	ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(int bookingId);

	ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(int id, PaymentStatus status);

	ResponseEntity<ResponseStructure<List<Payment>>> getPaymentsGreaterThan(double amount);

}
