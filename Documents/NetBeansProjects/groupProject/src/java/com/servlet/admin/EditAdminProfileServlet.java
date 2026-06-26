package com.servlet.admin;

import com.bean.adminBean;
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

@WebServlet("/EditAdminProfileServlet")
public class EditAdminProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session =
                    request.getSession();

            String adminID =
                    (String) session.getAttribute(
                            "adminID");

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * "
                  + "FROM Admin "
                  + "WHERE adminID = ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    adminID);

            ResultSet rs =
                    ps.executeQuery();

            adminBean admin =
                    new adminBean();

            if (rs.next()) {

                admin.setAdminID(
                        rs.getString(
                                "adminID"));

                admin.setPassword(
                        rs.getString(
                                "password"));

                admin.setAdminName(
                        rs.getString(
                                "adminName"));

                admin.setAdminPhone(
                        rs.getString(
                                "adminPhone"));

                admin.setAdminDOB(
                        rs.getString(
                                "adminDOB"));
            }

            request.setAttribute(
                    "admin",
                    admin);

            conn.close();

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/admin/editAdminProfile.jsp");

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