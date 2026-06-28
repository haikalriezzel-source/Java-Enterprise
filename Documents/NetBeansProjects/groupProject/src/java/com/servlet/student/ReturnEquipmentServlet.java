/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import util.DBConnection;

import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/ReturnEquipmentServlet")
public class ReturnEquipmentServlet extends HttpServlet {

@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {

    try {

        int loanID =
                Integer.parseInt(
                        request.getParameter(
                                "loanID"));

        Connection conn =
                DBConnection.getConnection();

        String getLoan =
                "SELECT equipmentID, "
              + "loanQuantity "
              + "FROM LoanEquipment "
              + "WHERE loanID=?";

        PreparedStatement psLoan =
                conn.prepareStatement(
                        getLoan);

        psLoan.setInt(
                1,
                loanID);

        ResultSet rs =
                psLoan.executeQuery();

        int equipmentID = 0;
        int quantity = 0;

        if(rs.next()) {

            equipmentID =
                    rs.getInt(
                            "equipmentID");

            quantity =
                    rs.getInt(
                            "loanQuantity");
        }

        String insertReturn =
                "INSERT INTO ReturnRecord "
              + "(loanID, equipmentID, "
              + "returnQuantity, "
              + "returnDate, "
              + "equipmentCondition, "
              + "returnStatus) "
              + "VALUES "
              + "(?, ?, ?, "
              + "CURRENT_DATE, "
              + "?, ?)";

        PreparedStatement psReturn =
                conn.prepareStatement(
                        insertReturn);

        psReturn.setInt(
                1,
                loanID);

        psReturn.setInt(
                2,
                equipmentID);

        psReturn.setInt(
                3,
                quantity);

        psReturn.setString(
                4,
                "Good");

        psReturn.setString(
                5,
                "Pending");

        psReturn.executeUpdate();
        
        String updateLoan =
        "UPDATE LoanEquipment "
      + "SET loanStatus=? "
      + "WHERE loanID=?";

        PreparedStatement psUpdate = conn.prepareStatement(updateLoan);

        psUpdate.setString(1, "Returning");

        psUpdate.setInt(2,loanID);

        psUpdate.executeUpdate();

        psUpdate.close();

        HttpSession session = request.getSession();
        session.setAttribute("scrollToHistory", true);

        response.sendRedirect(
            request.getContextPath()
            + "/MyLoanServlet");

    } catch(Exception e) {

        e.printStackTrace();
    }
}

}
