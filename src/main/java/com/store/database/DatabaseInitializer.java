package com.store.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {

        String usersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL DEFAULT 'USER'
                );
                """;

        String productsTable = """
                CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_name TEXT NOT NULL UNIQUE,
                    unit_type TEXT NOT NULL,
                    minimum_stock REAL NOT NULL DEFAULT 0,
                    description TEXT
                );
                """;

        String suppliersTable = """
                CREATE TABLE IF NOT EXISTS suppliers (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    supplier_name TEXT NOT NULL,
                    phone TEXT,
                    email TEXT
                );
                """;

        String departmentsTable = """
                CREATE TABLE IF NOT EXISTS departments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    department_name TEXT NOT NULL UNIQUE
                );
                """;

        String stockReceiptsTable = """
                CREATE TABLE IF NOT EXISTS stock_receipts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL,
                    supplier_id INTEGER,
                    date_received TEXT NOT NULL,
                    quantity REAL NOT NULL,
                    weight REAL,
                    reference TEXT,
                    
                    FOREIGN KEY (product_id)
                        REFERENCES products(id),
                    
                    FOREIGN KEY (supplier_id)
                        REFERENCES suppliers(id)
                );
                """;

        String stockIssuesTable = """
                CREATE TABLE IF NOT EXISTS stock_issues (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL,
                    department_id INTEGER,
                    date_issued TEXT NOT NULL,
                    quantity REAL NOT NULL,
                    weight REAL,
                    issued_to TEXT NOT NULL,
                    reason TEXT,
                    
                    FOREIGN KEY (product_id)
                        REFERENCES products(id),
                    
                    FOREIGN KEY (department_id)
                        REFERENCES departments(id)
                );
                """;

        try (Connection connection = Database.connect();
             Statement statement = connection.createStatement()) {

            //Enable foreign keys
            statement.execute("PRAGMA foreign_keys = ON");

            statement.execute(usersTable);
            statement.execute(productsTable);
            statement.execute(suppliersTable);
            statement.execute(departmentsTable);
            statement.execute(stockReceiptsTable);
            statement.execute(stockIssuesTable);

            String insertAdmin = """
                    INSERT OR IGNORE INTO users (username, password, role)
                    VALUES ('admin', 'admin' 'ADMIN')
                    """;

            statement.execute(insertAdmin);

            System.out.println("Database tables created successfully.");
        } catch (SQLException e) {

            System.out.println("Database initialization failed.");
            e.printStackTrace();
        }

    }
}
