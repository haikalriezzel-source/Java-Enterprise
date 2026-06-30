package com.servlet.admin;

import util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dashboardAdmin")
public class dashboardAdmin extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Connection conn =
                    DBConnection.getConnection();

            // =========================
            // SYSTEM STATISTICS
            // =========================

            PreparedStatement ps1 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) FROM Student");

            ResultSet rs1 =
                    ps1.executeQuery();

            rs1.next();

            int totalStudents =
                    rs1.getInt(1);

            PreparedStatement ps2 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) FROM Facility");

            ResultSet rs2 =
                    ps2.executeQuery();

            rs2.next();

            int totalFacilities =
                    rs2.getInt(1);

            PreparedStatement ps3 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) FROM Equipment");

            ResultSet rs3 =
                    ps3.executeQuery();

            rs3.next();

            int totalEquipment =
                    rs3.getInt(1);

            PreparedStatement ps4 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) "
                          + "FROM FacilityBooking "
                          + "WHERE bookingStatus='Pending'");

            ResultSet rs4 =
                    ps4.executeQuery();

            rs4.next();

            int pendingBookings =
                    rs4.getInt(1);

            PreparedStatement ps5 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) "
                          + "FROM LoanEquipment "
                          + "WHERE loanStatus='Pending'");

            ResultSet rs5 =
                    ps5.executeQuery();

            rs5.next();

            int pendingLoans =
                    rs5.getInt(1);

            PreparedStatement ps6 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) "
                          + "FROM ReturnRecord "
                          + "WHERE returnStatus='Pending'");

            ResultSet rs6 =
                    ps6.executeQuery();

            rs6.next();

            int pendingReturns =
                    rs6.getInt(1);

            // =========================
            // PEAK USAGE REPORT
            // =========================

            String facilityName =
                    "No Data";

            String peakHour =
                    "-";

            int totalApprovedBookings =
                    0;

            PreparedStatement ps7 =
                    conn.prepareStatement(

                            "SELECT "
                          + "F.facilityName, "
                          + "FB.startTime, "
                          + "COUNT(*) AS totalBooking "
                          + "FROM FacilityBooking FB "
                          + "JOIN Facility F "
                          + "ON FB.facilityID = F.facilityID "
                          + "WHERE FB.bookingStatus='Approved' "
                          + "GROUP BY "
                          + "F.facilityName, "
                          + "FB.startTime "
                          + "ORDER BY totalBooking DESC"

                    );

            ResultSet rs7 =
                    ps7.executeQuery();

            if (rs7.next()) {

                facilityName =
                        rs7.getString(
                                "facilityName");

                peakHour =
                        rs7.getTime(
                                "startTime")
                                .toString();

                totalApprovedBookings =
                        rs7.getInt(
                                "totalBooking");
            }

            // =========================
            // INVENTORY HEALTH REPORT
            // =========================

            PreparedStatement ps8 =
        conn.prepareStatement(

        "SELECT "
      + "COALESCE(SUM(availableQuantity),0), "
      + "COALESCE(SUM(maintenanceQuantity),0), "
      + "COALESCE(SUM(damagedQuantity),0) "
      + "FROM Equipment");

ResultSet rs8 =
        ps8.executeQuery();

int availableEquipment = 0;
int maintenanceEquipment = 0;
int damagedEquipment = 0;

if(rs8.next()){

    availableEquipment =
            rs8.getInt(1);

    maintenanceEquipment =
            rs8.getInt(2);

    damagedEquipment =
            rs8.getInt(3);

}

            // =========================
            // LOW STOCK REPORT
            // =========================

            PreparedStatement ps11 =
                    conn.prepareStatement(
                            "SELECT COUNT(*) "
                          + "FROM Equipment "
                          + "WHERE availableQuantity <= 3");

            ResultSet rs11 =
                    ps11.executeQuery();

            rs11.next();

            int lowStockCount =
                    rs11.getInt(1);

            // =========================
            // SEND TO JSP
            // =========================

            request.setAttribute(
                    "totalStudents",
                    totalStudents);

            request.setAttribute(
                    "totalFacilities",
                    totalFacilities);

            request.setAttribute(
                    "totalEquipment",
                    totalEquipment);

            request.setAttribute(
                    "pendingBookings",
                    pendingBookings);

            request.setAttribute(
                    "pendingLoans",
                    pendingLoans);

            request.setAttribute(
                    "pendingReturns",
                    pendingReturns);

            request.setAttribute(
                    "facilityName",
                    facilityName);

            request.setAttribute(
                    "peakHour",
                    peakHour);

            request.setAttribute(
                    "totalApprovedBookings",
                    totalApprovedBookings);

            request.setAttribute(
                    "availableEquipment",
                    availableEquipment);

            request.setAttribute(
                    "damagedEquipment",
                    damagedEquipment);

            request.setAttribute(
                    "maintenanceEquipment",
                    maintenanceEquipment);

            request.setAttribute(
                    "lowStockCount",
                    lowStockCount);

            conn.close();

            request.getRequestDispatcher(
                    "/admin/adminDashboard.jsp")
                    .forward(
                            request,
                            response);

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Dashboard Error : "
                    + e.getMessage());
        }
    }
}