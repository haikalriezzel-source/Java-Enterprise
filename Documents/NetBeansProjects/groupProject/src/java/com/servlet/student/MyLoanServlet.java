/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import com.bean.LoanEquipmentBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/MyLoanServlet")
public class MyLoanServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<LoanEquipmentBean> activeList =
                new ArrayList<>();

        ArrayList<LoanEquipmentBean> historyList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            HttpSession session =
                    request.getSession();

            String studentID =
                    (String) session.getAttribute(
                            "studentID");

            conn =
                    DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT "
                      + "L.LOANID, "
                      + "E.EQUIPMENTNAME, "
                      + "L.STARTDATE, "
                      + "L.ENDDATE, "
                      + "L.LOANQUANTITY, "
                      + "L.LOANSTATUS "
                      + "FROM LOANEQUIPMENT L "
                      + "JOIN EQUIPMENT E "
                      + "ON L.EQUIPMENTID = E.EQUIPMENTID "
                      + "WHERE L.STUDENTID = ? "
                      + "AND (LOWER(E.EQUIPMENTNAME) LIKE ? "
                      + "OR LOWER(L.LOANSTATUS) LIKE ?) "
                      + "ORDER BY L.LOANID DESC";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(
                        1,
                        studentID);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT "
                      + "L.LOANID, "
                      + "E.EQUIPMENTNAME, "
                      + "L.STARTDATE, "
                      + "L.ENDDATE, "
                      + "L.LOANQUANTITY, "
                      + "L.LOANSTATUS "
                      + "FROM LOANEQUIPMENT L "
                      + "JOIN EQUIPMENT E "
                      + "ON L.EQUIPMENTID = E.EQUIPMENTID "
                      + "WHERE L.STUDENTID = ? "
                      + "ORDER BY L.LOANID DESC";

                ps =
                        conn.prepareStatement(sql);

                ps.setString(
                        1,
                        studentID);
            }

            rs =
                    ps.executeQuery();

            while (rs.next()) {

                LoanEquipmentBean loan =
                        new LoanEquipmentBean();

                loan.setLoanID(
                        rs.getInt("LOANID"));

                loan.setEquipmentName(
                        rs.getString("EQUIPMENTNAME"));

                loan.setStartDate(
                        rs.getDate("STARTDATE"));

                loan.setEndDate(
                        rs.getDate("ENDDATE"));

                loan.setLoanQuantity(
                        rs.getInt("LOANQUANTITY"));

                loan.setLoanStatus(
                        rs.getString("LOANSTATUS"));

                String status =
                        loan.getLoanStatus();

                if ("Pending".equals(status)
                        || "Approved".equals(status)) {

                    activeList.add(loan);

                } else {

                    historyList.add(loan);
                }
            }

            request.setAttribute(
                    "activeList",
                    activeList);

            request.setAttribute(
                    "historyList",
                    historyList);

            request.setAttribute(
                    "keyword",
                    keyword);

            if (session.getAttribute("scrollToHistory") != null) {

            request.setAttribute("scrollToHistory", true);

            session.removeAttribute("scrollToHistory");
            
            }
            
            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "student/myLoan.jsp");

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
