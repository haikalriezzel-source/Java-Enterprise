<%-- 
    Document   : login
    Created on : Jun 10, 2026, 10:40:22 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>
            Sports Equipment and Facility Rental
        </title>
    </head>

    <body>

        <h1>Login</h1>

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
            } else if(error.equals("invalid")){
        %>

        <p style="color:red;">
            Invalid User ID or Password.
        </p>

        <%
            } else if(error.equals("wrongrole")){
        %>

        <p style="color:red;">
            Incorrect user type selected.
            Please select the correct role.
        </p>

        <%
            } else if(error.equals("driver")){
        %>

        <p style="color:red;">
            Database driver not found.
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

        <form action="loginServlet"
              method="post">

            <label>User Type</label>

            <select name="userType"
                    required>

                <option value="">
                    -- Select User Type --
                </option>

                <option value="student">
                    Student
                </option>

                <option value="admin">
                    Admin
                </option>

            </select>

            <p>
                User ID
                <input type="text"
                       name="userID">
            </p>

            <p>
                Password
                <input type="password"
                       name="password">
            </p>

            <button type="submit">
                Login
            </button>

        </form>

        <br>

        <a href="register.jsp">
            Register Here
        </a>

    </body>
</html>