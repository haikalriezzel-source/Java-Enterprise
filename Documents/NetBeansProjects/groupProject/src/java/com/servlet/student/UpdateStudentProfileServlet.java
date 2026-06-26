/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;

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

@WebServlet("/UpdateStudentProfileServlet")
public class UpdateStudentProfileServlet extends HttpServlet {

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

            String password =
                    request.getParameter(
                            "password");

            String studentDOB =
                    request.getParameter(
                            "studentDOB");

            String phoneNumber =
                    request.getParameter(
                            "phoneNumber");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE Student "
                  + "SET studentName=?, "
                  + "password=?, "
                  + "studentDOB=?, "
                  + "phoneNumber=? "
                  + "WHERE studentID=?";

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    studentName);

            ps.setString(
                    2,
                    password);

            ps.setString(
                    3,
                    studentDOB);

            ps.setString(
                    4,
                    phoneNumber);

            ps.setString(
                    5,
                    studentID);

            ps.executeUpdate();

            conn.close();

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "studentName",
                    studentName);

            response.sendRedirect(
                    request.getContextPath()
                    + "/studentDashboard");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Update Error : "
                    + e.getMessage());
        }
    }
}
