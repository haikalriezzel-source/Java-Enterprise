package com.servlet.student;

import util.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/BorrowEquipmentServlet")
public class BorrowEquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Connection conn = null;

        try {

            HttpSession session =
                    request.getSession(false);

            if (session == null
                    || session.getAttribute("studentID") == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/login.jsp");

                return;
            }

            String studentID =
                    (String) session.getAttribute(
                            "studentID");

            int equipmentID =
                    Integer.parseInt(
                            request.getParameter(
                                    "equipmentID"));

            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "loanQuantity"));

            String startDate =
                    request.getParameter(
                            "startDate");

            String endDate =
                    request.getParameter(
                            "endDate");

            // Date Validation
            LocalDate today =
                    LocalDate.now();

            LocalDate borrowDate =
                    LocalDate.parse(startDate);

            LocalDate returnDate =
                    LocalDate.parse(endDate);

            if (borrowDate.isBefore(today)) {

                response.getWriter().println(
                        "<script>"
                        + "alert('Borrow date cannot be earlier than today!');"
                        + "history.back();"
                        + "</script>");

                return;
            }

            if (returnDate.isBefore(borrowDate)) {

                response.getWriter().println(
                        "<script>"
                        + "alert('Return date cannot be earlier than borrow date!');"
                        + "history.back();"
                        + "</script>");

                return;
            }

            // Quantity Validation
            if (quantity <= 0) {

                response.getWriter().println(
                        "<script>"
                        + "alert('Quantity must be greater than 0!');"
                        + "history.back();"
                        + "</script>");

                return;
            }

            conn =
                    DBConnection.getConnection();

            // Check Equipment Stock
            String stockSql =
                    "SELECT availableQuantity "
                    + "FROM Equipment "
                    + "WHERE equipmentID = ?";

            PreparedStatement psStock =
                    conn.prepareStatement(
                            stockSql);

            psStock.setInt(
                    1,
                    equipmentID);

            ResultSet rsStock =
                    psStock.executeQuery();

            int availableStock = 0;

            if (rsStock.next()) {

                availableStock =
                        rsStock.getInt(
                                "availableQuantity");
            }

            if (quantity > availableStock) {

                response.getWriter().println(
                        "<script>"
                        + "alert('Requested quantity exceeds available stock!');"
                        + "history.back();"
                        + "</script>");

                return;
            }

            // Insert Borrow Request
            String sql =
                    "INSERT INTO LoanEquipment "
                    + "(studentID, equipmentID, "
                    + "startDate, endDate, "
                    + "loanQuantity, loanStatus) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    studentID);

            ps.setInt(
                    2,
                    equipmentID);

            ps.setDate(
                    3,
                    Date.valueOf(startDate));

            ps.setDate(
                    4,
                    Date.valueOf(endDate));

            ps.setInt(
                    5,
                    quantity);

            ps.setString(
                    6,
                    "Pending");

            ps.executeUpdate();

            response.sendRedirect(
                    request.getContextPath()
                    + "/MyLoanServlet");

        } catch (NumberFormatException e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                    + "alert('Invalid quantity entered!');"
                    + "history.back();"
                    + "</script>");

        } catch (SQLException e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                    + "alert('Database error occurred!');"
                    + "history.back();"
                    + "</script>");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>"
                    + "alert('Unexpected system error occurred!');"
                    + "history.back();"
                    + "</script>");

        } finally {

            try {

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}