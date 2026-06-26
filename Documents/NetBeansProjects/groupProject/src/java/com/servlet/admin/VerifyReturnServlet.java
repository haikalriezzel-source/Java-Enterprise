/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.admin;
import util.DBConnection;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/VerifyReturnServlet")
public class VerifyReturnServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int returnID =
                    Integer.parseInt(
                            request.getParameter(
                                    "returnID"));

            String condition =
                    request.getParameter(
                            "equipmentCondition");

            Connection conn =
                    DBConnection.getConnection();

            String getReturn =
                    "SELECT loanID, "
                  + "equipmentID, "
                  + "returnQuantity "
                  + "FROM ReturnRecord "
                  + "WHERE returnID=?";

            PreparedStatement psReturn =
                    conn.prepareStatement(
                            getReturn);

            psReturn.setInt(
                    1,
                    returnID);

            ResultSet rs =
                    psReturn.executeQuery();

            int loanID = 0;
            int equipmentID = 0;
            int returnQuantity = 0;

            if (rs.next()) {

                loanID =
                        rs.getInt(
                                "loanID");

                equipmentID =
                        rs.getInt(
                                "equipmentID");

                returnQuantity =
                        rs.getInt(
                                "returnQuantity");
            }

            /*
             * GOOD / MINOR DAMAGE
             * Add stock back
             */
            if ("Good".equals(condition)
                    || "Minor Damage".equals(condition)) {

                String updateEquipment =
                        "UPDATE Equipment "
                      + "SET quantity = quantity + ? "
                      + "WHERE equipmentID=?";

                PreparedStatement psEquip =
                        conn.prepareStatement(
                                updateEquipment);

                psEquip.setInt(
                        1,
                        returnQuantity);

                psEquip.setInt(
                        2,
                        equipmentID);

                psEquip.executeUpdate();

                String updateStatus =
                        "UPDATE Equipment "
                      + "SET equipmentStatus='Available' "
                      + "WHERE equipmentID=?";

                PreparedStatement psStatus =
                        conn.prepareStatement(
                                updateStatus);

                psStatus.setInt(
                        1,
                        equipmentID);

                psStatus.executeUpdate();
            }

            /*
             * MAJOR DAMAGE
             */
            else if ("Major Damage".equals(condition)) {

                String updateStatus =
                        "UPDATE Equipment "
                      + "SET equipmentStatus='Damaged' "
                      + "WHERE equipmentID=?";

                PreparedStatement psStatus =
                        conn.prepareStatement(
                                updateStatus);

                psStatus.setInt(
                        1,
                        equipmentID);

                psStatus.executeUpdate();
            }

            /*
             * LOST
             */
            else if ("Lost".equals(condition)) {

                String updateStatus =
                        "UPDATE Equipment "
                      + "SET equipmentStatus='In-Maintenance' "
                      + "WHERE equipmentID=?";

                PreparedStatement psStatus =
                        conn.prepareStatement(
                                updateStatus);

                psStatus.setInt(
                        1,
                        equipmentID);

                psStatus.executeUpdate();
            }

            /*
             * Update Return Record
             */
            String updateReturn =
                    "UPDATE ReturnRecord "
                  + "SET returnStatus='Verified', "
                  + "equipmentCondition=? "
                  + "WHERE returnID=?";

            PreparedStatement psUpdateReturn =
                    conn.prepareStatement(
                            updateReturn);

            psUpdateReturn.setString(
                    1,
                    condition);

            psUpdateReturn.setInt(
                    2,
                    returnID);

            psUpdateReturn.executeUpdate();

            /*
             * Update Loan Status
             */
            String updateLoan =
                    "UPDATE LoanEquipment "
                  + "SET loanStatus='Verified' "
                  + "WHERE loanID=?";

            PreparedStatement psLoan =
                    conn.prepareStatement(
                            updateLoan);

            psLoan.setInt(
                    1,
                    loanID);

            psLoan.executeUpdate();

            conn.close();

            response.sendRedirect(
                    "AdminReturnListServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : "
                    + e.getMessage());
        }
    }
}
