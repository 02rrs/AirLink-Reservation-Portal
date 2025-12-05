package com.jsp.AirLink.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.exception.PaymentNotFoundException;
import com.jsp.AirLink.model.Payment;
import com.jsp.AirLink.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayment() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException("No payments found!");
        } 
        else {
            return payments;
        }
    }

    public Payment getPaymentById(int id) {
        Optional<Payment> opt = paymentRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        } 
        else {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
    }

    public List<Payment> getPaymentByStatus(PaymentStatus status) {
        List<Payment> list = paymentRepository.findByPaymentStatus(status);
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("No payments found with status: " + status);
        } 
        else {
            return list;
        }
    }

    public List<Payment> getPaymentByMode(ModeOfPayment method) {
        List<Payment> list = paymentRepository.findByPaymentMethod(method);
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("No payments found with payment method: " + method);
        } 
        else {
            return list;
        }
    }

    public Payment getPaymentByBookingId(int bookingId) {
        Payment payment = paymentRepository.findByBooking_Id(bookingId);

        if (payment == null) {
            throw new PaymentNotFoundException("No payment found for booking id: " + bookingId);
        } 
        else {
            return payment;
        }
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsGreaterThan(double amount) {
        List<Payment> list = paymentRepository.findByPaymentAmountGreaterThan(amount);
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("No payment found with amount more than: " + amount);
        } 
        else {
            return list;
        }
    }
}
