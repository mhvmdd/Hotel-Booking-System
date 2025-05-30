package org.advcomprog.hotelbooking.hotelbookingsystem;

import java.time.LocalDate;

public abstract class Room implements Comparable<Room> {
    protected  static int rooms=0;
    protected String roomNumber;
    protected int capacity;
    protected double priceForNight;
    protected boolean isAvailable;
    protected boolean HasBalcony=false;
    protected String View="City View";
    protected String imageUrl;
    protected Room(){}
    protected Room (String roomNumber, int capacity, double priceForNight, String imageUrl){
     if (Integer.valueOf(roomNumber) > 0)
     { this.roomNumber = roomNumber;}
     else
     {throw new IllegalArgumentException("Room Number must be +ve integer");}
     if (priceForNight>0)
     { this.priceForNight=priceForNight;}
     else
     {throw new IllegalArgumentException("Price for Night must be +ve double");}
     if (capacity>0)
     { this.capacity=capacity;}
     else
     {throw new IllegalArgumentException("Capacity must be +ve integer");}
     this.isAvailable=true;
     this.imageUrl = imageUrl != null ? imageUrl : "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800&auto=format&fit=crop";
     rooms++;
   }
    //Getters for Room
   public static int getNumberOfRooms(){
        return rooms;
    }
    
    public boolean isAvailable(){
        return isAvailable;
    }
    
    public boolean HasBalcony(){
        return HasBalcony;
    }
    public String View(){
        return View ;
    }
    
    public String getRoomNumber(){
        return roomNumber;
    }
    
     public int GetCapacity(){
        return  capacity;
    }
     public double getPrice(){
        return  priceForNight;
    }
   //Setter for isAvailable
     public void setAvailable(boolean available){
      this.isAvailable=available;   
     }
     public void displayInfo(){
     System.out.println("Room Number: "+roomNumber);
     System.out.println("Capacity: "+capacity+" persons");
     System.out.println("Price For Night: "+priceForNight);
     System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
     System.out.println("Features: Balcony -->"+HasBalcony);
     System.out.println("          View -->"+View);
     }
    @Override
    public int compareTo(Room o) {
        if (this.priceForNight>o.priceForNight)
                { return 1;}
       else if (this.priceForNight<o.priceForNight)
               { return -1;}
       else 
           { return 0;}
      
        
    }

    public String getImageUrl() {
        return imageUrl;
    }
   
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
