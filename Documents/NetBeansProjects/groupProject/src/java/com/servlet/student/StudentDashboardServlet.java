/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;

import com.bean.student;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author haikalriez
 */
@WebServlet("/studentDashboard")
public class StudentDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String studentID = (String) session.getAttribute("userID");

        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps;
            ResultSet rs;

            ps = conn.prepareStatement(
                    "SELECT * FROM Student WHERE studentID = ?");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {

                request.setAttribute(
                        "studentID",
                        rs.getString("studentID"));

                request.setAttribute(
                        "programme",
                        rs.getString("programme"));

                request.setAttribute(
                        "phoneNumber",
                        rs.getString("phoneNumber"));
            }

            int totalBookings = 0;
            int activeLoans = 0;
            int pendingRequests = 0;
            int returnedItems = 0;

            ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM FacilityBooking WHERE studentID = ?");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {
                totalBookings = rs.getInt(1);
            }

            ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM LoanEquipment WHERE studentID = ? AND loanStatus = 'Approved'");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {
                activeLoans = rs.getInt(1);
            }

            int pendingBookings = 0;
            int pendingLoans = 0;

            ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM FacilityBooking WHERE studentID = ? AND bookingStatus = 'Pending'");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {
                pendingBookings = rs.getInt(1);
            }

            ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM LoanEquipment WHERE studentID = ? AND loanStatus = 'Pending'");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {
                pendingLoans = rs.getInt(1);
            }

            pendingRequests = pendingBookings + pendingLoans;

            ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM ReturnRecord rr "
                    + "JOIN LoanEquipment le ON rr.loanID = le.loanID "
                    + "WHERE le.studentID = ?");

            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {
                returnedItems = rs.getInt(1);
            }

            request.setAttribute("totalBookings", totalBookings);
            request.setAttribute("activeLoans", activeLoans);
            request.setAttribute("pendingRequests", pendingRequests);
            request.setAttribute("returnedItems", returnedItems);

            conn.close();

            request.getRequestDispatcher(
                    "/student/studentDashboard.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : " + e.getMessage());

        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
