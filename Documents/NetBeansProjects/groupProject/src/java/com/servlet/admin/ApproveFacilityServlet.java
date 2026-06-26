/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;

import util.DBConnection;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/ApproveFacilityServlet")
public class ApproveFacilityServlet extends HttpServlet {

@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {

    try {

        HttpSession session =
                request.getSession();

        String adminID =
                (String) session.getAttribute(
                        "adminID");

        int bookingID =
                Integer.parseInt(
                        request.getParameter(
                                "bookingID"));

        String action =
                request.getParameter(
                        "action");

        Connection conn =
                DBConnection.getConnection();

        /* UPDATE FACILITY BOOKING */

        String updateBooking =
                "UPDATE FacilityBooking "
              + "SET bookingStatus=? "
              + "WHERE bookingID=?";

        PreparedStatement ps =
                conn.prepareStatement(
                        updateBooking);

        ps.setString(
                1,
                action);

        ps.setInt(
                2,
                bookingID);

        ps.executeUpdate();

        /* INSERT APPROVAL RECORD */

        String insertApproval =
                "INSERT INTO FacilityApproval "
              + "(bookingID, adminID, "
              + "approvalStatus, remarks) "
              + "VALUES (?, ?, ?, ?)";

        PreparedStatement psApproval =
                conn.prepareStatement(
                        insertApproval);

        psApproval.setInt(
                1,
                bookingID);

        psApproval.setString(
                2,
                adminID);

        psApproval.setString(
                3,
                action);

        psApproval.setString(
                4,
                "-");

        psApproval.executeUpdate();

        conn.close();

        response.sendRedirect(
                "AdminFacilityListServlet");

    } catch(Exception e) {

        e.printStackTrace();

        response.getWriter().println(
                "Approval Error: "
                + e.getMessage());
    }
}

}
