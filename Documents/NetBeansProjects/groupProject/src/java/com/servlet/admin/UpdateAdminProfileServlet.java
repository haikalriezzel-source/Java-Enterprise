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
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateAdminProfileServlet")
public class UpdateAdminProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String adminID =
                    request.getParameter(
                            "adminID");

            String adminName =
                    request.getParameter(
                            "adminName");

            String password =
                    request.getParameter(
                            "password");

            String adminPhone =
                    request.getParameter(
                            "adminPhone");

            String adminDOB =
                    request.getParameter(
                            "adminDOB");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Admin "
                  + "SET adminName=?, "
                  + "password=?, "
                  + "adminPhone=?, "
                  + "adminDOB=? "
                  + "WHERE adminID=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    adminName);

            ps.setString(
                    2,
                    password);

            ps.setString(
                    3,
                    adminPhone);

            ps.setString(
                    4,
                    adminDOB);

            ps.setString(
                    5,
                    adminID);

            ps.executeUpdate();

            conn.close();

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "adminName",
                    adminName);

            response.sendRedirect(
                    request.getContextPath()
                    + "/dashboardAdmin");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error : "
                    + e.getMessage());
        }
    }
}
