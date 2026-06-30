package com.servlet.admin;

import util.DBConnection;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ApproveLoanServlet")
public class ApproveLoanServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            HttpSession session =
                    request.getSession();

            String adminID =
                    (String) session.getAttribute(
                            "adminID");

            int loanID =
                    Integer.parseInt(
                            request.getParameter(
                                    "loanID"));

            String action =
                    request.getParameter(
                            "action");

            Connection conn =
                    DBConnection.getConnection();

            String loanSql =
                    "SELECT equipmentID, "
                  + "loanQuantity "
                  + "FROM LoanEquipment "
                  + "WHERE loanID=?";

            PreparedStatement psLoan =
                    conn.prepareStatement(
                            loanSql);

            psLoan.setInt(
                    1,
                    loanID);

            ResultSet rs =
                    psLoan.executeQuery();

            int equipmentID = 0;
            int loanQuantity = 0;

            if (rs.next()) {

                equipmentID =
                        rs.getInt(
                                "equipmentID");

                loanQuantity =
                        rs.getInt(
                                "loanQuantity");
            }

            if ("Approved".equals(action)) {

                String checkStock =
                        "SELECT availableQuantity "
                      + "FROM Equipment "
                      + "WHERE equipmentID=?";

                PreparedStatement psCheck =
                        conn.prepareStatement(
                                checkStock);

                psCheck.setInt(
                        1,
                        equipmentID);

                ResultSet rsCheck =
                        psCheck.executeQuery();

                int availableStock = 0;

                if (rsCheck.next()) {

                    availableStock =
                            rsCheck.getInt(
                                    "availableQuantity");
                }

                if (loanQuantity > availableStock) {

                    response.getWriter().println(
                            "<script>"
                          + "alert('Not enough available stock!');"
                          + "window.location='AdminLoanListServlet';"
                          + "</script>");

                    return;

                }

                String updateEquipment =
                        "UPDATE Equipment "
                      + "SET availableQuantity = availableQuantity - ? "
                      + "WHERE equipmentID=?";

                PreparedStatement psEquip =
                        conn.prepareStatement(
                                updateEquipment);

                psEquip.setInt(
                        1,
                        loanQuantity);

                psEquip.setInt(
                        2,
                        equipmentID);

                psEquip.executeUpdate();

                psEquip.close();

                psCheck.close();

                rsCheck.close();

            }

            String updateLoan =
                    "UPDATE LoanEquipment "
                  + "SET loanStatus=? "
                  + "WHERE loanID=?";

            PreparedStatement psUpdate =
                    conn.prepareStatement(
                            updateLoan);

            psUpdate.setString(
                    1,
                    action);

            psUpdate.setInt(
                    2,
                    loanID);

            psUpdate.executeUpdate();

            String insertApproval =
                    "INSERT INTO LoanApproval "
                  + "(loanID, adminID, approvalStatus, remarks) "
                  + "VALUES (?, ?, ?, ?)";

            PreparedStatement psApproval =
                    conn.prepareStatement(
                            insertApproval);

            psApproval.setInt(
                    1,
                    loanID);

            psApproval.setString(
                    2,
                    adminID);

            psApproval.setString(
                    3,
                    action);

            psApproval.setString(
                    4,
                    "-");

            psApproval.executeUpdate();

            psApproval.close();
            psUpdate.close();
            psLoan.close();
            rs.close();
            conn.close();

            response.sendRedirect(
                    "AdminLoanListServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Loan Approval Error : "
                    + e.getMessage());

        }

    }

}
