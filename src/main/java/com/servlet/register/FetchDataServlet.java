package com.servlet.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetchData")
public class FetchDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        fetchDataById(id, request, response);
    }

    private void fetchDataById(int id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT id, username, mobilenumber, email, height, weight, gender, message FROM accounts WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("username");
                        String MobileNo = resultSet.getString("mobilenumber");
                        String Email = resultSet.getString("email");
                        String Height = resultSet.getString("height");
                        String Weight = resultSet.getString("weight");
                        String Gender = resultSet.getString("gender");
                        String Message = resultSet.getString("message");

                        request.setAttribute("id", id);
                        request.setAttribute("username", name);
                        request.setAttribute("mobilenumber", MobileNo);
                        request.setAttribute("email", Email);
                        request.setAttribute("height", Height);
                        request.setAttribute("weight", Weight);
                        request.setAttribute("gender", Gender);
                        request.setAttribute("message", Message);

                        request.getRequestDispatcher("displayData.jsp").forward(request, response);
                    } else {
                        response.getWriter().println("No data found for the given ID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
