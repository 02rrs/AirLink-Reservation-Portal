package com.jsp.AirLink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;
import com.jsp.AirLink.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByPaymentStatus(PaymentStatus status);

    List<Payment> findByPaymentMethod(ModeOfPayment method);

    List<Payment> findByPaymentAmountGreaterThan(double amount);

    Payment findByBooking_Id(Integer bookingId);
}
