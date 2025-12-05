package com.jsp.AirLink.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.AirLink.dao.BookingDao;
import com.jsp.AirLink.dao.PaymentDao;
import com.jsp.AirLink.dto.PaymentDto;
import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.model.Booking;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.resource.ResponseStructure;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private BookingDao bookingDao;

	@Override
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(PaymentDto paymentDto) {

		Booking booking = bookingDao.getBookingById(paymentDto.getBookingId());

		Payment payment = new Payment();
		payment.setPaymentAmount(paymentDto.getPaymentAmount());
		payment.setPaymentDateTime(LocalDateTime.now());
		payment.setPaymentStatus(paymentDto.getPaymentStatus());
		payment.setPaymentMethod(paymentDto.getPaymentMethod());
		payment.setBooking(booking);

		Payment savedPayment = paymentDao.savePayment(payment);

		ResponseStructure<Payment> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Payment recorded successfully");
		structure.setData(savedPayment);

		return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayments() {

		List<Payment> payments = paymentDao.getAllPayment();

		ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All payments fetched successfully");
		structure.setData(payments);

		return new ResponseEntity<ResponseStructure<List<Payment>>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(int id) {

		Payment payment = paymentDao.getPaymentById(id);

		ResponseStructure<Payment> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payment fetched successfully using ID");
		structure.setData(payment);

		return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(PaymentStatus status) {

		List<Payment> payments = paymentDao.getPaymentByStatus(status);

		ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payments fetched using payment status");
		structure.setData(payments);

		return new ResponseEntity<ResponseStructure<List<Payment>>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByMode(ModeOfPayment method) {

		List<Payment> payments = paymentDao.getPaymentByMode(method);

		ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payments fetched using payment method");
		structure.setData(payments);

		return new ResponseEntity<ResponseStructure<List<Payment>>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(int bookingId) {

		Payment payment = paymentDao.getPaymentByBookingId(bookingId);

		ResponseStructure<Payment> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payment fetched using booking id");
		structure.setData(payment);

		return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(int id, PaymentStatus status) {

		Payment payment = paymentDao.getPaymentById(id);
		payment.setPaymentStatus(status);

		Payment updatedPayment = paymentDao.updatePayment(payment);

		ResponseStructure<Payment> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payment status updated successfully");
		structure.setData(updatedPayment);

		return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentsGreaterThan(double amount) {

		List<Payment> payments = paymentDao.getPaymentsGreaterThan(amount);

		ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Payments fetched whose amount is greater than " + amount);
		structure.setData(payments);

		return new ResponseEntity<ResponseStructure<List<Payment>>>(structure, HttpStatus.OK);
	}
}
