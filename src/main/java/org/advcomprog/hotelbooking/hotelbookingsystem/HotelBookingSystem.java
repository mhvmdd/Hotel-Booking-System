/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.advcomprog.hotelbooking.hotelbookingsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import org.advcomprog.hotelbooking.hotelbookingsystem.Payment.PaymentMethod;
import javafx.scene.control.DatePicker;
import java.util.UUID;

/**
 *
 * @author Lenovo
 */
public class HotelBookingSystem extends Application {

  private BorderPane mainLayout;
  private Label titleLabel; // Store reference to title
  private Guest currentGuest; // Add current guest field
  private Hotel hotel; // Add Hotel instance

  // Credentials storage
  private String adminEmail;
  private String adminPassword;
  private String guestEmail;
  private String guestPassword;
  // Registration storage
  private String registerName;
  private String registerEmail;
  private String registerPassword;
  
  // Store all rooms
  private ArrayList<Room> allRooms = new ArrayList<>();

  private int currentRoomIndex = 0;

  private int currentAdminIndex = 0;
  public static void main(String[] args) {
      launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("Hotel Booking System");
      
      // Initialize hotel
      hotel = new Hotel();
      
      // Initialize rooms
      initializeRooms();
      
      // Get the sorted rooms from hotel
      allRooms = hotel.getRooms();
      
      // Initialize admin
      initializeAdmin();
      
      mainLayout = new BorderPane();

      // Title at top
      titleLabel = new Label("Hotel Booking System");
      titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
      BorderPane.setAlignment(titleLabel, Pos.CENTER);
      mainLayout.setTop(titleLabel);

      // Left buttons in GridPane (Login above Register)
      GridPane buttonGrid = new GridPane();
      buttonGrid.setVgap(20);
      buttonGrid.setHgap(20);
      buttonGrid.setAlignment(Pos.CENTER);
      buttonGrid.setPadding(new Insets(0, 0, 0, 50));

      Button loginBtn = new Button("Login");
      loginBtn.setPrefSize(120, 40);
      loginBtn.setOnAction(e -> handleLogin());
      GridPane.setRowIndex(loginBtn, 0);
      GridPane.setColumnIndex(loginBtn, 0);

      Button registerBtn = new Button("Register");
      registerBtn.setPrefSize(120, 40);
      registerBtn.setOnAction(e -> handleRegister());
      GridPane.setRowIndex(registerBtn, 1);
      GridPane.setColumnIndex(registerBtn, 0);

      buttonGrid.getChildren().addAll(loginBtn, registerBtn);
      mainLayout.setLeft(buttonGrid);

      // Right image
      GridPane imageGrid = new GridPane();
      imageGrid.setAlignment(Pos.CENTER);
      ImageView hotelImage = new ImageView(new Image("https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800&auto=format&fit=crop"));
      hotelImage.setFitWidth(400);
      hotelImage.setFitHeight(300);
      imageGrid.setPadding(new Insets(0, 50, 0, 50));
      imageGrid.getChildren().addAll(hotelImage);
      
      mainLayout.setRight(imageGrid);

      primaryStage.setScene(new Scene(mainLayout, 700, 800));
      primaryStage.show();
  }

  private void clearCenter() {
      mainLayout.setLeft(null);
      mainLayout.setCenter(null);
      mainLayout.setRight(null);
  }

  private void restoreMainMenu() {
      clearCenter();
      
      // Left buttons in GridPane (Login above Register)
      GridPane buttonGrid = new GridPane();
      buttonGrid.setVgap(20);
      buttonGrid.setHgap(20);
      buttonGrid.setAlignment(Pos.CENTER);
      buttonGrid.setPadding(new Insets(0, 0, 0, 50));

      Button loginBtn = new Button("Login");
      loginBtn.setPrefSize(120, 40);
      loginBtn.setOnAction(e -> handleLogin());
      GridPane.setRowIndex(loginBtn, 0);
      GridPane.setColumnIndex(loginBtn, 0);

      Button registerBtn = new Button("Register");
      registerBtn.setPrefSize(120, 40);
      registerBtn.setOnAction(e -> handleRegister());
      GridPane.setRowIndex(registerBtn, 1);
      GridPane.setColumnIndex(registerBtn, 0);

      buttonGrid.getChildren().addAll(loginBtn, registerBtn);
      mainLayout.setLeft(buttonGrid);

      // Right image
      GridPane imageGrid = new GridPane();
      imageGrid.setAlignment(Pos.CENTER);
      ImageView hotelImage = new ImageView(new Image("https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800&auto=format&fit=crop"));
      hotelImage.setFitWidth(400);
      hotelImage.setFitHeight(300);
      imageGrid.setPadding(new Insets(0, 50, 0, 50));
      imageGrid.getChildren().addAll(hotelImage);
      mainLayout.setRight(imageGrid);
  }

