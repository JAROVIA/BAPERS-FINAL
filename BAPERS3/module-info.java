module BAPERS3 {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires layout;
    requires kernel;
    opens CONTROLLER;
    opens GUI;
}