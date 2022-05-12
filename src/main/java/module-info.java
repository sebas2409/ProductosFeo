module com.teoria.productosfeo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.teoria.productosfeo to javafx.fxml;
    exports com.teoria.productosfeo;
    exports com.teoria.productosfeo.controller;
    exports com.teoria.productosfeo.model;
    exports com.teoria.productosfeo.config;
    opens com.teoria.productosfeo.controller to javafx.fxml;
}