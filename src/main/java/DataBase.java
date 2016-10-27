
import java.sql.*;
import java.util.Scanner;

public class DataBase {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/Orders";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "pass";

    public static Connection initDB() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Goods");
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("DROP TABLE IF EXISTS Orders");
            st.execute("CREATE TABLE Goods (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), price DOUBLE)");
            st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), surname VARCHAR(20))");
            st.execute("CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, client_name VARCHAR(20), good_name VARCHAR(20))");
        } finally {
            st.close();
        }
        return conn;
    }

}
/*
Создать проект «База данных заказов». Создать таблицы «Товары» , «Клиенты» и «Заказы».
        Написать код для добавления новых клиентов, товаров и оформления заказов.*/
