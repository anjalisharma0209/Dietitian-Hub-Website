package com.servlet.register;

// AdminDashboardServlet.java

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("adminAuthenticated") != null) {
            // Admin is authenticated; proceed to the dashboard
            request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
        } else {
            // Admin is not authenticated; redirect to the login page
            response.sendRedirect("admin_login.jsp");
        }
    }
}
