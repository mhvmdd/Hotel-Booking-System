/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public class LuxuriousSuite extends Suite {
    public LuxuriousSuite(String roomNumber, int capacity, int NumberOfRooms, String imageUrl) {
        super(roomNumber, capacity, 150.0, NumberOfRooms, imageUrl);
        this.haslivingArea = true;
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Luxurious Suite");
        super.displayInfo();
    }
}
