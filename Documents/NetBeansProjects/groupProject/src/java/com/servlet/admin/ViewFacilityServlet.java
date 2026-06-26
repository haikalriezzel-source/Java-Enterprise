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
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author haikalriez
 */
@WebServlet("/ViewFacilityServlet")
public class ViewFacilityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

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
                        "SELECT * FROM Facility "
                      + "WHERE LOWER(facilityName) LIKE ? "
                      + "OR LOWER(location) LIKE ? "
                      + "OR LOWER(facilityStatus) LIKE ?";

                ps = conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT * FROM Facility";

                ps = conn.prepareStatement(sql);
            }

            rs = ps.executeQuery();

            ArrayList<facilityBean> facilityList =
                    new ArrayList<>();

            while (rs.next()) {

                facilityBean facility =
                        new facilityBean();

                facility.setFacilityID(
                        rs.getInt("facilityID"));

                facility.setFacilityName(
                        rs.getString("facilityName"));

                facility.setLocation(
                        rs.getString("location"));

                facility.setCapacity(
                        rs.getInt("capacity"));

                facility.setFacilityStatus(
                        rs.getString("facilityStatus"));

                facility.setFacilityImage(
                        rs.getString("facilityImage"));

                facilityList.add(facility);
            }

            request.setAttribute(
                    "facilityList",
                    facilityList);

            request.setAttribute(
                    "keyword",
                    keyword);

            request.getRequestDispatcher(
                    "/admin/manageFacility.jsp")
                    .forward(request, response);

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
