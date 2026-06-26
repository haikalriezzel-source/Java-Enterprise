/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;

import util.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author haikalriez
 */
@WebServlet("/AdminFacilityListServlet")
public class AdminFacilityListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Object[]> pendingList =
                new ArrayList<>();

        ArrayList<Object[]> historyList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT "
                      + "FB.bookingID, "
                      + "FB.studentID, "
                      + "S.studentName, "
                      + "F.facilityName, "
                      + "FB.bookingDate, "
                      + "FB.startTime, "
                      + "FB.endTime, "
                      + "FB.purpose, "
                      + "FB.numberOfParticipants, "
                      + "FB.bookingStatus "
                      + "FROM FacilityBooking FB "
                      + "JOIN Facility F "
                      + "ON FB.facilityID = F.facilityID "
                      + "JOIN Student S "
                      + "ON FB.studentID = S.studentID "
                      + "WHERE LOWER(FB.studentID) LIKE ? "
                      + "OR LOWER(S.studentName) LIKE ? "
                      + "OR LOWER(F.facilityName) LIKE ? "
                      + "OR LOWER(FB.bookingStatus) LIKE ? "
                      + "OR LOWER(FB.purpose) LIKE ? "
                      + "ORDER BY FB.bookingID DESC";

                ps = conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);
                ps.setString(4, search);
                ps.setString(5, search);

            } else {

                sql =
                        "SELECT "
                      + "FB.bookingID, "
                      + "FB.studentID, "
                      + "S.studentName, "
                      + "F.facilityName, "
                      + "FB.bookingDate, "
                      + "FB.startTime, "
                      + "FB.endTime, "
                      + "FB.purpose, "
                      + "FB.numberOfParticipants, "
                      + "FB.bookingStatus "
                      + "FROM FacilityBooking FB "
                      + "JOIN Facility F "
                      + "ON FB.facilityID = F.facilityID "
                      + "JOIN Student S "
                      + "ON FB.studentID = S.studentID "
                      + "ORDER BY FB.bookingID DESC";

                ps = conn.prepareStatement(sql);
            }

            rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row =
                        new Object[10];

                row[0] =
                        rs.getInt("bookingID");

                row[1] =
                        rs.getString("studentID");

                row[2] =
                        rs.getString("studentName");

                row[3] =
                        rs.getString("facilityName");

                row[4] =
                        rs.getDate("bookingDate");

                row[5] =
                        rs.getTime("startTime");

                row[6] =
                        rs.getTime("endTime");

                row[7] =
                        rs.getString("purpose");

                row[8] =
                        rs.getInt("numberOfParticipants");

                row[9] =
                        rs.getString("bookingStatus");

                if ("Pending".equals(
                        row[9])) {

                    pendingList.add(row);

                } else {

                    historyList.add(row);
                }
            }

            request.setAttribute(
                    "pendingList",
                    pendingList);

            request.setAttribute(
                    "historyList",
                    historyList);

            request.setAttribute(
                    "keyword",
                    keyword);

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/admin/facilityApproval.jsp");

            rd.forward(
                    request,
                    response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : "
                    + e.getMessage());

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}