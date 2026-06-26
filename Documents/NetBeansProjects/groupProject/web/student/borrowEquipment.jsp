<%-- 
    Document   : borrowEquipment
    Created on : Jun 24, 2026, 12:29:07 AM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>

<html>

<head>
    <title>Borrow Equipment</title>
</head>

<body>

    <h2>Borrow Equipment</h2>

    <%
    String error =
            request.getParameter("error");

    if(error != null){

        if(error.equals("pastdate")){
    %>

    <p style="color:red;">
        Borrow date cannot be earlier than today.
    </p>

    <%
        } else if(error.equals("returndate")){
    %>

    <p style="color:red;">
        Return date cannot be earlier than borrow date.
    </p>

    <%
        } else if(error.equals("quantity")){
    %>

    <p style="color:red;">
        Quantity must be greater than 0.
    </p>

    <%
        } else if(error.equals("stock")){
    %>

    <p style="color:red;">
        Requested quantity exceeds available stock.
    </p>

    <%
        } else if(error.equals("database")){
    %>

    <p style="color:red;">
        Database error occurred.
    </p>

    <%
        }
    }
    %>

    <form action="${pageContext.request.contextPath}/BorrowEquipmentServlet"
          method="post">

        <input type="hidden"
               name="equipmentID"
               value="${equipmentID}">

        <p>
            Quantity :

            <input type="number"
                   name="loanQuantity"
                   min="1"
                   required>
        </p>

        <p>
            Start Date :

            <input type="date"
                   name="startDate"
                   min="<%= LocalDate.now() %>"
                   required>
        </p>

        <p>
            End Date :

            <input type="date"
                   name="endDate"
                   min="<%= LocalDate.now() %>"
                   required>
        </p>

        <p>
            <input type="submit"
                   value="Submit Loan">
        </p>

    </form>
    
    <a href="<%= request.getContextPath() %>/equipmentList">
    Back To Equipment List
    </a>

</body>

</html>