package com.servlet.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerOrUpdate(request, response, "INSERT INTO accounts (username, mobilenumber, email, height, weight, gender, message) VALUES (?, ?, ?, ?, ?, ?, ?)");
        } else if ("update".equals(action)) {
            registerOrUpdate(request, response, "UPDATE accounts SET username = ?, mobilenumber = ?, email = ?, height = ?, weight = ?, gender = ?, message = ? WHERE id = ?");
        } else if ("delete".equals(action)) {
            deleteUser(request, response);
        }
    }

    private void registerOrUpdate(HttpServletRequest request, HttpServletResponse response, String sql) throws ServletException, IOException {
        String username = request.getParameter("username");
        String mobilenumber = request.getParameter("mobilenumber");
        String email = request.getParameter("email");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String gender = request.getParameter("gender");
        String message = request.getParameter("message");

        int id = 0;
        if ("update".equals(request.getParameter("action"))) {
            id = Integer.parseInt(request.getParameter("updateId"));
        }

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/phplogin";
            String dbUsername = "root";
            String dbPassword = "Google@17"; // Updated password

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, mobilenumber);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, height);
                preparedStatement.setString(5, weight);
                preparedStatement.setString(6, gender);
                preparedStatement.setString(7, message);
                if ("update".equals(request.getParameter("action"))) {
                    preparedStatement.setInt(8, id);
                }

                preparedStatement.executeUpdate();
            }

            connection.close();

            if ("register".equals(request.getParameter("action"))) {
                response.getWriter().println("User registered successfully.");
                response.sendRedirect("Home page.html");
            } else {
                response.getWriter().println("Record updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if ("register".equals(request.getParameter("action"))) {
                response.getWriter().println("Error registering user.");
            } else {
                response.getWriter().println("Error updating record.");
            }
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idToDelete = Integer.parseInt(request.getParameter("deleteId"));

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/phplogin";
            String dbUsername = "root";
            String dbPassword = "Google@17"; // Updated password

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String deleteSql = "DELETE FROM accounts WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
                preparedStatement.setInt(1, idToDelete);
                preparedStatement.executeUpdate();
            }

            connection.close();

            response.getWriter().println("Record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting record.");
        }
    }
}
