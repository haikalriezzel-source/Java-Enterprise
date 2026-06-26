/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/BookFacilityServlet")
public class BookFacilityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            HttpSession session =
                    request.getSession(false);

            if (session == null
                    || session.getAttribute("userID") == null) {

                response.sendRedirect("login.jsp");
                return;
            }

            String studentID =
                    (String) session.getAttribute(
                            "userID");

            String facilityID =
                    request.getParameter(
                            "facilityID");

            String bookingDate =
                    request.getParameter(
                            "bookingDate");

            String startTime =
                    request.getParameter(
                            "startTime");

            String endTime =
                    request.getParameter(
                            "endTime");

            String purpose =
                    request.getParameter(
                            "purpose");

            String participants =
                    request.getParameter(
                            "participants");

            // Booking Date Validation
            LocalDate today =
                    LocalDate.now();

            LocalDate selectedDate =
                    LocalDate.parse(
                            bookingDate);

            if (selectedDate.isBefore(today)) {

                response.getWriter().println(
                        "<script>"
                      + "alert('Booking date cannot be earlier than today!');"
                      + "history.back();"
                      + "</script>");

                return;
            }

            // Time Validation
            LocalTime bookingStart =
                    LocalTime.parse(
                            startTime);

            LocalTime bookingEnd =
                    LocalTime.parse(
                            endTime);

            if (!bookingEnd.isAfter(
                    bookingStart)) {

                response.getWriter().println(
                        "<script>"
                      + "alert('End time must be after start time!');"
                      + "history.back();"
                      + "</script>");

                return;
            }

            // Participants Validation
            int participantCount =
                    Integer.parseInt(
                            participants);

            if (participantCount <= 0) {

                response.getWriter().println(
                        "<script>"
                      + "alert('Number of participants must be greater than 0!');"
                      + "history.back();"
                      + "</script>");

                return;
            }

            Class.forName(
                    "org.apache.derby.jdbc.ClientDriver");

            conn =
                    DBConnection.getConnection();

            // Get Facility Details
            String facilitySql =
                    "SELECT OPENTIME, CLOSETIME, CAPACITY "
                  + "FROM Facility "
                  + "WHERE FACILITYID=?";

            ps =
                    conn.prepareStatement(
                            facilitySql);

            ps.setInt(
                    1,
                    Integer.parseInt(
                            facilityID));

            rs =
                    ps.executeQuery();

            if (rs.next()) {

                LocalTime openTime =
                        rs.getTime(
                                "OPENTIME")
                                .toLocalTime();

                LocalTime closeTime =
                        rs.getTime(
                                "CLOSETIME")
                                .toLocalTime();

                int capacity =
                        rs.getInt(
                                "CAPACITY");

                // Operating Hours Validation
                if (bookingStart.isBefore(
                        openTime)
                        || bookingEnd.isAfter(
                                closeTime)) {

                    response.getWriter().println(
                            "<script>"
                          + "alert('Booking time must be within facility operating hours!');"
                          + "history.back();"
                          + "</script>");

                    return;
                }

                // Capacity Validation
                if (participantCount
                        > capacity) {

                    response.getWriter().println(
                            "<script>"
                          + "alert('Number of participants exceeds facility capacity!');"
                          + "history.back();"
                          + "</script>");

                    return;
                }
            }

            rs.close();
            ps.close();

            // Booking Clash Validation
            String clashSql =
                    "SELECT BOOKINGID "
                  + "FROM FacilityBooking "
                  + "WHERE FACILITYID = ? "
                  + "AND BOOKINGDATE = ? "
                  + "AND BOOKINGSTATUS IN "
                  + "('Pending','Approved') "
                  + "AND (STARTTIME < ? "
                  + "AND ENDTIME > ?)";

            ps =
                    conn.prepareStatement(
                            clashSql);

            ps.setInt(
                    1,
                    Integer.parseInt(
                            facilityID));

            ps.setDate(
                    2,
                    java.sql.Date.valueOf(
                            bookingDate));

            ps.setTime(
                    3,
                    java.sql.Time.valueOf(
                            endTime + ":00"));

            ps.setTime(
                    4,
                    java.sql.Time.valueOf(
                            startTime + ":00"));

            rs =
                    ps.executeQuery();

            if (rs.next()) {

                response.getWriter().println(
                        "<script>"
                      + "alert('This facility is already booked during the selected time slot!');"
                      + "history.back();"
                      + "</script>");

                return;
            }

            rs.close();
            ps.close();

            // Insert Booking
            String sql =
                    "INSERT INTO FacilityBooking "
                  + "(studentID, facilityID, bookingDate, "
                  + "startTime, endTime, purpose, "
                  + "numberOfParticipants, bookingStatus) "
                  + "VALUES(?,?,?,?,?,?,?,?)";

            ps =
                    conn.prepareStatement(
                            sql);

            ps.setString(1, studentID);

            ps.setInt(
                    2,
                    Integer.parseInt(
                            facilityID));

            ps.setDate(
                    3,
                    java.sql.Date.valueOf(
                            bookingDate));

            ps.setTime(
                    4,
                    java.sql.Time.valueOf(
                            startTime + ":00"));

            ps.setTime(
                    5,
                    java.sql.Time.valueOf(
                            endTime + ":00"));

            ps.setString(
                    6,
                    purpose);

            ps.setInt(
                    7,
                    participantCount);

            ps.setString(
                    8,
                    "Pending");

            ps.executeUpdate();

            response.sendRedirect(
                    "facilityList");

        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                  + "alert('Invalid number entered!');"
                  + "history.back();"
                  + "</script>");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                  + "alert('System error occurred!');"
                  + "history.back();"
                  + "</script>");

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

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
