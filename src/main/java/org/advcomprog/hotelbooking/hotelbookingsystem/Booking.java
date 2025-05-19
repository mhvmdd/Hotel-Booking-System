package org.advcomprog.hotelbooking.hotelbookingsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Booking implements Displayable {
    private final String bookingId;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private BookingStatus status; // e.g., "BOOKED", "CHECKED_IN", "CHECKED_OUT", "CANCELLED"
    
    public enum BookingStatus {
        BOOKED,
        CHECKED_IN,
        CHECKED_OUT,
        CANCELLED
    } 

    public Booking(String bookingId,Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // Check if this booking overlaps a given date range
    public boolean overlaps(LocalDate start, LocalDate end) {
        return !(checkOut.isBefore(start) || checkIn.isAfter(end));
    }

    public void displayInfo() {
        System.out.println("========== Booking Details ==========");
        System.out.println("Booking ID      : " + bookingId);
        System.out.println("Guest Name      : " + guest.getName());
        System.out.println("Guest ID        : " + guest.getUserId());
        System.out.println("Room Number     : " + room.getRoomNumber());
        System.out.printf("Price per night  : $%.2f%n", room.getPrice());
        System.out.println("Check-In        : " + checkIn);
        System.out.println("Check-Out       : " + checkOut);
        System.out.println("Status          : " + (status != null ? status.name() : "Not Set"));
        System.out.println("=====================================");
        }
    
}
