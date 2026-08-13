package com.store.controller;

import com.store.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {

    public void showDashboard(Stage stage, User user) {

        Label title = new Label("HITECH STORE MANAGEMENT SYSTEM");

        Label welcomeLabel = new Label(
                "Welcome, " + user.getUsername()
                    + " (" + user.getRole() + ")"
        );

        Button productsButton = new Button("Products");
        Button receiveButton = new Button("Receive Stock");
        Button issueButton = new Button("Issue Stock");
        Button suppliersButton = new Button("Suppliers");
        Button departmentsButton = new Button("Departments");
        Button stockButton = new Button("Stock Balance");
        Button reportsButton = new Button("Reports");
        Button logoutButton = new Button("Logout");

        VBox layout = new VBox(
                15,
                title,
                welcomeLabel,
                productsButton,
                receiveButton,
                issueButton,
                suppliersButton,
                departmentsButton,
                stockButton,
                reportsButton,
                logoutButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 700, 600);

        stage.setTitle("Dashboard - HITECH Store Management System");
        stage.setScene(scene);
        stage.show();


    }
}
