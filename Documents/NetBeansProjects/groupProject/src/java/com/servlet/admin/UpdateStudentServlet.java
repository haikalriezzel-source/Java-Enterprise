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
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String studentID =
                    request.getParameter(
                            "studentID");

            String studentName =
                    request.getParameter(
                            "studentName");

            String studentDOB =
                    request.getParameter(
                            "studentDOB");

            String programme =
                    request.getParameter(
                            "programme");

            String phoneNumber =
                    request.getParameter(
                            "phoneNumber");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Student "
                  + "SET studentName=?, "
                  + "studentDOB=?, "
                  + "programme=?, "
                  + "phoneNumber=? "
                  + "WHERE studentID=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    studentName);

            ps.setString(
                    2,
                    studentDOB);

            ps.setString(
                    3,
                    programme);

            ps.setString(
                    4,
                    phoneNumber);

            ps.setString(
                    5,
                    studentID);

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    request.getContextPath()
                    + "/ViewStudentServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error : "
                    + e.getMessage());
        }
    }
}
