module com.landry.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;
    requires itextpdf;

    opens com.landry.hotel to javafx.fxml;
    exports com.landry.hotel;
    exports com.landry.hotel.Controllers;
    exports com.landry.hotel.Models;
}