module org.advcomprog.hotelbooking.hotelbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    
    exports org.advcomprog.hotelbooking.hotelbookingsystem;
    opens org.advcomprog.hotelbooking.hotelbookingsystem to javafx.graphics, javafx.fxml, javafx.base;
} 