/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import com.bean.facilityBookingBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/MyFacilityBookingServlet")
public class MyFacilityBookingServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<facilityBookingBean> activeList =
                new ArrayList<>();

        ArrayList<facilityBookingBean> historyList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            HttpSession session =
                    request.getSession();

            String studentID =
                    (String) session.getAttribute(
                            "studentID");

            conn =
                    DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if(keyword != null
                    && !keyword.trim().isEmpty()){

                sql =
                        "SELECT "
                      + "FB.bookingID, "
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
                      + "WHERE FB.studentID=? "
                      + "AND (LOWER(F.facilityName) LIKE ? "
                      + "OR LOWER(FB.purpose) LIKE ? "
                      + "OR LOWER(FB.bookingStatus) LIKE ?) "
                      + "ORDER BY FB.bookingID DESC";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(1, studentID);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(2, search);
                ps.setString(3, search);
                ps.setString(4, search);

            }else{

                sql =
                        "SELECT "
                      + "FB.bookingID, "
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
                      + "WHERE FB.studentID=? "
                      + "ORDER BY FB.bookingID DESC";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(1, studentID);
            }

            rs =
                    ps.executeQuery();

            while(rs.next()){

                facilityBookingBean booking =
                        new facilityBookingBean();

                booking.setBookingID(
                        rs.getInt("bookingID"));

                booking.setFacilityName(
                        rs.getString("facilityName"));

                booking.setBookingDate(
                        rs.getDate("bookingDate"));

                booking.setStartTime(
                        rs.getTime("startTime"));

                booking.setEndTime(
                        rs.getTime("endTime"));

                booking.setPurpose(
                        rs.getString("purpose"));

                booking.setNumberOfParticipants(
                        rs.getInt("numberOfParticipants"));

                booking.setBookingStatus(
                        rs.getString("bookingStatus"));

                if("Pending".equals(
                        booking.getBookingStatus())
                        || "Approved".equals(
                                booking.getBookingStatus())){

                    activeList.add(booking);

                }else{

                    historyList.add(booking);
                }
            }

            request.setAttribute(
                    "activeList",
                    activeList);

            request.setAttribute(
                    "historyList",
                    historyList);

            request.setAttribute(
                    "keyword",
                    keyword);

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "student/myFacilityBooking.jsp");

            rd.forward(
                    request,
                    response);

        }catch(Exception e){

            e.printStackTrace();

        }finally{

            try{

                if(rs!=null) rs.close();

                if(ps!=null) ps.close();

                if(conn!=null) conn.close();

            }catch(Exception e){

                e.printStackTrace();
            }
        }
    }
}
