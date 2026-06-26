/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author haikalriez
 */
@WebServlet("/BorrowEquipmentPageServlet")
public class BorrowEquipmentPageServlet
        extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String equipmentID =
                request.getParameter(
                        "equipmentID");

        request.setAttribute(
                "equipmentID",
                equipmentID);

        RequestDispatcher rd =
                request.getRequestDispatcher(
                        "student/borrowEquipment.jsp");

        rd.forward(
                request,
                response);
    }
}

