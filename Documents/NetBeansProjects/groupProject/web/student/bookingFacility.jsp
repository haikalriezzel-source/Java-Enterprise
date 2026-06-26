<%-- 
    Document   : bookingFacility
    Created on : Jun 23, 2026, 12:56:51 AM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>

<%
String facilityID = request.getParameter("facilityID");
%>

<!DOCTYPE html>

<html>

<head>
    <title>Book Facility</title>
</head>

<body>

<h1>Book Facility</h1>

<form action="<%=request.getContextPath()%>/BookFacilityServlet"
      method="post">

    <input type="hidden"
           name="facilityID"
           value="<%=facilityID%>">

    <p>
        Booking Date:

        <input type="date"
               name="bookingDate"
               min="<%= LocalDate.now() %>"
               required>
    </p>

    <p>
        Start Time:

        <input type="time"
               name="startTime"
               required>
    </p>

    <p>
        End Time:

        <input type="time"
               name="endTime"
               required>
    </p>

    <p>
        Purpose:

        <input type="text"
               name="purpose"
               required>
    </p>

    <p>
        Number Of Participants:

        <input type="number"
               name="participants"
               min="1"
               required>
    </p>

    <input type="submit"
           value="Submit Booking">

</form>

<script>

document.querySelector(
        'input[name="startTime"]')
        .addEventListener(
        'change',
        function () {

    document.querySelector(
            'input[name="endTime"]')
            .min = this.value;

});

</script>

</body>

    <a href="<%= request.getContextPath() %>/facilityList">
    Back To Facility List
    </a>

</html>