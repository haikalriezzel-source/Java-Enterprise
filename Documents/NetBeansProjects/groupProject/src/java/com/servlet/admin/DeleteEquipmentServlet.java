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
@WebServlet("/DeleteEquipmentServlet")
public class DeleteEquipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int equipmentID =
                    Integer.parseInt(
                            request.getParameter("id"));

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM Equipment "
                    + "WHERE equipmentID = ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, equipmentID);

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    "ViewEquipmentServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Delete Error: "
                    + e.getMessage());
        }
    }
}
