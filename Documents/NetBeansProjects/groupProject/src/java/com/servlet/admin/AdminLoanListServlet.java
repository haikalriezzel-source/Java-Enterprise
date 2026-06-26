/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;
import util.DBConnection;
import com.bean.LoanEquipmentBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/AdminLoanListServlet")
public class AdminLoanListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<LoanEquipmentBean> pendingList =
                new ArrayList<>();

        ArrayList<LoanEquipmentBean> historyList =
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
                        "SELECT "
                      + "l.loanID, "
                      + "l.studentID, "
                      + "s.studentName, "
                      + "e.equipmentName, "
                      + "l.startDate, "
                      + "l.endDate, "
                      + "l.loanQuantity, "
                      + "l.loanStatus "
                      + "FROM LoanEquipment l "
                      + "JOIN Student s "
                      + "ON l.studentID = s.studentID "
                      + "JOIN Equipment e "
                      + "ON l.equipmentID = e.equipmentID "
                      + "WHERE LOWER(l.studentID) LIKE ? "
                      + "OR LOWER(s.studentName) LIKE ? "
                      + "OR LOWER(e.equipmentName) LIKE ? "
                      + "OR LOWER(l.loanStatus) LIKE ? "
                      + "ORDER BY l.loanID DESC";

                ps =
                        conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);
                ps.setString(4, search);

            } else {

                sql =
                        "SELECT "
                      + "l.loanID, "
                      + "l.studentID, "
                      + "s.studentName, "
                      + "e.equipmentName, "
                      + "l.startDate, "
                      + "l.endDate, "
                      + "l.loanQuantity, "
                      + "l.loanStatus "
                      + "FROM LoanEquipment l "
                      + "JOIN Student s "
                      + "ON l.studentID = s.studentID "
                      + "JOIN Equipment e "
                      + "ON l.equipmentID = e.equipmentID "
                      + "ORDER BY l.loanID DESC";

                ps =
                        conn.prepareStatement(sql);
            }

            rs =
                    ps.executeQuery();

            while (rs.next()) {

                LoanEquipmentBean loan =
                        new LoanEquipmentBean();

                loan.setLoanID(
                        rs.getInt("loanID"));

                loan.setStudentID(
                        rs.getString("studentID"));

                loan.setStudentName(
                        rs.getString("studentName"));

                loan.setEquipmentName(
                        rs.getString("equipmentName"));

                loan.setStartDate(
                        rs.getDate("startDate"));

                loan.setEndDate(
                        rs.getDate("endDate"));

                loan.setLoanQuantity(
                        rs.getInt("loanQuantity"));

                loan.setLoanStatus(
                        rs.getString("loanStatus"));

                if ("Pending".equals(
                        loan.getLoanStatus())) {

                    pendingList.add(loan);

                } else {

                    historyList.add(loan);
                }
            }

            request.setAttribute(
                    "pendingList",
                    pendingList);

            request.setAttribute(
                    "historyList",
                    historyList);

            request.setAttribute(
                    "keyword",
                    keyword);

            request.getRequestDispatcher(
                    "/admin/loanApproval.jsp")
                    .forward(request, response);

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




