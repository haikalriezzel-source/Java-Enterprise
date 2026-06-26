package com.servlet.admin;

import com.bean.student;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/ViewStudentServlet")
public class ViewStudentServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<student> studentList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn =
                    DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT * FROM Student "
                      + "WHERE LOWER(studentID) LIKE ? "
                      + "OR LOWER(studentName) LIKE ? "
                      + "OR LOWER(programme) LIKE ? "
                      + "ORDER BY studentID";

                ps =
                        conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT * FROM Student "
                      + "ORDER BY studentID";

                ps =
                        conn.prepareStatement(sql);
            }

            rs =
                    ps.executeQuery();

            while (rs.next()) {

                student s =
                        new student();

                s.setStudentID(
                        rs.getString(
                                "studentID"));

                s.setStudentName(
                        rs.getString(
                                "studentName"));

                s.setStudentDOB(
                        rs.getDate(
                                "studentDOB"));

                s.setProgramme(
                        rs.getString(
                                "programme"));

                s.setPhoneNumber(
                        rs.getString(
                                "phoneNumber"));

                studentList.add(s);
            }

            request.setAttribute(
                    "studentList",
                    studentList);

            request.setAttribute(
                    "keyword",
                    keyword);

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/admin/manageStudent.jsp");

            rd.forward(
                    request,
                    response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : "
                    + e.getMessage());

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}