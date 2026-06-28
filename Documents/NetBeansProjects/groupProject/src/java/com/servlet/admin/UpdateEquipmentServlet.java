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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author haikalriez
 */
@WebServlet("/UpdateEquipmentServlet")
public class UpdateEquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int equipmentID =
                    Integer.parseInt(
                            request.getParameter(
                                    "equipmentID"));

            String equipmentName =
                    request.getParameter(
                            "equipmentName");

            String brandModel =
                    request.getParameter(
                            "brandModel");

            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "quantity"));

            String equipmentStatus =
                    request.getParameter(
                            "equipmentStatus");

            String equipmentImage =
                    request.getParameter(
                            "equipmentImage");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Equipment "
                  + "SET equipmentName=?, "
                  + "brandModel=?, "
                  + "quantity=?, "
                  + "equipmentStatus=?, "
                  + "equipmentImage=? "
                  + "WHERE equipmentID=?";

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    equipmentName);

            ps.setString(
                    2,
                    brandModel);

            ps.setInt(
                    3,
                    quantity);

            ps.setString(
                    4,
                    equipmentStatus);

            ps.setString(
                    5,
                    equipmentImage);

            ps.setInt(
                    6,
                    equipmentID);

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    request.getContextPath()
                    + "/ViewEquipmentServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error: "
                    + e.getMessage());
        }
    }
}
    

