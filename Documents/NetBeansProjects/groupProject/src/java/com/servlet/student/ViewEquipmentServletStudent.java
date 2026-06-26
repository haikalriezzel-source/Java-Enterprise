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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<equipmentBean> equipmentList =
                new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn =
                    DBConnection.getConnection();

            String keyword =
                    request.getParameter("keyword");

            String sql;

            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                sql =
                        "SELECT * FROM Equipment "
                      + "WHERE equipmentStatus='Available' "
                      + "AND (LOWER(equipmentName) LIKE ? "
                      + "OR LOWER(brandModel) LIKE ? "
                      + "OR LOWER(equipmentStatus) LIKE ?) "
                      + "ORDER BY equipmentID";

                ps =
                        conn.prepareStatement(sql);

                String search =
                        "%" + keyword.toLowerCase() + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

            } else {

                sql =
                        "SELECT * FROM Equipment "
                      + "WHERE equipmentStatus='Available' "
                      + "ORDER BY equipmentID";

                ps =
                        conn.prepareStatement(sql);
            }

            rs =
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
