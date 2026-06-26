package com.servlet.student;

import com.bean.student;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EditStudentProfileServlet")
public class EditStudentProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session =
                    request.getSession();

            String studentID =
                    (String) session.getAttribute(
                            "studentID");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * "
                  + "FROM Student "
                  + "WHERE studentID=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    studentID);

            ResultSet rs =
                    ps.executeQuery();

            student student =
                    new student();

            if (rs.next()) {

                student.setStudentID(
                        rs.getString(
                                "studentID"));

                student.setPassword(
                        rs.getString(
                                "password"));

                student.setStudentName(
                        rs.getString(
                                "studentName"));

                student.setStudentDOB(
                        rs.getDate(
                                "studentDOB"));

                student.setProgramme(
                        rs.getString(
                                "programme"));

                student.setPhoneNumber(
                        rs.getString(
                                "phoneNumber"));
            }

            request.setAttribute(
                    "student",
                    student);

            conn.close();

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/student/editStudentProfile.jsp");

            rd.forward(
                    request,
                    response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : "
                    + e.getMessage());
        }
    }
}