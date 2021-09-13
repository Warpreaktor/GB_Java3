package ru.java3.HomeWork_02;

import java.sql.*;

public class HW_02 {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void create() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Даниил', 75);");
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Михаил', 65);");
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Самуил', 50);");
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Гавриил', 100);");
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Мануил', 95);");
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Измаил', 100);");
    }

    private static void read() throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM students;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt(3));
            }
        }
    }

    private static void update() throws SQLException {
        stmt.executeUpdate("UPDATE students SET score = 60 WHERE name = 'Самуил';");
    }

    private static void delete() throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE score < 70 ;");
    }


    public static void main(String[] args) throws SQLException {
        connect();

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                "        id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "        name  TEXT,\n" +
                "        score INTEGER\n" +
                "    );");
        create();
        read();
        disconnect();
        update();
        delete();
    }

}
