<%-- 
    Document   : editStudentProfile
    Created on : Jun 25, 2026, 1:01:48 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bean.student"%>

<%
student student =
        (student)
        request.getAttribute("student");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Edit Student Profile</title>

</head>

<body>

<h1>Edit Student Profile</h1>

<form action="<%= request.getContextPath() %>/UpdateStudentProfileServlet"
      method="post">

    <p>Student ID</p>

    <input type="text"
           name="studentID"
           value="<%= student.getStudentID() %>"
           readonly>

    <br><br>

    <p>Student Name</p>

    <input type="text"
           name="studentName"
           value="<%= student.getStudentName() %>"
           required>

    <br><br>

    <p>Password</p>

    <input type="password"
           id="password"
           name="password"
           value="<%= student.getPassword() %>"
           required>

    <button type="button"
            onclick="togglePassword()">

        👁

    </button>

    <br><br>

    <p>Date of Birth</p>

    <input type="date"
           name="studentDOB"
           value="<%= student.getStudentDOB() %>"
           required>

    <br><br>

    <p>Programme</p>

        <input type="text"
       value="<%= student.getProgramme() %>"
       readonly>

        <input type="hidden"
       name="programme"
       value="<%= student.getProgramme() %>">

    <br><br>

    <p>Phone Number</p>

    <input type="text"
           name="phoneNumber"
           value="<%= student.getPhoneNumber() %>"
           required>

    <br><br>

    <button type="submit">

        Update Profile

    </button>

</form>

<br><br>

<a href="<%= request.getContextPath() %>/studentDashboard">

    Back To Dashboard

</a>

<script>

function togglePassword() {

    var password =
            document.getElementById(
                    "password");

    if (password.type === "password") {

        password.type = "text";

    } else {

        password.type = "password";

    }

}

</script>

</body>

</html>