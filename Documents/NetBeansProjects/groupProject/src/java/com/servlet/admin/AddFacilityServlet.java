package com.servlet.admin;

import com.bean.facilityBean;
import util.DBConnection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddFacilityServlet")
@MultipartConfig
public class AddFacilityServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String facilityName = request.getParameter("facilityName");

        String location = request.getParameter("location");

        int capacity = Integer.parseInt(request.getParameter("capacity"));

        String openTime = request.getParameter("openTime");

        String closeTime = request.getParameter("closeTime");
        
        String facilityStatus = request.getParameter("facilityStatus");

            Part imagePart =
                    request.getPart(
                            "facilityImage");

            String fileName =
                    imagePart.getSubmittedFileName();

            fileName =
                    fileName.replaceAll(
                            "\\s+",
                            "_");

            String uploadPath =
                    "C:\\Users\\saf\\Documents\\NetBeansProjectss\\Java-Enterprise\\Documents\\NetBeansProjects\\groupProject\\web\\uploads";

            File uploadDir =
                    new File(uploadPath);

            if (!uploadDir.exists()) {

                uploadDir.mkdirs();
            }

            String filePath =
                    uploadPath
                    + File.separator
                    + fileName;

            InputStream input =
                    imagePart.getInputStream();

            Files.copy(
                    input,
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING
            );
            
            facilityBean facility = new facilityBean();

            facility.setFacilityName(facilityName);
            facility.setLocation(location);
            facility.setCapacity(capacity);
            facility.setOpenTime(openTime);
            facility.setCloseTime(closeTime);
            facility.setFacilityStatus(facilityStatus);
            

            facility.setFacilityImage(
                    fileName);

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO Facility "
                    + "(facilityName, location, capacity, "
                    + "openTime, closeTime, facilityStatus, facilityImage) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    facility.getFacilityName());

            ps.setString(
                    2,
                    facility.getLocation());

            ps.setInt(
                    3,
                    facility.getCapacity());

            ps.setTime(
                    4,
                    java.sql.Time.valueOf(
                            facility.getOpenTime()
                            + ":00"));

            ps.setTime(
                    5,
                    java.sql.Time.valueOf(
                            facility.getCloseTime()
                            + ":00"));

            ps.setString(
                    6,
                    facility.getFacilityStatus());

            ps.setString(
                    7,
                    facility.getFacilityImage());

            ps.executeUpdate();

            conn.close();

            response.sendRedirect(
                    "admin/addFacility.jsp?success=1");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Add Facility Error: "
                    + e.getMessage());

        }
    }
}