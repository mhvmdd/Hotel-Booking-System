/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.advcomprog.hotelbooking.hotelbookingsystem;

public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    
    protected User(String userId, String name, String email, String password) {
        this.userId = userId;
        setName(name);
        setEmail(email);
        setPassword(password);
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException(
                "Password can only contain letters, numbers, and underscores (_)");
        }
        this.password = password;
    }
    
    public boolean verifyPassword(String entered) {
        return this.password.equals(entered);
    }
    
    public abstract void DisplayProfile();
    
    public abstract String getRole();
}
