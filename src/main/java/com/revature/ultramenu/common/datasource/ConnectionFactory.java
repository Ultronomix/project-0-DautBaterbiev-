package com.revature.ultramenu.common.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connFactory;
    private Properties dbProps = new Properties();

    private ConnectionFactory() {
        try {
            Class.forName("org.postgresql.Driver");
            dbProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {

            throw new RuntimeException("Could not read from properties file.", e);
        } catch (ClassNotFoundException e) {

            throw new RuntimeException("Failed to load PostgreSQL JDBC driver.", e);
        }
    }

    public static ConnectionFactory getInstance() {
        if (connFactory == null) {
            connFactory = new ConnectionFactory();
        }
        return connFactory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbProps.getProperty("db-url"), dbProps.getProperty("db-username"), dbProps.getProperty("db-password"));
    }

}
