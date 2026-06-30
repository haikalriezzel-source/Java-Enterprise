package com.servlet.admin;

import com.bean.ReturnRecordBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminReturnListServlet")
public class AdminReturnListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ReturnRecordBean> pendingList =
                new ArrayList<>();

        ArrayList<ReturnRecordBean> historyList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn =
                    DBConnection.getConnection();

            String keyword =
                    request.getParameter(
                            "keyword");

            String sql;

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT "
                      + "R.returnID, "
                      + "R.loanID, "
                      + "L.studentID, "
                      + "S.studentName, "
                      + "R.equipmentID, "
                      + "E.equipmentName, "
                      + "R.returnQuantity, "
                      + "R.returnDate, "
                      + "R.equipmentCondition, "
                      + "R.returnStatus "
                      + "FROM ReturnRecord R "
                      + "JOIN LoanEquipment L "
                      + "ON R.loanID = L.loanID "
                      + "JOIN Student S "
                      + "ON L.studentID = S.studentID "
                      + "JOIN Equipment E "
                      + "ON R.equipmentID = E.equipmentID "
                      + "WHERE LOWER(L.studentID) LIKE ? "
                      + "OR LOWER(S.studentName) LIKE ? "
                      + "OR LOWER(R.returnStatus) LIKE ? "
                      + "OR LOWER(R.equipmentCondition) LIKE ? "
                      + "OR LOWER(E.equipmentName) LIKE ? "
                      + "OR CAST(R.equipmentID AS CHAR(20)) LIKE ? "
                      + "ORDER BY R.returnID DESC";

                ps =
                        conn.prepareStatement(
                                sql);

                String search =
                        "%" +
                        keyword.toLowerCase()
                        + "%";

                ps.setString(
                        1,
                        search);

                ps.setString(
                        2,
                        search);

                ps.setString(
                        3,
                        search);

                ps.setString(
                        4,
                        search);

                ps.setString(
                        5,
                        search);

                ps.setString(
                        6,
                        search);

            } else {

                sql =
                        "SELECT "
                      + "R.returnID, "
                      + "R.loanID, "
                      + "L.studentID, "
                      + "S.studentName, "
                      + "R.equipmentID, "
                      + "E.equipmentName, "
                      + "R.returnQuantity, "
                      + "R.returnDate, "
                      + "R.equipmentCondition, "
                      + "R.returnStatus "
                      + "FROM ReturnRecord R "
                      + "JOIN LoanEquipment L "
                      + "ON R.loanID = L.loanID "
                      + "JOIN Student S "
                      + "ON L.studentID = S.studentID "
                      + "JOIN Equipment E "
                      + "ON R.equipmentID = E.equipmentID "
                      + "ORDER BY R.returnID DESC";

                ps =
                        conn.prepareStatement(
                                sql);

            }

            rs =
                    ps.executeQuery();

            while (rs.next()) {
                                ReturnRecordBean record =
                        new ReturnRecordBean();

                record.setReturnID(
                        rs.getInt(
                                "returnID"));

                record.setLoanID(
                        rs.getInt(
                                "loanID"));

                record.setStudentID(
                        rs.getString(
                                "studentID"));

                record.setStudentName(
                        rs.getString(
                                "studentName"));

                record.setEquipmentID(
                        rs.getInt(
                                "equipmentID"));

                record.setEquipmentName(
                        rs.getString(
                                "equipmentName"));

                record.setReturnQuantity(
                        rs.getInt(
                                "returnQuantity"));

                record.setReturnDate(
                        rs.getDate(
                                "returnDate"));

                record.setEquipmentCondition(
                        rs.getString(
                                "equipmentCondition"));

                record.setReturnStatus(
                        rs.getString(
                                "returnStatus"));

                if ("Pending".equalsIgnoreCase(
                        record.getReturnStatus())) {

                    pendingList.add(
                            record);

                } else {

                    historyList.add(
                            record);

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

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/admin/returnApproval.jsp");

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