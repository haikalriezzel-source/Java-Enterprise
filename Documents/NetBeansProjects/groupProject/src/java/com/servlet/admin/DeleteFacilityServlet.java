/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;

import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author haikalriez
 */
@WebServlet("/DeleteFacilityServlet")
public class DeleteFacilityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psDelete = null;
        ResultSet rs = null;

        try {

            int facilityID =
                    Integer.parseInt(
                            request.getParameter("id"));

            conn =
                    DBConnection.getConnection();

            // Check whether the facility has booking history
            String checkSql =
                    "SELECT COUNT(*) "
                  + "FROM FacilityBooking "
                  + "WHERE facilityID = ?";

            psCheck =
                    conn.prepareStatement(
                            checkSql);

            psCheck.setInt(
                    1,
                    facilityID);

            rs =
                    psCheck.executeQuery();

            int totalBooking = 0;

            if (rs.next()) {

                totalBooking =
                        rs.getInt(1);

            }

            if (totalBooking > 0) {

                response.getWriter().println(

                        "<script>"
                      + "alert('This facility cannot be deleted because it has booking history.');"
                      + "window.location='ViewFacilityServlet';"
                      + "</script>");

                return;

            }

            // Delete facility
            String deleteSql =
                    "DELETE FROM Facility "
                  + "WHERE facilityID = ?";

            psDelete =
                    conn.prepareStatement(
                            deleteSql);

            psDelete.setInt(
                    1,
                    facilityID);

            psDelete.executeUpdate();

            response.sendRedirect(
                    "ViewFacilityServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Delete Error : "
                  + e.getMessage());

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (psCheck != null)
                    psCheck.close();

                if (psDelete != null)
                    psDelete.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}
