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
@WebServlet("/DeleteEquipmentServlet")
public class DeleteEquipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement psCheck = null;
        PreparedStatement psDelete = null;
        ResultSet rs = null;

        try {

            int equipmentID =
                    Integer.parseInt(
                            request.getParameter("id"));

            conn =
                    DBConnection.getConnection();

            // Check if equipment has loan history
            String checkSql =
                    "SELECT COUNT(*) "
                  + "FROM LoanEquipment "
                  + "WHERE equipmentID = ?";

            psCheck =
                    conn.prepareStatement(
                            checkSql);

            psCheck.setInt(
                    1,
                    equipmentID);

            rs =
                    psCheck.executeQuery();

            int totalLoan = 0;

            if (rs.next()) {

                totalLoan =
                        rs.getInt(1);

            }

            if (totalLoan > 0) {

                response.getWriter().println(

                        "<script>"
                      + "alert('This equipment cannot be deleted because it has loan history.');"
                      + "window.location='ViewEquipmentServlet';"
                      + "</script>");

                return;

            }

            String deleteSql =
                    "DELETE FROM Equipment "
                  + "WHERE equipmentID = ?";

            psDelete =
                    conn.prepareStatement(
                            deleteSql);

            psDelete.setInt(
                    1,
                    equipmentID);

            psDelete.executeUpdate();

            response.sendRedirect(
                    "ViewEquipmentServlet");

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
