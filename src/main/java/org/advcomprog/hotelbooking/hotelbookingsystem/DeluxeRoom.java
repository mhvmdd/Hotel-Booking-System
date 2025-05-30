/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public class DeluxeRoom extends Room{
    private boolean hasJacuzzi;
    private boolean hasMinibar;
    
    public DeluxeRoom(String roomNumber, int capacity, String imageUrl) {
        super(roomNumber, capacity, 75.0, imageUrl);
        this.hasJacuzzi = true;
        this.hasMinibar = true;
        this.HasBalcony = true;
        this.View = "Sea View";
    }

    public boolean hasJacuzzi() {
        return hasJacuzzi;
    }
    
    public boolean hasMinibar() {
        return hasMinibar;
    }

    @Override
    public void displayInfo() { 
        System.out.println("Type: Deluxe Room");
        super.displayInfo();
        System.out.println("          Jacuzzi -->" + hasJacuzzi);
        System.out.println("          Minibar -->" + hasMinibar);
    }
}