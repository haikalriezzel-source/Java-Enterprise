/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;

import com.bean.facilityBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author haikalriez
 */
@WebServlet("/facilityList")
public class ViewFacilityServletStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<facilityBean> facilityList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Class.forName(
                    "org.apache.derby.jdbc.ClientDriver");

            conn =
                    DBConnection.getConnection();

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

                ps =
                        conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT * FROM Facility";

                ps =
                        conn.prepareStatement(sql);
            }

            rs =
                    ps.executeQuery();

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

                facility.setOpenTime(
                        rs.getString("openTime"));

                facility.setCloseTime(
                        rs.getString("closeTime"));

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

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/student/facilityList.jsp");

            rd.forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

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
