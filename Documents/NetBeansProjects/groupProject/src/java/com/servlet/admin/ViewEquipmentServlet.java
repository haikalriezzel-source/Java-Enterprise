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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if (keyword != null && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT * FROM Equipment "
                      + "WHERE LOWER(equipmentName) LIKE ? "
                      + "OR LOWER(brandModel) LIKE ? "
                      + "OR LOWER(equipmentStatus) LIKE ?";

                ps = conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT * FROM Equipment";

                ps = conn.prepareStatement(sql);
            }

            rs = ps.executeQuery();

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

                equipment.setQuantity(
                        rs.getInt("quantity"));

                equipment.setEquipmentStatus(
                        rs.getString("equipmentStatus"));

                equipment.setEquipmentImage(
                        rs.getString("equipmentImage"));

                equipmentList.add(equipment);
            }

            request.setAttribute(
                    "equipmentList",
                    equipmentList);

            request.setAttribute(
                    "keyword",
                    keyword);

            request.getRequestDispatcher(
                    "/admin/manageEquipment.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error : " + e.getMessage());

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