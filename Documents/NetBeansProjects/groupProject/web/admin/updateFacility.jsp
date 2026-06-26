<%-- 
    Document   : updateFacility
    Created on : Jun 15, 2026, 4:40:16 PM
    Author     : haikalriez
--%>

<%@page import="java.sql.*"%>
<%@page import="util.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int facilityID =
            Integer.parseInt(
                    request.getParameter("id"));

    Connection conn =
            DBConnection.getConnection();

    String sql =
            "SELECT * FROM Facility "
            + "WHERE facilityID=?";

    PreparedStatement ps =
            conn.prepareStatement(sql);

    ps.setInt(1, facilityID);

    ResultSet rs =
            ps.executeQuery();

    rs.next();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Facility</title>
</head>

<body>

    <h1>Update Facility</h1>

    <form action="<%= request.getContextPath() %>/UpdateFacilityServlet"
          method="post">

        <input type="hidden"
               name="facilityID"
               value="<%= rs.getInt("facilityID") %>">

        <p>Facility Name</p>

        <input type="text"
               name="facilityName"
               value="<%= rs.getString("facilityName") %>"
               required>

        <br><br>

        <p>Location</p>

        <input type="text"
               name="location"
               value="<%= rs.getString("location") %>">

        <br><br>

        <p>Capacity</p>

        <input type="number"
               name="capacity"
               value="<%= rs.getInt("capacity") %>"
               required>

        <br><br>

        <p>Open Time</p>

        <input type="time"
               name="openTime"
               value="<%= rs.getTime("openTime").toString().substring(0,5) %>"
               required>

        <br><br>

        <p>Close Time</p>

        <input type="time"
               name="closeTime"
               value="<%= rs.getTime("closeTime").toString().substring(0,5) %>"
               required>

        <br><br>

        <p>Status</p>

        <select name="facilityStatus">

            <option value="Available"
                <%= rs.getString("facilityStatus").equals("Available")
                ? "selected" : "" %>>
                Available
            </option>

            <option value="Unavailable"
                <%= rs.getString("facilityStatus").equals("Unavailable")
                ? "selected" : "" %>>
                Unavailable
            </option>

        </select>

        <br><br>

        <p>Facility Image</p>

        <input type="text"
               name="facilityImage"
               value="<%= rs.getString("facilityImage") %>">

        <br><br>

        <button type="submit">
            Update Facility
        </button>

    </form>

    <br>

    <a href="<%= request.getContextPath() %>/ViewFacilityServlet">
        Back
    </a>

</body>
</html>

<%
    conn.close();
%>
