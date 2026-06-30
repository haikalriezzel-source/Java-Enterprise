<%-- 
    Document   : repairEquipment
    Created on : Jun 28, 2026, 6:27:55 PM
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
        "SELECT * FROM Equipment WHERE equipmentID=?";

PreparedStatement ps =
        conn.prepareStatement(sql);

ps.setInt(1, equipmentID);

ResultSet rs =
        ps.executeQuery();

rs.next();
%>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<title>Repair Equipment</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      rel="stylesheet">

<link href="<%=request.getContextPath()%>/assets/css/admin.css"
      rel="stylesheet">

</head>

<body class="admin-ui-body">

<jsp:include page="layout/topbar.jsp"/>

<main class="admin-ui-main">

<div class="admin-page-header">

<div>

<h1 class="admin-page-title">

Repair Equipment

</h1>

<p class="admin-page-subtitle">

Move equipment from Maintenance back to Available.

</p>

</div>

<a href="<%=request.getContextPath()%>/ViewEquipmentServlet"
   class="btn btn-admin-soft">

<i class="fa-solid fa-arrow-left me-2"></i>

Back

</a>

</div>

<div class="row justify-content-center">

<div class="col-lg-6">

<div class="admin-card card">

<div class="card-header">

<i class="fa-solid fa-screwdriver-wrench me-2"></i>

Repair Equipment

</div>

<div class="card-body">

<form action="<%=request.getContextPath()%>/RepairEquipmentServlet"
      method="post">

<input type="hidden"
       name="equipmentID"
       value="<%=rs.getInt("equipmentID")%>">

<div class="mb-3">

<label class="form-label">

Equipment

</label>

<input class="form-control"
       type="text"
       value="<%=rs.getString("equipmentName")%>"
       readonly>

</div>

<div class="mb-3">

<label class="form-label">

Maintenance Quantity

</label>

<input class="form-control"
       type="text"
       value="<%=rs.getInt("maintenanceQuantity")%>"
       readonly>

</div>

<div class="mb-3">

<label class="form-label">

Repair Quantity

</label>

<input class="form-control"
       type="number"
       name="repairQuantity"
       min="1"
       max="<%=rs.getInt("maintenanceQuantity")%>"
       required>

</div>

<div class="d-flex justify-content-end gap-2">

<a class="btn btn-outline-secondary"
   href="<%=request.getContextPath()%>/ViewEquipmentServlet">

Cancel

</a>

<button class="btn btn-warning"
        type="submit">

<i class="fa-solid fa-screwdriver-wrench me-2"></i>

Repair Equipment

</button>

</div>

</form>

</div>

</div>

</div>

</div>

</main>

</body>

</html>

<%
conn.close();
%>
