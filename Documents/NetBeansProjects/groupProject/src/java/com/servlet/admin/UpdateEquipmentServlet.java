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
    protected void doPost(HttpServletRequest request,
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

            int totalQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "totalQuantity"));

            int availableQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "availableQuantity"));

            int maintenanceQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "maintenanceQuantity"));

            int damagedQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "damagedQuantity"));

            String equipmentImage =
                    request.getParameter(
                            "equipmentImage");

            // Validation

            if (availableQuantity
                    + maintenanceQuantity
                    + damagedQuantity
                    != totalQuantity) {

                response.getWriter().println(

                        "<h2>Inventory Error</h2>"
                      + "<p>"
                      + "Total Quantity must equal "
                      + "Available + Maintenance + Damaged."
                      + "</p>"
                      + "<br>"
                      + "<a href='javascript:history.back()'>"
                      + "Go Back"
                      + "</a>");

                return;

            }

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Equipment "
                  + "SET equipmentName=?, "
                  + "brandModel=?, "
                  + "totalQuantity=?, "
                  + "availableQuantity=?, "
                  + "maintenanceQuantity=?, "
                  + "damagedQuantity=?, "
                  + "equipmentImage=? "
                  + "WHERE equipmentID=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    equipmentName);

            ps.setString(
                    2,
                    brandModel);

            ps.setInt(
                    3,
                    totalQuantity);

            ps.setInt(
                    4,
                    availableQuantity);

            ps.setInt(
                    5,
                    maintenanceQuantity);

            ps.setInt(
                    6,
                    damagedQuantity);

            ps.setString(
                    7,
                    equipmentImage);

            ps.setInt(
                    8,
                    equipmentID);

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    request.getContextPath()
                    + "/ViewEquipmentServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error : "
                    + e.getMessage());

        }

    }

}
    