  private void initializeRooms() {
      // Standard Rooms
      StandardRoom std1 = hotel.createStandardRoom("101", 2, 
          "https://images.unsplash.com/photo-1618773928121-c32242e63f39?w=800&auto=format&fit=crop");
      StandardRoom std2 = hotel.createStandardRoom("102", 3, 
          "https://images.unsplash.com/photo-1595576508898-0ad5c879a061?w=800&auto=format&fit=crop");
          
      // Deluxe Rooms
      DeluxeRoom dlx1 = hotel.createDeluxeRoom("201", 2, 
          "https://images.unsplash.com/photo-1590490360182-c33d57733427?w=800&auto=format&fit=crop");
      DeluxeRoom dlx2 = hotel.createDeluxeRoom("202", 4, 
          "https://images.unsplash.com/photo-1591088398332-8a7791972843?w=800&auto=format&fit=crop");
          
      // Junior Suites
      JuniorSuite js1 = hotel.createJuniorSuite("301", 2, 2, 
          "https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?w=800&auto=format&fit=crop");
      JuniorSuite js2 = hotel.createJuniorSuite("302", 3, 2, 
          "https://images.unsplash.com/photo-1584132967334-10e028bd69f7?w=800&auto=format&fit=crop");
          
      // Luxurious Suites
      LuxuriousSuite ls1 = hotel.createLuxuriousSuite("401", 4, 3, 
          "https://images.unsplash.com/photo-1631049307264-da0ec9d70304?w=800&auto=format&fit=crop");
      LuxuriousSuite ls2 = hotel.createLuxuriousSuite("402", 6, 3, 
          "https://images.unsplash.com/photo-1631049552057-403cdb8f0658?w=800&auto=format&fit=crop");

      // Sort rooms by price using compareTo
      allRooms = hotel.getRooms();
      for (int i = 0; i < allRooms.size() - 1; i++) {
          for (int j = 0; j < allRooms.size() - i - 1; j++) {
              if (allRooms.get(j).compareTo(allRooms.get(j + 1)) > 0) {
                  // Swap rooms
                  Room temp = allRooms.get(j);
                  allRooms.set(j, allRooms.get(j + 1));
                  allRooms.set(j + 1, temp);
              }
          }
      }
  }

  private void initializeAdmin() {
      Admin defaultAdmin = new Admin("ADM-001", "Mohammed", "m@m.com", "1111111111");
      hotel.addUser(defaultAdmin);
  }

