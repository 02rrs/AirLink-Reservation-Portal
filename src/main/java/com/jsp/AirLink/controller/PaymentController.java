package com.jsp.AirLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.AirLink.dto.PaymentDto;
import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;
import com.jsp.AirLink.service.PaymentService;
@RestController
@RequestMapping("/pay")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/record")
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(@RequestBody PaymentDto paymentDto) {
		return paymentService.recordPayment(paymentDto);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments() {
		return paymentService.getAllPayments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable int id) {
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(@PathVariable PaymentStatus status) {
		return paymentService.getPaymentByStatus(status);
	}

	@GetMapping("/mode/{method}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(@PathVariable ModeOfPayment method) {
		return paymentService.getPaymentByMode(method);
	}

	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(@PathVariable int bookingId) {
		return paymentService.getPaymentByBookingId(bookingId);
	}

	@PutMapping("/updateStatus/{id}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable int id, @PathVariable PaymentStatus status) {
		return paymentService.updatePaymentStatus(id, status);
	}

	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentsGreaterThan(@PathVariable double amount) {
		return paymentService.getPaymentsGreaterThan(amount);
	}
}
