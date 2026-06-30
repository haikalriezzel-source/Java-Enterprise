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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author haikalriez
 */
@WebServlet("/RepairEquipmentServlet")
public class RepairEquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Connection conn = null;

        try {

            int equipmentID =
                    Integer.parseInt(
                            request.getParameter(
                                    "equipmentID"));

            int repairQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "repairQuantity"));

            conn =
                    DBConnection.getConnection();

            String checkSql =
                    "SELECT maintenanceQuantity "
                  + "FROM Equipment "
                  + "WHERE equipmentID=?";

            PreparedStatement psCheck =
                    conn.prepareStatement(
                            checkSql);

            psCheck.setInt(
                    1,
                    equipmentID);

            ResultSet rs =
                    psCheck.executeQuery();

            int maintenance = 0;

            if(rs.next()){

                maintenance =
                        rs.getInt(
                                "maintenanceQuantity");

            }

            if(repairQuantity <= 0){

                response.getWriter().println(

                        "<script>"
                      + "alert('Invalid repair quantity!');"
                      + "history.back();"
                      + "</script>");

                return;

            }

            if(repairQuantity > maintenance){

                response.getWriter().println(

                        "<script>"
                      + "alert('Repair quantity exceeds maintenance stock!');"
                      + "history.back();"
                      + "</script>");

                return;

            }

            String updateSql =
                    "UPDATE Equipment "
                  + "SET maintenanceQuantity = maintenanceQuantity - ?, "
                  + "availableQuantity = availableQuantity + ? "
                  + "WHERE equipmentID=?";

            PreparedStatement psUpdate =
                    conn.prepareStatement(
                            updateSql);

            psUpdate.setInt(
                    1,
                    repairQuantity);

            psUpdate.setInt(
                    2,
                    repairQuantity);

            psUpdate.setInt(
                    3,
                    equipmentID);

            psUpdate.executeUpdate();

            psUpdate.close();
            psCheck.close();
            rs.close();
            conn.close();

            response.sendRedirect(
                    request.getContextPath()
                    + "/ViewEquipmentServlet");

        } catch(Exception e){

            e.printStackTrace();

            response.getWriter().println(

                    "Repair Error : "
                    + e.getMessage());

        }

    }

}
