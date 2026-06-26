<%-- 
    Document   : register
    Created on : Jun 10, 2026, 11:24:37 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
</head>

<body>

    <h2>Student Registration</h2>

    <%
    String error =
            request.getParameter("error");

    if(error != null){

        if(error.equals("empty")){
    %>

    <p style="color:red;">
        Please fill in all required fields.
    </p>

    <%
        } else if(error.equals("duplicate")){
    %>

    <p style="color:red;">
        Student ID already exists.
    </p>

    <%
        } else if(error.equals("date")){
    %>

    <p style="color:red;">
        Invalid date format.
    </p>

    <%
        } else if(error.equals("database")){
    %>

    <p style="color:red;">
        Database connection error.
    </p>

    <%
        } else if(error.equals("system")){
    %>

    <p style="color:red;">
        Unexpected system error occurred.
    </p>

    <%
        }
    }
    %>

    <form action="RegisterServlet"
          method="post">

        <p>
            Student ID:
            <input type="text"
                   name="studentID"
                   required>
        </p>

        <p>
            Full Name:
            <input type="text"
                   name="studentName"
                   required>
        </p>

        <p>
            Date of Birth:
            <input type="date"
                   name="studentDOB"
                   required>
        </p>

        <p>
            Programme:
            <input type="text"
                   name="programme"
                   required>
        </p>

        <p>
            Phone Number:
            <input type="text"
                   name="phoneNumber"
                   required>
        </p>

        <p>
            Password:
            <input type="password"
                   name="password"
                   required>
        </p>

        <input type="submit"
               value="Register">

    </form>

    <br>

    <a href="login.jsp">
        Back to Login
    </a>

</body>
</html>