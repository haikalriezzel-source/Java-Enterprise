package com.servlet.admin;

import com.bean.equipmentBean;
import util.DBConnection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/AddEquipmentServlet")
@MultipartConfig
public class AddEquipmentServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String equipmentName =
                    request.getParameter(
                            "equipmentName");

            String brandModel =
                    request.getParameter(
                            "brandModel");

            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "quantity"));

            String equipmentStatus =
                    request.getParameter(
                            "equipmentStatus");

            Part imagePart =
                    request.getPart(
                            "equipmentImage");

            String fileName =
                    imagePart.getSubmittedFileName();

            fileName =
                    fileName.replaceAll(
                            "\\s+",
                            "_");

            String uploadPath =
                    "C:\\Users\\haikalriez\\Documents\\NetBeansProjects\\groupProject\\web\\uploads";

            File uploadDir =
                    new File(uploadPath);

            if (!uploadDir.exists()) {

                uploadDir.mkdirs();
            }

            String filePath =
                    uploadPath
                    + File.separator
                    + fileName;

            System.out.println(
                    "Saving Image To: "
                    + filePath);

            InputStream input =
                    imagePart.getInputStream();

            Files.copy(
                    input,
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING);

            equipmentBean equipment =
                    new equipmentBean();

            equipment.setEquipmentName(
                    equipmentName);

            equipment.setBrandModel(
                    brandModel);

            equipment.setQuantity(
                    quantity);

            equipment.setEquipmentStatus(
                    equipmentStatus);

            equipment.setEquipmentImage(
                    fileName);

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO Equipment "
                  + "(equipmentName, "
                  + "brandModel, "
                  + "quantity, "
                  + "equipmentStatus, "
                  + "equipmentImage) "
                  + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(
                            sql);

            ps.setString(
                    1,
                    equipment.getEquipmentName());

            ps.setString(
                    2,
                    equipment.getBrandModel());

            ps.setInt(
                    3,
                    equipment.getQuantity());

            ps.setString(
                    4,
                    equipment.getEquipmentStatus());

            ps.setString(
                    5,
                    equipment.getEquipmentImage());

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    "admin/addEquipment.jsp?success=1");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Upload Error: "
                    + e.getMessage());
        }
    }
}