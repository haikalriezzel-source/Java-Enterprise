package com.servlet.admin;

import com.bean.equipmentBean;
import util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewEquipmentServlet")
public class ViewEquipmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM Equipment "
                  + "ORDER BY equipmentName";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            ArrayList<equipmentBean> equipmentList =
                    new ArrayList<>();

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

            request.getRequestDispatcher(
                    "/admin/manageEquipment.jsp")
                    .forward(
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