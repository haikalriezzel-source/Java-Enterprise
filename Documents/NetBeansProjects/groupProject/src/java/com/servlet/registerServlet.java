/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.bean.registerBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class registerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String studentID = request.getParameter("studentID");
        String studentName = request.getParameter("studentName");
        String studentDOB = request.getParameter("studentDOB");
        String programme = request.getParameter("programme");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        // Empty Field Validation
        if (studentID == null || studentID.trim().isEmpty()
                || studentName == null || studentName.trim().isEmpty()
                || studentDOB == null || studentDOB.trim().isEmpty()
                || programme == null || programme.trim().isEmpty()
                || phoneNumber == null || phoneNumber.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.sendRedirect(
                    "register.jsp?error=empty");

            return;
        }

        registerBean student = new registerBean();

        student.setStudentID(studentID);
        student.setStudentName(studentName);
        student.setStudentDOB(studentDOB);
        student.setProgramme(programme);
        student.setPhoneNumber(phoneNumber);
        student.setPassword(password);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Class.forName(
                    "org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/SportsDB",
                    "sukan",
                    "sukan");

            // Check Duplicate Student ID
            String checkSql =
                    "SELECT studentID "
                    + "FROM Student "
                    + "WHERE studentID = ?";

            ps = conn.prepareStatement(checkSql);
            ps.setString(1, studentID);

            rs = ps.executeQuery();

            if (rs.next()) {

                response.sendRedirect(
                        "register.jsp?error=duplicate");

                return;
            }

            rs.close();
            ps.close();

            String sql =
                    "INSERT INTO Student "
                    + "(studentID, password, studentName, "
                    + "studentDOB, programme, phoneNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getStudentName());
            ps.setDate(4,
                    java.sql.Date.valueOf(
                            student.getStudentDOB()));
            ps.setString(5, student.getProgramme());
            ps.setString(6, student.getPhoneNumber());

            ps.executeUpdate();

            response.sendRedirect(
                    "login.jsp?register=success");

        } catch (IllegalArgumentException e) {

            e.printStackTrace();

            response.sendRedirect(
                    "register.jsp?error=date");

        } catch (java.sql.SQLException e) {

            e.printStackTrace();

            response.sendRedirect(
                    "register.jsp?error=database");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    "register.jsp?error=system");

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