  private void showBrowseRooms() {
      clearCenter();
      
      // Get current room
      Room currentRoom = allRooms.get(currentRoomIndex);
      
      // Right side - Room image and details
      VBox rightBox = new VBox(10);
      rightBox.setAlignment(Pos.CENTER);
      rightBox.setPadding(new Insets(0, 50, 0, 0));
      
      VBox leftBox = new VBox(10);
      leftBox.setAlignment(Pos.CENTER);
      leftBox.setPadding(new Insets(0, 0, 0, 50));

      // Room image - using room's specific image
      ImageView roomImage = new ImageView(new Image(currentRoom.getImageUrl()));
      roomImage.setFitWidth(400);
      roomImage.setFitHeight(300);
      
      // Navigation buttons
      GridPane navigationButtons = new GridPane();
      navigationButtons.setAlignment(Pos.CENTER);
      
      Button previousBtn = new Button("Previous");
      previousBtn.setPrefSize(100, 30);
      previousBtn.setOnAction(e -> {
          if (currentRoomIndex > 0) {
              currentRoomIndex--;
              showBrowseRooms();
          }
      });
      previousBtn.setDisable(currentRoomIndex == 0);
      
      Button nextBtn = new Button("Next");
      nextBtn.setPrefSize(100, 30);
      nextBtn.setOnAction(e -> {
          if (currentRoomIndex < allRooms.size() - 1) {
              currentRoomIndex++;
              showBrowseRooms();
          }
      });
      nextBtn.setDisable(currentRoomIndex == allRooms.size() - 1);
      
      Button bookBtn = new Button("Book");
      bookBtn.setPrefSize(100, 30);
      bookBtn.setDisable(!currentRoom.isAvailable());
      bookBtn.setOnAction(e -> showPaymentDialog(currentRoom));
      
      Button mainMenuBtn = new Button("Main Menu");
      mainMenuBtn.setPrefSize(100, 30);
      mainMenuBtn.setOnAction(e -> showGuestMenu(currentGuest));

      navigationButtons.add(previousBtn, 0, 0);
      navigationButtons.add(nextBtn, 1, 0);
      navigationButtons.add(bookBtn, 0, 1);
      navigationButtons.add(mainMenuBtn, 1, 1);

      navigationButtons.setHgap(10);
      navigationButtons.setVgap(10);

      // Room details
      VBox detailsBox = new VBox(5);
      detailsBox.setAlignment(Pos.CENTER);
      detailsBox.getChildren().addAll(
          new Label("Room Number: " + currentRoom.getRoomNumber()),
          new Label("Type: " + currentRoom.getClass().getSimpleName()),
          new Label("Capacity: " + currentRoom.GetCapacity() + " persons"),
          new Label(String.format("Price per night: $%.2f", currentRoom.getPrice())),
          new Label("View: " + currentRoom.View()),
          new Label("Balcony: " + (currentRoom.HasBalcony() ? "Yes" : "No")),
          new Label("Availability: " + (currentRoom.isAvailable() ? "Available" : "Not Available"))
      );
      
      rightBox.getChildren().addAll(roomImage, navigationButtons);
      mainLayout.setRight(rightBox);

      leftBox.getChildren().addAll(detailsBox);
      mainLayout.setLeft(leftBox);
  }

  private void showGuestMenu(Guest guest) {
      this.currentGuest = guest; // Store the current guest
      clearCenter();
      
      // Update title to include guest name
      titleLabel.setText("Hotel Booking System");
      titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");
      
      // Left buttons in GridPane
      GridPane buttonGrid = new GridPane();
      buttonGrid.setVgap(20);
      buttonGrid.setHgap(20);
      buttonGrid.setAlignment(Pos.CENTER);
      buttonGrid.setPadding(new Insets(0, 0, 0, 50));

      Button browseBtn = new Button("Browse");
      browseBtn.setPrefSize(120, 40);
      browseBtn.setOnAction(e -> {
          currentRoomIndex = 0; // Reset to first room
          showBrowseRooms();
      });
      GridPane.setRowIndex(browseBtn, 1);
      GridPane.setColumnIndex(browseBtn, 0);

      Button logoutBtn = new Button("Logout");
      logoutBtn.setPrefSize(120, 40);
      logoutBtn.setOnAction(e -> {
          titleLabel.setText("Hotel Booking System");
          currentGuest = null; // Clear current guest on logout
          restoreMainMenu();
      });
      GridPane.setRowIndex(logoutBtn, 2);
      GridPane.setColumnIndex(logoutBtn, 0);
      
      Label welcomeLabel = new Label("Hello, " + guest.getName());
      welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");

      GridPane.setRowIndex(welcomeLabel, 0);
      GridPane.setColumnIndex(welcomeLabel, 0);

      

      buttonGrid.getChildren().addAll(welcomeLabel,browseBtn, logoutBtn);
      mainLayout.setLeft(buttonGrid);

      // Right image
      GridPane imageGrid = new GridPane();
      imageGrid.setAlignment(Pos.CENTER);
      ImageView hotelImage = new ImageView(new Image("https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800&auto=format&fit=crop"));
      hotelImage.setFitWidth(400);
      hotelImage.setFitHeight(300);
      imageGrid.setPadding(new Insets(0, 50, 0, 50));
      imageGrid.getChildren().addAll(hotelImage);
      mainLayout.setRight(imageGrid);
  }

