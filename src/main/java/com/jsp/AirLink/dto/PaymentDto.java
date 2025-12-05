package com.jsp.AirLink.dto;

import com.jsp.AirLink.enums.ModeOfPayment;
import com.jsp.AirLink.enums.PaymentStatus;

public class PaymentDto {
	private Double paymentAmount;
    private PaymentStatus paymentStatus;
    private ModeOfPayment paymentMethod;
    private Integer bookingId;

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
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

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
