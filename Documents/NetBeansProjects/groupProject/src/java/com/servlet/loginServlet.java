package com.servlet;

import util.DBConnection;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String userID =
                request.getParameter("userID");

        String password =
                request.getParameter("password");

        String userType =
                request.getParameter("userType");

        // Input Validation
        if (userID == null || userID.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || userType == null || userType.trim().isEmpty()) {

            response.sendRedirect(
                    "login.jsp?error=empty");

            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Class.forName(
                    "org.apache.derby.jdbc.ClientDriver");

            conn =
                    DBConnection.getConnection();

            if ("student".equals(userType)) {

                String sql =
                        "SELECT * "
                        + "FROM Student "
                        + "WHERE studentID = ? "
                        + "AND password = ?";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(1, userID);
                ps.setString(2, password);

            } else if ("admin".equals(userType)) {

                String sql =
                        "SELECT * "
                        + "FROM Admin "
                        + "WHERE adminID = ? "
                        + "AND password = ?";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(1, userID);
                ps.setString(2, password);

            } else {

                response.sendRedirect(
                        "login.jsp?error=role");

                return;
            }

            rs =
                    ps.executeQuery();

            if (rs.next()) {

                HttpSession session =
                        request.getSession();

                session.setAttribute(
                        "userID",
                        userID);

                session.setAttribute(
                        "userType",
                        userType);

                if ("student".equals(userType)) {

                    session.setAttribute(
                            "studentID",
                            rs.getString(
                                    "studentID"));

                    session.setAttribute(
                            "studentName",
                            rs.getString(
                                    "studentName"));

                    System.out.println(
                            "Student Login : "
                            + rs.getString(
                                    "studentID"));

                    response.sendRedirect(
                            "studentDashboard");

                } else {

                    session.setAttribute(
                            "adminID",
                            rs.getString(
                                    "adminID"));

                    session.setAttribute(
                            "adminName",
                            rs.getString(
                                    "adminName"));

                    System.out.println(
                            "Admin Login : "
                            + rs.getString(
                                    "adminID"));

                    response.sendRedirect(
                            "dashboardAdmin");
                }

            } else {

                response.sendRedirect(
                        "login.jsp?error=invalid");
            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            response.sendRedirect(
                    "login.jsp?error=driver");

        } catch (SQLException e) {

            e.printStackTrace();

            response.sendRedirect(
                    "login.jsp?error=database");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    "login.jsp?error=system");

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}