  private void handleLogin() {
      clearCenter();

      VBox optionsBox = new VBox(20);
      optionsBox.setAlignment(Pos.CENTER);

      Label chooseLabel = new Label("Select Login Type:");
      chooseLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

      Button adminBtn = new Button("Admin");
      adminBtn.setPrefSize(120, 40);
      adminBtn.setOnAction(e -> handleAdminLogin());

      Button guestBtn = new Button("Guest");
      guestBtn.setPrefSize(120, 40);
      guestBtn.setOnAction(e -> handleGuestLogin());

      Button backBtn = new Button("Back");
      backBtn.setPrefSize(120, 40);
      backBtn.setOnAction(e -> restoreMainMenu());

      optionsBox.getChildren().addAll(chooseLabel, adminBtn, guestBtn, backBtn);
      mainLayout.setCenter(optionsBox);
  }

  private void handleAdminLogin() {
      clearCenter();
      VBox form = new VBox(10);
      form.setAlignment(Pos.CENTER);

      Label title = new Label("Admin Login");
      title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

      Label emailLabel = new Label("Email:");
      TextField emailField = new TextField();
      emailField.setPrefWidth(100);

      Label passwordLabel = new Label("Password:");
      PasswordField pwField = new PasswordField();
      pwField.setPrefWidth(100);

      Label errorLabel = new Label("");
      errorLabel.setStyle("-fx-text-fill: red;");

      Button submit = new Button("Submit");
      submit.setPrefSize(120, 40);
      submit.setOnAction(e -> {
          try {
              // Check admin credentials
              for (User admin : hotel.getAdmins()) {
                  if (admin.getEmail().equals(emailField.getText()) && 
                      admin.verifyPassword(pwField.getText())) {
                      currentAdminIndex = hotel.getAdmins().indexOf(admin);
                      showAdminWindow((Admin) admin);
                      return;
                  }
              }
              errorLabel.setText("Invalid email or password!");
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });

      Button backBtn = new Button("Back");
      backBtn.setPrefSize(120, 40);
      backBtn.setOnAction(e -> handleLogin());

      form.getChildren().addAll(title, emailLabel, emailField, passwordLabel, pwField, 
                              errorLabel, submit, backBtn);
      mainLayout.setCenter(form);
  }

  private void handleGuestLogin() {
      clearCenter();
      VBox form = new VBox(10);
      form.setAlignment(Pos.CENTER);

      Label title = new Label("Guest Login");
      title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
      
      Label emailLabel = new Label("Email:");
      TextField emailField = new TextField();
      emailField.setPrefWidth(100);
      
      Label passwordLabel = new Label("Password:");
      PasswordField pwField = new PasswordField();
      pwField.setPrefWidth(100);

      Label errorLabel = new Label("");
      errorLabel.setStyle("-fx-text-fill: red;");

      Button submit = new Button("Submit");
      submit.setPrefSize(120, 40);
      submit.setOnAction(e -> {
          try {
              // Check guest credentials
              for (User guest : hotel.getGuests()) {
                  if (guest.getEmail().equals(emailField.getText()) && 
                      guest.verifyPassword(pwField.getText())) {
                      currentGuest = (Guest) guest;
                      showGuestMenu(currentGuest);
                      return;
                  }
              }
              errorLabel.setText("Invalid email or password!");
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });

      Button backBtn = new Button("Back");
      backBtn.setPrefSize(120, 40);
      backBtn.setOnAction(e -> handleLogin());

      form.getChildren().addAll(title, emailLabel, emailField, passwordLabel, pwField, 
                              errorLabel, submit, backBtn);
      mainLayout.setCenter(form);
  }

  private void handleRegister() {
      clearCenter();
      VBox form = new VBox(10);
      form.setAlignment(Pos.CENTER);

      Label title = new Label("Register");
      title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
      
      Label nameLabel = new Label("Name:");
      TextField nameField = new TextField();
      nameField.setPrefWidth(100);
      
      Label emailLabel = new Label("Email:");
      TextField emailField = new TextField();
      emailField.setPrefWidth(100);
      
      Label passwordLabel = new Label("Password:");
      PasswordField pwField = new PasswordField();
      pwField.setPrefWidth(100);

      // Add error label
      Label errorLabel = new Label("");
      errorLabel.setStyle("-fx-text-fill: red;");

      Button submit = new Button("Submit");
      submit.setPrefSize(120, 40);
      submit.setOnAction(e -> {
          try {
              // Get values from fields
              String name = nameField.getText();
              String email = emailField.getText();
              String password = pwField.getText();
              
              // Validate fields are not empty
              if (name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
                  errorLabel.setText("All fields are required!");
                  return;
              }
              
              // Check if email already exists
              for (User guestUser : hotel.getGuests()) {
                  if (guestUser instanceof Guest && guestUser.getEmail().equals(email)) {
                      errorLabel.setText("Email already registered!");
                      return;
                  }
              }
              
              // Create new guest with generated ID
              String guestId = "GST-" + UUID.randomUUID().toString().substring(0, 5);
              Guest newGuest = new Guest(guestId, name, email, password);
              
              // Add to hotel's guest list
              hotel.addUser(newGuest);
              
              // If successful, store the values and show success message
              registerName = name;
              registerEmail = email;
              registerPassword = password;
              
              errorLabel.setStyle("-fx-text-fill: green;");
              errorLabel.setText("Registration successful!");
              
              // Clear fields after successful registration
              nameField.clear();
              emailField.clear();
              pwField.clear();
              
          } catch (IllegalArgumentException ex) {
              errorLabel.setText(ex.getMessage());
          }
      });

      Button backBtn = new Button("Back");
      backBtn.setPrefSize(120, 40);
      backBtn.setOnAction(e -> restoreMainMenu());

      form.getChildren().addAll(title, nameLabel, nameField, emailLabel, emailField, 
                              passwordLabel, pwField, errorLabel, submit, backBtn);
      mainLayout.setCenter(form);
  }

  private void showPaymentDialog(Room room) {
      clearCenter();
      
      // Main container
      VBox mainContainer = new VBox(20);
      mainContainer.setAlignment(Pos.CENTER);
      mainContainer.setPadding(new Insets(20));
      mainContainer.setStyle("-fx-background-color: white; -fx-padding: 20px;");
      mainContainer.setMaxWidth(500);
      
      // Title
      Label titleLabel = new Label("Booking Details");
      titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
      
      // Error label
      Label errorLabel = new Label("");
      errorLabel.setStyle("-fx-text-fill: red;");
      
      // Form grid
      GridPane formGrid = new GridPane();
      formGrid.setHgap(10);
      formGrid.setVgap(10);
      formGrid.setAlignment(Pos.CENTER);
      
      // Check-in date
      Label checkInLabel = new Label("Check-in Date:");
      DatePicker checkInPicker = new DatePicker();
      checkInPicker.setValue(LocalDate.now());
      formGrid.add(checkInLabel, 0, 0);
      formGrid.add(checkInPicker, 1, 0);
      
      // Check-out date
      Label checkOutLabel = new Label("Check-out Date:");
      DatePicker checkOutPicker = new DatePicker();
      checkOutPicker.setValue(LocalDate.now().plusDays(1));
      formGrid.add(checkOutLabel, 0, 1);
      formGrid.add(checkOutPicker, 1, 1);
      
      // Payment method
      Label paymentLabel = new Label("Payment Method:");
      ComboBox<PaymentMethod> paymentMethodCombo = new ComboBox<>();
      paymentMethodCombo.getItems().addAll(PaymentMethod.values());
      paymentMethodCombo.setValue(PaymentMethod.CREDIT_CARD);
      formGrid.add(paymentLabel, 0, 2);
      formGrid.add(paymentMethodCombo, 1, 2);
      
      // Promo code
      Label promoLabel = new Label("Promo Code (Optional):");
      TextField promoField = new TextField();
      formGrid.add(promoLabel, 0, 3);
      formGrid.add(promoField, 1, 3);
      
      // Buttons
      HBox buttonBox = new HBox(10);
      buttonBox.setAlignment(Pos.CENTER);
      
      Button submitButton = new Button("Submit");
      submitButton.setPrefSize(100, 30);
      submitButton.setOnAction(e -> {
          try {
              // Validate dates
              LocalDate checkIn = checkInPicker.getValue();
              LocalDate checkOut = checkOutPicker.getValue();
              
              if (checkIn == null || checkOut == null) {
                  errorLabel.setText("Please select both check-in and check-out dates.");
                  return;
              }
              
              if (!checkOut.isAfter(checkIn)) {
                  errorLabel.setText("Check-out date must be after check-in date.");
                  return;
              }
              
              if (checkIn.isBefore(LocalDate.now())) {
                  errorLabel.setText("Check-in date cannot be in the past.");
                  return;
              }

              // Create booking to get payment details
              boolean success = hotel.createBooking(currentGuest, room.getRoomNumber(), 
                                                  checkIn, checkOut, paymentMethodCombo.getValue());
              
              // Find the booking we just created to get its ID
              String bookingId = null;
              for (int i = 0; i < hotel.getBookings().size(); i++) {
                  Booking b = hotel.getBookings().get(i);
                  if (b.getRoom().getRoomNumber().equals(room.getRoomNumber()) &&
                      b.getGuest().equals(currentGuest) &&
                      b.getCheckIn().equals(checkIn) &&
                      b.getCheckOut().equals(checkOut)) {
                      bookingId = b.getBookingId();
                      break;
                  }
              }
              
              if (bookingId == null) {
                  errorLabel.setText("Error finding booking details.");
                  return;
              }
              
              // Apply promo code if provided
              if (success && !promoField.getText().isEmpty()) {
                  try {
                      if (!hotel.addPromoCode(bookingId, promoField.getText())) {
                          errorLabel.setText("Invalid promo code. Proceeding without discount.");
                          return;
                      }
                  } catch (IllegalArgumentException ex) {
                      errorLabel.setText("Invalid promo code. Proceeding without discount.");
                      return;
                  }
              }
              
              // Get the payment for this booking
              Payment payment = hotel.getPaymentById("PAY" + bookingId);
              
              // Show booking confirmation
              showBookingConfirmation(room, checkIn, checkOut, 
                                    paymentMethodCombo.getValue(), 
                                    promoField.getText(),
                                    payment.getAmount(),
                                    bookingId);
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });
      
      Button backButton = new Button("Back");
      backButton.setPrefSize(100, 30);
      backButton.setOnAction(e -> showBrowseRooms());
      
      buttonBox.getChildren().addAll(submitButton, backButton);
      
      mainContainer.getChildren().addAll(titleLabel, errorLabel, formGrid, buttonBox);
      mainLayout.setCenter(mainContainer);
  }
  
  private void showBookingConfirmation(Room room, LocalDate checkIn, LocalDate checkOut, 
                                     PaymentMethod paymentMethod, String promoCode, double totalAmount,
                                     String bookingId) {
      clearCenter();
      VBox confirmationBox = new VBox(15);
      confirmationBox.setAlignment(Pos.CENTER);
      confirmationBox.setPadding(new Insets(20));
      confirmationBox.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-border-width: 1px;");
      confirmationBox.setMaxWidth(400);
      
      Label titleLabel = new Label("Booking Confirmation");
      titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
      
      // Error label
      Label errorLabel = new Label("");
      errorLabel.setStyle("-fx-text-fill: red;");
      
      // Receipt using GridPane for better alignment
      GridPane receiptGrid = new GridPane();
      receiptGrid.setHgap(10);
      receiptGrid.setVgap(5);
      receiptGrid.setAlignment(Pos.CENTER);
      
      receiptGrid.add(new Label("Booking ID:"), 0, 0);
      receiptGrid.add(new Label(bookingId), 1, 0);
      
      receiptGrid.add(new Label("Room Number:"), 0, 1);
      receiptGrid.add(new Label(room.getRoomNumber()), 1, 1);
      
      receiptGrid.add(new Label("Room Type:"), 0, 2);
      receiptGrid.add(new Label(room.getClass().getSimpleName()), 1, 2);
      
      receiptGrid.add(new Label("Check-in:"), 0, 3);
      receiptGrid.add(new Label(checkIn.toString()), 1, 3);
      
      receiptGrid.add(new Label("Check-out:"), 0, 4);
      receiptGrid.add(new Label(checkOut.toString()), 1, 4);
      
      receiptGrid.add(new Label("Number of Nights:"), 0, 5);
      receiptGrid.add(new Label(String.valueOf(java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut))), 1, 5);
      
      receiptGrid.add(new Label("Payment Method:"), 0, 6);
      receiptGrid.add(new Label(paymentMethod.toString()), 1, 6);
      
      receiptGrid.add(new Label("Promo Code:"), 0, 7);
      receiptGrid.add(new Label(promoCode.isEmpty() ? "None" : promoCode), 1, 7);
      
      // Add discount information if promo code is used
      if (promoCode.isEmpty() == false) {
          String discountText;
          if (promoCode.equals("CSE27")) {
              discountText = "20%";
          } else if (promoCode.equals("ASU30")) {
              discountText = "10%";
          } else {
              discountText = "0%";
          }
          receiptGrid.add(new Label("Discount Applied:"), 0, 8);
          receiptGrid.add(new Label(discountText), 1, 8);
          receiptGrid.add(new Label("Total Amount:"), 0, 9);
          receiptGrid.add(new Label(String.format("$%.2f", totalAmount)), 1, 9);
      } else {
          receiptGrid.add(new Label("Total Amount:"), 0, 8);
          receiptGrid.add(new Label(String.format("$%.2f", totalAmount)), 1, 8);
      }
      
      HBox buttonBox = new HBox(10);
      buttonBox.setAlignment(Pos.CENTER);
      
      Button confirmButton = new Button("Confirm");
      confirmButton.setOnAction(e -> {
          try {
              hotel.confirmation("ok", "PAY" + bookingId, bookingId);
              errorLabel.setStyle("-fx-text-fill: green;");
              errorLabel.setText("Booking confirmed successfully!");
              showPaymentConfirmation(room, bookingId);
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });
      
      Button cancelButton = new Button("Cancel");
      cancelButton.setOnAction(e -> {
          try {
              hotel.confirmation("cancel", "PAY" + bookingId, bookingId);
              showBrowseRooms();
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });
      
      buttonBox.getChildren().addAll(confirmButton, cancelButton);
      
      confirmationBox.getChildren().addAll(titleLabel, errorLabel, receiptGrid, buttonBox);
      mainLayout.setCenter(confirmationBox);
  }

  public void showPaymentConfirmation(Room room, String bookingId) {
    clearCenter();
    VBox paymentConfirmationBox = new VBox(15);
    paymentConfirmationBox.setAlignment(Pos.CENTER);
    paymentConfirmationBox.setPadding(new Insets(20));

    Label titleLabel = new Label("Payment Confirmation");
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");


    GridPane msgGrid = new GridPane();
    msgGrid.setHgap(10);
    msgGrid.setVgap(10);
    msgGrid.setAlignment(Pos.CENTER);

    msgGrid.add(new Label("Payment successful!"), 0, 0);
    msgGrid.add(new Label("Booking ID: " + bookingId), 0, 1);
    msgGrid.add(new Label("Room Number: " + room.getRoomNumber()), 0, 2);

    Button continueButton = new Button("Continue");
    continueButton.setOnAction(e -> showBrowseRooms());

    paymentConfirmationBox.getChildren().addAll(titleLabel, msgGrid, continueButton);
    mainLayout.setCenter(paymentConfirmationBox);
  }

  private void showAdminWindow(Admin admin) {
      clearCenter();
      
      VBox adminBox = new VBox(20);
      adminBox.setAlignment(Pos.CENTER);
      adminBox.setPadding(new Insets(20));
      
      Label welcomeLabel = new Label("Welcome, " + admin.getName());
      welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
      
      // Create admin action buttons
      Button removeGuestBtn = new Button("Remove Guest");
      removeGuestBtn.setPrefSize(200, 40);
      removeGuestBtn.setOnAction(e -> showRemoveGuest());
      
      Button addAdminBtn = new Button("Add Admin");
      addAdminBtn.setPrefSize(200, 40);
      addAdminBtn.setOnAction(e -> showAddAdmin());
      
      Button logoutBtn = new Button("Logout");
      logoutBtn.setPrefSize(200, 40);
      logoutBtn.setOnAction(e -> handleLogin());
      
      adminBox.getChildren().addAll(welcomeLabel, removeGuestBtn, addAdminBtn, logoutBtn);
      mainLayout.setCenter(adminBox);
  }

  private void showRemoveGuest() {
      clearCenter();
      
      VBox content = new VBox(20);
      content.setAlignment(Pos.CENTER);
      content.setPadding(new Insets(20));
      
      Label titleLabel = new Label("Remove Guest");
      titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
      
      Label errorLabel = new Label();
      errorLabel.setStyle("-fx-text-fill: red;");
      
      VBox guestList = new VBox(10);
      guestList.setStyle("-fx-background-color: white; -fx-padding: 20px;");
      guestList.setMaxWidth(500);
      
      // Get all guests and display their information
      ArrayList<User> guests = hotel.getGuests();
      if (guests.isEmpty()) {
          Label noGuestsLabel = new Label("No guests registered");
          noGuestsLabel.setStyle("-fx-font-size: 14px;");
          guestList.getChildren().add(noGuestsLabel);
      } else {
          for (User guest : guests) {
              HBox guestInfo = new HBox(20);
              guestInfo.setAlignment(Pos.CENTER_LEFT);
              guestInfo.setPadding(new Insets(10));
              
              VBox details = new VBox(5);
              details.getChildren().addAll(
                  new Label("ID: " + guest.getUserId()),
                  new Label("Name: " + guest.getName()),
                  new Label("Email: " + guest.getEmail()),
                  new Label("Password: " + guest.getPassword())
              );
              
              Button removeBtn = new Button("Remove");
              removeBtn.setPrefSize(100, 30);
              removeBtn.setOnAction(e -> {
                  try {

                      Guest guestTemp = (Guest) hotel.findUserById(guest.getUserId(), guest.getPassword());
                      Booking bookingTemp = hotel.findBookingByGuestId(guestTemp.getUserId());
                      if (bookingTemp != null) {
                        hotel.cancelBooking(bookingTemp.getBookingId());
                      }
                      hotel.removeUser(guest.getUserId(), guest.getPassword());
                      errorLabel.setStyle("-fx-text-fill: green;");
                      errorLabel.setText("Guest removed successfully!");
                      showRemoveGuest(); // Refresh the list
                  } catch (Exception ex) {
                      errorLabel.setText(ex.getMessage());
                  }
              });
              
              guestInfo.getChildren().addAll(details, removeBtn);
              guestList.getChildren().add(guestInfo);
          }
      }
      
      Button backButton = new Button("Back to Admin Menu");
      backButton.setPrefSize(200, 40);
      backButton.setOnAction(e -> showAdminWindow((Admin) hotel.getAdmins().get(0)));
      
      content.getChildren().addAll(titleLabel, guestList, backButton);
      mainLayout.setCenter(content);
  }

  private void showAddAdmin() {
      clearCenter();
      
      VBox content = new VBox(20);
      content.setAlignment(Pos.CENTER);
      content.setPadding(new Insets(20));
      
      Label titleLabel = new Label("Add New Admin");
      titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
      
      VBox form = new VBox(15);
      form.setStyle("-fx-background-color: white; -fx-padding: 20px;");
      form.setMaxWidth(400);
      form.setAlignment(Pos.CENTER);
      
      TextField nameField = new TextField();
      nameField.setPrefWidth(300);
      
      TextField emailField = new TextField();
      emailField.setPrefWidth(300);
      
      PasswordField passwordField = new PasswordField();
      passwordField.setPrefWidth(300);
      
      Label errorLabel = new Label();
      errorLabel.setStyle("-fx-text-fill: red;");
      
      Button addButton = new Button("Add Admin");
      addButton.setPrefSize(200, 40);
      addButton.setOnAction(e -> {
          try {
              String adminId = "ADM-" + UUID.randomUUID().toString().substring(0, 5);
              Admin newAdmin = new Admin(adminId, nameField.getText(), emailField.getText(), passwordField.getText());
              hotel.addUser(newAdmin);
              errorLabel.setStyle("-fx-text-fill: green;");
              errorLabel.setText("Admin added successfully!");
          } catch (Exception ex) {
              errorLabel.setText(ex.getMessage());
          }
      });
      
      Button backButton = new Button("Back to Admin Menu");
      backButton.setPrefSize(200, 40);
      backButton.setOnAction(e -> showAdminWindow((Admin) hotel.getAdmins().get(currentAdminIndex)));
      
      form.getChildren().addAll(
          new Label("Name:"), nameField,
          new Label("Email:"), emailField,
          new Label("Password:"), passwordField,
          errorLabel,
          addButton
      );
      
      content.getChildren().addAll(titleLabel, form, backButton);
      mainLayout.setCenter(content);
  }

} 