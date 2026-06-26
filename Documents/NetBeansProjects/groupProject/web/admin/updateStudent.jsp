<%-- 
    Document   : updateStudent
    Created on : Jun 25, 2026, 12:29:14 PM
    Author     : haikalriez
--%>

<%@page import="java.sql.*"%>
<%@page import="util.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
String studentID =
        request.getParameter("id");

Connection conn =
        DBConnection.getConnection();

String sql =
        "SELECT * FROM Student "
      + "WHERE studentID=?";

PreparedStatement ps =
        conn.prepareStatement(sql);

ps.setString(
        1,
        studentID);

ResultSet rs =
        ps.executeQuery();

rs.next();
%>

<!DOCTYPE html>

<html>

<head>

    <title>Update Student</title>

</head>

<body>

<h1>Update Student</h1>

<form action="<%= request.getContextPath() %>/UpdateStudentServlet"
      method="post">

    <input type="hidden"
           name="studentID"
           value="<%= rs.getString("studentID") %>">

    <p>Student Name</p>

    <input type="text"
           name="studentName"
           value="<%= rs.getString("studentName") %>"
           required>

    <br><br>

    <p>Date of Birth</p>

    <input type="date"
           name="studentDOB"
           value="<%= rs.getDate("studentDOB") %>"
           required>

    <br><br>

    <p>Programme</p>

    <input type="text"
           name="programme"
           value="<%= rs.getString("programme") %>"
           required>

    <br><br>

    <p>Phone Number</p>

    <input type="text"
           name="phoneNumber"
           value="<%= rs.getString("phoneNumber") %>"
           required>

    <br><br>

    <button type="submit">

        Update Student

    </button>

</form>

<br>

<a href="<%= request.getContextPath() %>/ViewStudentServlet">

    Back

</a>

</body>

</html>

<%
conn.close();
%>
