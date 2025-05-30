/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public abstract class Suite extends Room{
    protected int NumberOfRooms;
    protected boolean haslivingArea;
    
     protected Suite(){}
    protected Suite (String roomNumber, int capacity, double priceForNight, int NumberOfRooms, String imageUrl){
     super(roomNumber, capacity, priceForNight, imageUrl);
     if (NumberOfRooms>0)
     { this.NumberOfRooms=NumberOfRooms;}
     else
     {throw new IllegalArgumentException("Number of rooms must be +ve integer");}
     
     this.HasBalcony=true;
     this.View="Sea View";
    
   }
   
     public boolean HasLivingArea(){
        return haslivingArea ;
    }
     public int SuiteRooms(){
         return NumberOfRooms; 
     }
    @Override
    public void displayInfo() {
        System.out.println("Suite Number: "+roomNumber);
        System.out.println("Capacity: "+capacity);
        System.out.println("Number Of Rooms :"+NumberOfRooms);
        System.out.println("Price For Night: "+priceForNight);
        System.out.println("Features: Balcony -->"+HasBalcony);
        System.out.println("          View -->"+View);
        System.out.println("          Has Living Area -->"+haslivingArea);
       
    }     
}