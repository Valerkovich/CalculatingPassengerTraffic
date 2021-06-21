package ua.cn.stu.diploma.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection getConnect() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Diploma";
            String login = "postgres";
            String password = "12061996";
            return DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
