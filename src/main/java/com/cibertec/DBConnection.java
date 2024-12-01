package com.cibertec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Establecemos la conexión principal con SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=LPBD02;encrypt=false";
    private static final String USER = "sa"; // Cambié de USE a USER para claridad
    private static final String PASSWORD = "sql";

    public static Connection getConnector() throws ClassNotFoundException, SQLException {
        // Cargar el controlador JDBC de SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Retornar la conexión con los parámetros adecuados
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

