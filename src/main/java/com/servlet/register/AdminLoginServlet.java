package com.servlet.register;

// AdminLoginServlet.java

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isValidAdmin(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("adminAuthenticated", true);
            response.sendRedirect("admin_dashboard.jsp");
        } else {
            response.sendRedirect("admin_login.jsp?error=1");
        }
    }

    private boolean isValidAdmin(String username, String password) {
        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/phplogin";
        String dbUser = "root";
        String dbPassword = "Google@17";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If a row is returned, the admin is valid
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
