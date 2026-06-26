<%-- 
    Document   : editAdminProfile
    Created on : Jun 25, 2026, 12:45:16 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bean.adminBean"%>

<%
adminBean admin =
        (adminBean)
        request.getAttribute("admin");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Admin Profile</title>

</head>

<body>

<h1>Edit Admin Profile</h1>

<form action="<%= request.getContextPath() %>/UpdateAdminProfileServlet"
      method="post">

    <p>Admin ID</p>

    <input type="text"
           name="adminID"
           value="<%= admin.getAdminID() %>"
           readonly>

    <br><br>

    <p>Admin Name</p>

    <input type="text"
           name="adminName"
           value="<%= admin.getAdminName() %>"
           required>

    <br><br>

    <p>Password</p>

    <input type="password"
           name="password"
           value="<%= admin.getPassword() %>"
           required>

    <br><br>

    <p>Phone Number</p>

    <input type="text"
           name="adminPhone"
           value="<%= admin.getAdminPhone() %>"
           required>

    <br><br>

    <p>Date of Birth</p>

    <input type="date"
           name="adminDOB"
           value="<%= admin.getAdminDOB() %>"
           required>

    <br><br>

    <button type="submit">

        Update Profile

    </button>

</form>

<br><br>

<a href="<%= request.getContextPath() %>/dashboardAdmin">

    Back To Dashboard

</a>

</body>

</html>
