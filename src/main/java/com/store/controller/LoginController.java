package com.store.controller;

import com.store.controller.DashboardController;
import com.store.dao.UserDAO;
import com.store.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

    private final UserDAO userDAO = new UserDAO();

    public void showLogin(Stage stage) {
        Label title = new Label("HITECH STORE MANAGEMENT SYSTEM");

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Button loginButton = new Button("LOGIN");

        Label messageLabel = new Label();

        loginButton.setOnAction(event -> {

            String username = usernameField.getText().trim();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter usrname and password.");
                return;
            }

            User user = userDAO.authenticate(username, password);

            if (user != null) {
                DashboardController dashboardController = new DashboardController();
                dashboardController.showDashboard(stage, user);

            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        VBox layout = new VBox(
                10,
                title,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton,
                messageLabel

        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 500, 450);

        stage.setTitle("Login - HITECH Store Management Ssytem");
        stage.setScene(scene);
        stage.show();

    }

}
