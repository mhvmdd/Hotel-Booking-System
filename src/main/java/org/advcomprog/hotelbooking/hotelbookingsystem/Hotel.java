/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prooject;

/**
 *
 * @author omara
 */

import com.mycompany.prooject.Booking.BookingStatus;
import com.mycompany.prooject.Payment.PaymentMethod;
import com.mycompany.prooject.Payment.PaymentStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> guests = new ArrayList<>();
    private ArrayList<User> admins = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();

    ///// Room Management /////
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room successfully added.");
    }

    public boolean removeRoom(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                rooms.remove(room);
                System.out.println("Room removed successfully.");
                return true;
            }
        }
        throw new IllegalArgumentException("Room with number " + roomNumber + " does not exist.");
    }

    public Room findRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        throw new IllegalArgumentException("Room with number " + roomNumber + " not found.");
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room) && booking.overlaps(checkIn, checkOut)) {
                return false;
            }
        }
        return true;
    }

    public void displayAllRooms() {
        System.out.println("List of all rooms:");
        for (Room room : rooms) {
            room.displayInfo();
        }
    }

    ///// Admin/User Management /////
    public void addUser(User user) {
        if (user instanceof Guest) {
            guests.add(user);
            System.out.println("Guest successfully added.");
        } else {
            admins.add(user);
            System.out.println("Admin successfully added.");
        }
    }

    public boolean removeUser(String userId, String password) {
       Guest guest = (Guest) findUserById(userId, password);
        if (guests.remove(guest)) {
            System.out.println("Guest removed successfully.");
            return true;
        }
        throw new IllegalArgumentException("User not found or cannot be removed.");
    }

    public User findUserById(String userId, String password) {
        for (User guest : guests) {
            if (guest.getUserId().equals(userId) && guest.getPassword().equals(password)) {
                return guest;
            }
        }
        throw new IllegalArgumentException("Guest with provided credentials not found.");
    }

    public void displayAllGuests() {
        System.out.println("List of all guests:");
        for (User guest : guests) {
            guest.DisplayProfile();
        }
    }

    ///// Booking Management /////
    public boolean createBooking(User user, String roomNumber,
                                 LocalDate checkIn, LocalDate checkOut, PaymentMethod paymentMethod) {
        Guest guest = (Guest) findUserById(user.getUserId(), user.getPassword());
        Room room = findRoomByNumber(roomNumber);

        if (!isRoomAvailable(room, checkIn, checkOut)) {
            throw new IllegalStateException("Room is not available for the selected dates.");
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        if (days <= 0) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }
        String bookingID = "BKG-" + UUID.randomUUID().toString().substring(0,5);
        Booking newBooking = new Booking(bookingID, guest, room, checkIn, checkOut);
        double amount = days * room.getPrice();

        bookings.add(newBooking);

        if (!recordPayment(bookingID, amount, paymentMethod)) {
            bookings.remove(newBooking); // Rollback
            throw new IllegalStateException("Payment failed. Booking was not completed.");
        }

        System.out.println("Booking created successfully: " + newBooking.getBookingId());
        return true;
    }

    public boolean cancelBooking(String bookingID) {
        Booking booking = findBookingById(bookingID);
        if (booking == null) {
            throw new IllegalArgumentException("Booking with ID " + bookingID + " not found.");
        }
        Room roomTemp = findRoomByNumber(booking.getRoom().getRoomNumber());
        roomTemp.setAvailable(true);
        bookings.remove(booking);
        System.out.println("Booking canceled successfully.");
        return true;
    }

    public Booking findBookingById(String bookingID) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingID)) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Booking with ID " + bookingID + " not found.");
    }

    public void displayAllBookings() {
        System.out.println("List of all bookings:");
        for (Booking booking : bookings) {
            System.out.println("name : " + booking.getGuest().getName());
            booking.displayInfo();
        }
    }

    public void displayBookingsForGuest(String guestID) {
        System.out.println("Bookings for guest ID: " + guestID);
        for (Booking booking : bookings) {
            if (booking.getGuest().getUserId().equals(guestID)) {
                booking.displayInfo();
            }
        }
    }
    public Booking findBookingByGuestId(String guestID) {
        for (Booking booking : bookings) {
            if (booking.getGuest().getUserId().equals(guestID)) {
                return booking;
            }
        }
        throw new IllegalArgumentException("Guest with ID " + guestID + " not found.");
    }
        

    public boolean performCheckIn(String bookingID) {
        Booking booking = findBookingById(bookingID);
        booking.setStatus(BookingStatus.CHECKED_IN);
        System.out.println("Check-in completed for booking ID: " + bookingID);
        return true;
    }

    public boolean performCheckOut(String bookingID) {
        Booking booking = findBookingById(bookingID);
        booking.setStatus(BookingStatus.CHECKED_OUT);
        System.out.println("Check-out completed for booking ID: " + bookingID);
        return true;
    }

    ///// Payment Management /////
    public boolean recordPayment(String bookingID, double amount, PaymentMethod paymentMethod) {
        Booking booking = findBookingById(bookingID);
        if (booking == null) {
            throw new IllegalArgumentException("Booking for payment not found.");
        }
        String paymentID = "PAY" + bookingID;
        Payment payment = new Payment(paymentID, bookingID, amount, paymentMethod, PaymentStatus.PENDING);
        payments.add(payment);
        System.out.println("Payment recorded. Payment ID: " + paymentID);
        return true;
    }

    private boolean processRefund(String paymentID) {
        Payment payment = getPaymentById(paymentID);
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        System.out.println("Refund processed successfully.");
        return true;
    }

    public Payment getPaymentById(String paymentID) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentID)) {
                return payment;
            }
        }
        throw new IllegalArgumentException("Payment with ID " + paymentID + " not found.");
    }

    public void confirmation(String confirm , String paymentID , String bookingID) {       
        Payment payment = getPaymentById(paymentID);
        if(confirm.equals("ok")) {
            payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
            // Update room availability
            Booking booking = findBookingById(bookingID);
            booking.getRoom().setAvailable(false);
        }
        else{
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payments.remove(payment);
            cancelBooking(bookingID);
        }
    }


    public void displayAllPayments() {
        System.out.println("List of all payments:");
        for (Payment payment : payments) {
                payment.displayInfo();
        }
    }

    public boolean displayPaymentsForBooking(String bookingID) {
        boolean found = false;
        for (Payment payment : payments) {
            if (payment.getBookingId().equals(bookingID)) {
                System.out.println("Payment for booking UUID " + bookingID + ": " + payment);
                found = true;
            }
        }
        if (!found) throw new IllegalArgumentException("No payment found for booking ID " + bookingID);
        return true;
    }

    ///// Offer Management /////
    public boolean addPromoCode(String bookingId, String promoCode) {
        boolean found = false;
        Payment paymentTemp = null;
        for (Payment payment : payments) {
            if (payment.getBookingId().equals(bookingId)) {
                //System.out.println("Payment for booking UUID " + bookingID + ": " + payment);
                paymentTemp=payment;
                found = true;  
            }
        }
        if(found || paymentTemp != null){
            double originalAmount = paymentTemp.getAmount();
            switch (promoCode) {
                case "CSE27":
                    paymentTemp.setOffer(20); // 20% discount
                    break;
                case "ASU30":
                    paymentTemp.setOffer(10); // 10% discount
                    break;
                default:
                    throw new IllegalArgumentException("Invalid promo code.");
            }
            System.out.println("Promo code applied successfully.");
            return true;
        }
        else{
            throw new IllegalArgumentException("No payment found for booking ID " + bookingId);
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<User> getGuests() {
        return guests;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }
}
