/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;

import com.bean.facilityBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haikalriez
 */
@WebServlet("/UpdateFacilityServlet")
public class UpdateFacilityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int facilityID =
                    Integer.parseInt(
                            request.getParameter(
                                    "facilityID"));

            String facilityName =
                    request.getParameter(
                            "facilityName");

            String location =
                    request.getParameter(
                            "location");

            int capacity =
                    Integer.parseInt(
                            request.getParameter(
                                    "capacity"));

            String openTime =
                    request.getParameter(
                            "openTime");

            String closeTime =
                    request.getParameter(
                            "closeTime");

            String facilityStatus =
                    request.getParameter(
                            "facilityStatus");

            String facilityImage =
                    request.getParameter(
                            "facilityImage");

            facilityBean facility =
                    new facilityBean();

            facility.setFacilityID(
                    facilityID);

            facility.setFacilityName(
                    facilityName);

            facility.setLocation(
                    location);

            facility.setCapacity(
                    capacity);

            facility.setOpenTime(
                    openTime);

            facility.setCloseTime(
                    closeTime);

            facility.setFacilityStatus(
                    facilityStatus);

            facility.setFacilityImage(
                    facilityImage);

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Facility "
                    + "SET facilityName=?, "
                    + "location=?, "
                    + "capacity=?, "
                    + "openTime=?, "
                    + "closeTime=?, "
                    + "facilityStatus=?, "
                    + "facilityImage=? "
                    + "WHERE facilityID=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    facility.getFacilityName());

            ps.setString(
                    2,
                    facility.getLocation());

            ps.setInt(
                    3,
                    facility.getCapacity());

            ps.setTime(
                    4,
                    java.sql.Time.valueOf(
                            facility.getOpenTime()
                            + ":00"));

            ps.setTime(
                    5,
                    java.sql.Time.valueOf(
                            facility.getCloseTime()
                            + ":00"));

            ps.setString(
                    6,
                    facility.getFacilityStatus());

            ps.setString(
                    7,
                    facility.getFacilityImage());

            ps.setInt(
                    8,
                    facility.getFacilityID());

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    "ViewFacilityServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error: "
                    + e.getMessage());
        }
    }
}
