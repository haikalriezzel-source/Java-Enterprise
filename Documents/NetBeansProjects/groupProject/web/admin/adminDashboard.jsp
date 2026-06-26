<%-- 
    Document   : adminDashboard
    Created on : Jun 11, 2026, 11:46:51 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
    <title>Admin Dashboard</title>
</head>

<body>

    <h1>Admin Dashboard</h1>

    <h3>
        Welcome,
        <%= session.getAttribute("adminName") %>
    </h3>

    <a href="<%= request.getContextPath() %>/EditAdminProfileServlet">
        Edit Profile
    </a>

<br><br>
    
    <hr>

    <h2>System Statistics</h2>

    <table border="1">

        <tr>
            <th>Statistic</th>
            <th>Value</th>
        </tr>

        <tr>
            <td>Total Students</td>
            <td><%= request.getAttribute("totalStudents") %></td>
        </tr>

        <tr>
            <td>Total Facilities</td>
            <td><%= request.getAttribute("totalFacilities") %></td>
        </tr>

        <tr>
            <td>Total Equipment</td>
            <td><%= request.getAttribute("totalEquipment") %></td>
        </tr>

        <tr>
            <td>Pending Facility Bookings</td>
            <td><%= request.getAttribute("pendingBookings") %></td>
        </tr>

        <tr>
            <td>Pending Equipment Loans</td>
            <td><%= request.getAttribute("pendingLoans") %></td>
        </tr>

        <tr>
            <td>Pending Equipment Returns</td>
            <td><%= request.getAttribute("pendingReturns") %></td>
        </tr>

    </table>

    <br>

    <hr>

    <h2>Peak Usage Report</h2>

    <table border="1">

        <tr>
            <th>Description</th>
            <th>Value</th>
        </tr>

        <tr>
            <td>Most Popular Facility</td>
            <td>
                <%= request.getAttribute("facilityName") %>
            </td>
        </tr>

        <tr>
            <td>Peak Usage Hour</td>
            <td>
                <%= request.getAttribute("peakHour") %>
            </td>
        </tr>

        <tr>
            <td>Total Approved Bookings</td>
            <td>
                <%= request.getAttribute("totalApprovedBookings") %>
            </td>
        </tr>

    </table>

    <br>

    <hr>

    <h2>Inventory Health Report</h2>

    <table border="1">

        <tr>
            <th>Equipment Status</th>
            <th>Total</th>
        </tr>

        <tr>
            <td>Available</td>
            <td>
                <%= request.getAttribute("availableEquipment") %>
            </td>
        </tr>

        <tr>
            <td>Damaged</td>
            <td>
                <%= request.getAttribute("damagedEquipment") %>
            </td>
        </tr>

        <tr>
            <td>In-Maintenance</td>
            <td>
                <%= request.getAttribute("maintenanceEquipment") %>
            </td>
        </tr>

    </table>

    <br>

    <hr>

    <h2>Low Stock Equipment Report</h2>

    <table border="1">

        <tr>
            <th>Description</th>
            <th>Value</th>
        </tr>

        <tr>
            <td>Equipment With Quantity ≤ 3</td>
            <td>
                <%= request.getAttribute("lowStockCount") %>
            </td>
        </tr>

    </table>

    <br>

    <hr>

    <h3>Menu</h3>

    <a href="<%= request.getContextPath() %>/ViewFacilityServlet">
        Manage Facility
    </a>

    <br><br>

    <a href="<%= request.getContextPath() %>/ViewEquipmentServlet">
        Manage Equipment
    </a>

    <br><br>
    
    <a href="<%= request.getContextPath() %>/ViewStudentServlet">
        Manage Students
    </a>

    <br><br>

    <a href="<%= request.getContextPath() %>/AdminFacilityListServlet">
        Approve Facility Booking
    </a>

    <br><br>

    <a href="<%= request.getContextPath() %>/AdminLoanListServlet">
        Approve Equipment Loan
    </a>

    <br><br>

    <a href="<%= request.getContextPath() %>/AdminReturnListServlet">
        Verify Equipment Return
    </a>

    <br><br>

    <a href="<%= request.getContextPath() %>/LogoutServlet"
       onclick="return confirm('Are you sure you want to logout?');">

        Logout

    </a>

</body>

</html>
