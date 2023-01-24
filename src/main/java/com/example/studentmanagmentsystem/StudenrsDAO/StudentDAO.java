package com.example.studentmanagmentsystem.StudenrsDAO;

import com.example.studentmanagmentsystem.entity.Student;

import java.sql.*;

public class StudentDAO{
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qwerty";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL , DB_USERNAME , DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public StudentDAO() throws SQLException {}

    public static Student findStudent(int id) throws SQLException {
        String findById = "select from students where id =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(findById);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.next());

        Student student = new Student();
          student.setEmail(resultSet.getString("email"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));

        return student;

    }
}
