package com.store;

import com.store.database.DatabaseInitializer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) {

        DatabaseInitializer.initialize();

        Label label = new Label("HITECH STORE INVENTORY SYSTEM");

        StackPane root = new StackPane(label);

        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("HITECH STORE INVENTORY SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/* package com.store;

import com.store.database.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        try (Connection connection = Database.connect()) {

            System.out.println("SQLite connection successful!");

        } catch (Exception e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        Label label = new Label("Store Inventory System");

        StackPane root = new StackPane(label);

        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Store Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}*/
