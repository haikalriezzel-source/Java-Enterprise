<%-- 
    Document   : updateEquipment
    Created on : Jun 13, 2026, 8:47:48 PM
    Author     : haikalriez
--%>

<%@page import="java.sql.*"%>
<%@page import="util.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int equipmentID =
            Integer.parseInt(
                    request.getParameter("id"));

    Connection conn =
            DBConnection.getConnection();

    String sql =
            "SELECT * FROM Equipment "
          + "WHERE equipmentID=?";

    PreparedStatement ps =
            conn.prepareStatement(sql);

    ps.setInt(1, equipmentID);

    ResultSet rs =
            ps.executeQuery();

    rs.next();
%>

<!DOCTYPE html>

<html>

<head>
    <title>Update Equipment</title>
</head>

<body>

<h1>Update Equipment</h1>

<form action="<%= request.getContextPath() %>/UpdateEquipmentServlet"
      method="post">

    <input type="hidden"
           name="equipmentID"
           value="<%= rs.getInt("equipmentID") %>">

    <p>Equipment Name</p>

    <input type="text"
           name="equipmentName"
           value="<%= rs.getString("equipmentName") %>"
           required>

    <br><br>

    <p>Brand / Model</p>

    <input type="text"
           name="brandModel"
           value="<%= rs.getString("brandModel") %>">

    <br><br>

    <p>Quantity</p>

    <input type="number"
           name="quantity"
           value="<%= rs.getInt("quantity") %>"
           required>

    <br><br>

    <p>Equipment Status</p>

    <select name="equipmentStatus">

        <option value="Available"
            <%= "Available".equals(
                    rs.getString("equipmentStatus"))
                    ? "selected"
                    : "" %>>
            Available
        </option>

        <option value="Damaged"
            <%= "Damaged".equals(
                    rs.getString("equipmentStatus"))
                    ? "selected"
                    : "" %>>
            Damaged
        </option>

        <option value="In-Maintenance"
            <%= "In-Maintenance".equals(
                    rs.getString("equipmentStatus"))
                    ? "selected"
                    : "" %>>
            In-Maintenance
        </option>

    </select>

    <br><br>

    <p>Equipment Image</p>

    <input type="text"
           name="equipmentImage"
           value="<%= rs.getString("equipmentImage") %>">

    <br><br>

    <button type="submit">
        Update Equipment
    </button>

</form>

<br>

<a href="<%= request.getContextPath() %>/ViewEquipmentServlet">
    Back
</a>

</body>

</html>

<%
    conn.close();
%>