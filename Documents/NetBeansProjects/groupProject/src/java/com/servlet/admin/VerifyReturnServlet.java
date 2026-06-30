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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int returnID =
                    Integer.parseInt(
                            request.getParameter("returnID"));

            String condition =
                    request.getParameter("equipmentCondition");

            Connection conn =
                    DBConnection.getConnection();

            String getReturn =
                    "SELECT loanID, equipmentID, returnQuantity "
                  + "FROM ReturnRecord "
                  + "WHERE returnID=?";

            PreparedStatement psReturn =
                    conn.prepareStatement(getReturn);

            psReturn.setInt(1, returnID);

            ResultSet rs =
                    psReturn.executeQuery();

            int loanID = 0;
            int equipmentID = 0;
            int returnQuantity = 0;

            if (rs.next()) {

                loanID =
                        rs.getInt("loanID");

                equipmentID =
                        rs.getInt("equipmentID");

                returnQuantity =
                        rs.getInt("returnQuantity");
            }

            // GOOD
            if ("Good".equals(condition)) {

                PreparedStatement ps =
                        conn.prepareStatement(

                        "UPDATE Equipment "
                      + "SET availableQuantity = availableQuantity + ? "
                      + "WHERE equipmentID=?");

                ps.setInt(1, returnQuantity);
                ps.setInt(2, equipmentID);

                ps.executeUpdate();

                ps.close();
            }

            // MINOR DAMAGE
            else if ("Minor Damage".equals(condition)) {

                PreparedStatement ps =
                        conn.prepareStatement(

                        "UPDATE Equipment "
                      + "SET maintenanceQuantity = maintenanceQuantity + ? "
                      + "WHERE equipmentID=?");

                ps.setInt(1, returnQuantity);
                ps.setInt(2, equipmentID);

                ps.executeUpdate();

                ps.close();

            }

            // MAJOR DAMAGE
            else if ("Major Damage".equals(condition)) {

                PreparedStatement ps =
                        conn.prepareStatement(

                        "UPDATE Equipment "
                      + "SET damagedQuantity = damagedQuantity + ? "
                      + "WHERE equipmentID=?");

                ps.setInt(1, returnQuantity);
                ps.setInt(2, equipmentID);

                ps.executeUpdate();

                ps.close();

            }

            // LOST
            else if ("Lost".equals(condition)) {

                PreparedStatement ps =
                        conn.prepareStatement(

                        "UPDATE Equipment "
                      + "SET totalQuantity = totalQuantity - ? "
                      + "WHERE equipmentID=?");

                ps.setInt(1, returnQuantity);
                ps.setInt(2, equipmentID);

                ps.executeUpdate();

                ps.close();

            }

            PreparedStatement psReturnUpdate =
                    conn.prepareStatement(

                    "UPDATE ReturnRecord "
                  + "SET returnStatus='Verified', "
                  + "equipmentCondition=? "
                  + "WHERE returnID=?");

            psReturnUpdate.setString(
                    1,
                    condition);

            psReturnUpdate.setInt(
                    2,
                    returnID);

            psReturnUpdate.executeUpdate();

            PreparedStatement psLoan =
                    conn.prepareStatement(

                    "UPDATE LoanEquipment "
                  + "SET loanStatus='Verified' "
                  + "WHERE loanID=?");

            psLoan.setInt(
                    1,
                    loanID);

            psLoan.executeUpdate();

            psLoan.close();
            psReturnUpdate.close();
            psReturn.close();
            rs.close();
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
