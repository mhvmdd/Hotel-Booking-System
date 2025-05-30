/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

/**
 *
 * @author omara
 */
public enum AccessLevel {
    FULL("Full Access", true),
    MODERATE("Moderate Access", false),
    LIMITED("Limited Access", false);
    
    private final String levelName;
    private final boolean canResetPassword;
    
    AccessLevel(String levelName, boolean canResetPassword) {
        this.levelName = levelName;
        this.canResetPassword = canResetPassword;
    }
    
    public String getLevelName() {
        return levelName;
    }
    
    public boolean hasPermission(AccessLevel requiredLevel) {
        return this.ordinal() <= requiredLevel.ordinal();
    }
    
    public boolean canResetPassword() {
        return canResetPassword;
    }
}
