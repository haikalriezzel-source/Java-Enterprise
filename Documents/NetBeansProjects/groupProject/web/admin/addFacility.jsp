<%-- 
    Document   : addFacility
    Created on : Jun 15, 2026, 3:27:06 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>Add Facility</title>
</head>

<body>

    <h1>Add Facility</h1>

    <%
        if(request.getParameter("success") != null){
    %>

        <p>
            Facility Added Successfully.
        </p>

    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/AddFacilityServlet"
      method="post"
      enctype="multipart/form-data">

        <p>Facility Name</p>
        <input type="text"
               name="facilityName"
               required>

        <br><br>

        <p>Location</p>
        <input type="text"
               name="location">

        <br><br>

        <p>Capacity</p>
        <input type="number"
               name="capacity"
               required>

        <br><br>

        <p>Open Time</p>
        <input type="time"
               name="openTime"
               required>

        <br><br>

        <p>Close Time</p>
        <input type="time"
               name="closeTime"
               required>

        <br><br>

        <p>Status</p>

        <select name="facilityStatus">

            <option value="Available">
                Available
            </option>

            <option value="Unavailable">
                Unavailable
            </option>

        </select>

        <br><br>

        <p>Facility Image</p>
            <input type="file" name="facilityImage" accept="image/*" required>

        <br><br>

        <button type="submit">
            Add Facility
        </button>

    </form>

    <br>

    <a href="<%= request.getContextPath() %>/dashboardAdmin">
        Back to Dashboard
    </a>

</body>
</html>
