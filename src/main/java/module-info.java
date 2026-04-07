module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    exports bankingapp;
    exports bankingapp.model;
    exports bankingapp.service;
    exports com.example.demo1;
}
