package org.advcomprog.hotelbooking.hotelbookingsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Payment implements Displayable {
    private final String paymentId;
    private final String bookingId;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    
    public enum PaymentMethod {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL
    }
    
    public enum PaymentStatus {
    SUCCESSFUL,
    FAILED,
    PENDING,
    REFUNDED
    }
    
    public Payment(String paymentId, String bookingId, double amount, PaymentMethod method, PaymentStatus status) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }
    
    void setPaymentStatus(PaymentStatus paymentStatus) {
        status = paymentStatus;
    }
   
    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
    public void displayInfo() {
        System.out.println("========== Payment Details ==========");
        System.out.println("Payment ID   : " + paymentId);
        System.out.println("Booking ID   : " + bookingId);
        System.out.printf("Amount       : $%.2f%n", amount);
        System.out.println("Method       : " + method.name().replace("_", " "));
        System.out.println("Status       : " + status.name());
        System.out.println("=====================================");
    }
}
