module com.autoclicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires jnativehook;
    requires javafx.media;

    opens com.autoclicker to javafx.fxml, org.jnativehook;


    exports com.autoclicker;
    exports com.autoclicker.view;
}
