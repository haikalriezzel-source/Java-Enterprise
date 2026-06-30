/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.student;
import com.bean.equipmentBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author haikalriez
 */
@WebServlet("/equipmentList")
public class ViewEquipmentServletStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<equipmentBean> equipmentList =
                new ArrayList<>();

        try {

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * "
                  + "FROM Equipment "
                  + "ORDER BY equipmentName";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                equipmentBean equipment =
                        new equipmentBean();

                equipment.setEquipmentID(
                        rs.getInt("equipmentID"));

                equipment.setEquipmentName(
                        rs.getString("equipmentName"));

                equipment.setBrandModel(
                        rs.getString("brandModel"));

                equipment.setTotalQuantity(
                        rs.getInt("totalQuantity"));

                equipment.setAvailableQuantity(
                        rs.getInt("availableQuantity"));

                equipment.setMaintenanceQuantity(
                        rs.getInt("maintenanceQuantity"));

                equipment.setDamagedQuantity(
                        rs.getInt("damagedQuantity"));

                equipment.setEquipmentImage(
                        rs.getString("equipmentImage"));

                equipmentList.add(equipment);

            }

            request.setAttribute(
                    "equipmentList",
                    equipmentList);

            conn.close();

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/student/equipmentList.jsp");

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
