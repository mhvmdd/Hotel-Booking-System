/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public class StandardRoom extends Room{
    public StandardRoom(String roomNumber, int capacity, String imageUrl) {
        super(roomNumber, capacity, 35.0, imageUrl);
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Standard Room");
        super.displayInfo();
    }
}
    
    