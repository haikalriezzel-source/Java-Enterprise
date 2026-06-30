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
@WebServlet("/DisposeEquipmentServlet")
public class DisposeEquipmentServlet extends HttpServlet {

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

            int disposeQuantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "disposeQuantity"));

            conn =
                    DBConnection.getConnection();

            String checkSql =
                    "SELECT damagedQuantity, totalQuantity "
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

            int damaged = 0;
            int total = 0;

            if (rs.next()) {

                damaged =
                        rs.getInt(
                                "damagedQuantity");

                total =
                        rs.getInt(
                                "totalQuantity");

            }

            if (disposeQuantity <= 0) {

                response.getWriter().println(

                        "<script>"
                      + "alert('Invalid dispose quantity!');"
                      + "history.back();"
                      + "</script>");

                return;

            }

            if (disposeQuantity > damaged) {

                response.getWriter().println(

                        "<script>"
                      + "alert('Dispose quantity exceeds damaged stock!');"
                      + "history.back();"
                      + "</script>");

                return;

            }

            if (disposeQuantity > total) {

                response.getWriter().println(

                        "<script>"
                      + "alert('Invalid inventory data!');"
                      + "history.back();"
                      + "</script>");

                return;

            }

            String updateSql =
                    "UPDATE Equipment "
                  + "SET damagedQuantity = damagedQuantity - ?, "
                  + "totalQuantity = totalQuantity - ? "
                  + "WHERE equipmentID=?";

            PreparedStatement psUpdate =
                    conn.prepareStatement(
                            updateSql);

            psUpdate.setInt(
                    1,
                    disposeQuantity);

            psUpdate.setInt(
                    2,
                    disposeQuantity);

            psUpdate.setInt(
                    3,
                    equipmentID);

            psUpdate.executeUpdate();

            psUpdate.close();
            rs.close();
            psCheck.close();
            conn.close();

            response.sendRedirect(
                    request.getContextPath()
                    + "/ViewEquipmentServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(

                    "Dispose Error : "
                    + e.getMessage());

        }

    }

}
