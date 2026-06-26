<%-- 
    Document   : studentDashboard
    Created on : Jun 22, 2026, 11:07:15 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
</head>
<body>

    <h1>Student Dashboard</h1>

    <hr>

    <h2>Welcome, ${sessionScope.studentName}</h2>
    
    <a href="<%= request.getContextPath() %>/EditStudentProfileServlet">
        Edit Profile
    </a>

<br><br>

    <table border="1">
        <tr>
            <td>Student ID</td>
            <td>${studentID}</td>
        </tr>

        <tr>
            <td>Programme</td>
            <td>${programme}</td>
        </tr>

        <tr>
            <td>Phone Number</td>
            <td>${phoneNumber}</td>
        </tr>
    </table>

    <br>

    <h3>Dashboard Statistics</h3>

    <table border="1">
        <tr>
            <th>Total Bookings</th>
            <th>Active Loans</th>
            <th>Pending Requests</th>
            <th>Returned Items</th>
        </tr>

        <tr>
            <td>${totalBookings}</td>
            <td>${activeLoans}</td>
            <td>${pendingRequests}</td>
            <td>${returnedItems}</td>
        </tr>
    </table>

    <br>

    <h3>Quick Menu</h3>

    <ul>
        <li><a href="<%= request.getContextPath() %>/facilityList">View Facilities</a></li>
        <li><a href="<%= request.getContextPath() %>/equipmentList">View Equipment</a></li>
        <li><a href="${pageContext.request.contextPath}/MyFacilityBookingServlet">My Facility Bookings</a></li>
        <li><a href="${pageContext.request.contextPath}/MyLoanServlet">My Equipment Loans</a>
</li>
        <li><a href="<%= request.getContextPath() %>/LogoutServlet"onclick="return confirm('Are you sure you want to logout?');">
            Logout
        </a></li>
    </ul>

</body>
</html>
