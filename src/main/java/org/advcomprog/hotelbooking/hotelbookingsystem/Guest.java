package org.advcomprog.hotelbooking.hotelbookingsystem;

public class Guest extends User {

    
    public Guest(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }
    
    @Override
    public void DisplayProfile() {
        System.out.println("========== Guest Profile ==========");
        System.out.println("Guest ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("=================================");
    }
    
    @Override
    public String getRole() {
        return "Guest";
    }
}
