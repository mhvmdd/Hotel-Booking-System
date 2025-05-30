/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public class JuniorSuite extends Suite {
    public JuniorSuite(String roomNumber, int capacity, int NumberOfRooms, String imageUrl) {
        super(roomNumber, capacity, 45.0, NumberOfRooms, imageUrl);
        this.haslivingArea = false;
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Junior Suite");
        super.displayInfo();
    }
}