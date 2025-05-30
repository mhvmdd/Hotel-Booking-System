/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public class Admin extends User {
    public Admin(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public void DisplayProfile() {
        System.out.println("========== Admin Profile ==========");
        System.out.println("Admin ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("=================================");
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}
