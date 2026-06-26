<%-- 
    Document   : manageEquipment
    Created on : Jun 13, 2026, 7:13:12 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.equipmentBean"%>

<!DOCTYPE html>

<html>

<head>
    <title>Manage Equipment</title>
</head>

<body>

<h1>Equipment List</h1>

<form action="<%=request.getContextPath()%>/ViewEquipmentServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search equipment..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/ViewEquipmentServlet">
        Reset
    </a>

</form>

<br>

<a href="<%= request.getContextPath() %>/admin/addEquipment.jsp">
    Add New Equipment
</a>

<br><br>

<%
ArrayList<equipmentBean> equipmentList =
        (ArrayList<equipmentBean>)
        request.getAttribute("equipmentList");
%>

<table border="1">

<tr>

    <th>Equipment ID</th>
    <th>Equipment Name</th>
    <th>Brand / Model</th>
    <th>Quantity</th>
    <th>Status</th>
    <th>Image</th>
    <th>Action</th>

</tr>

<%

if(equipmentList != null){

    if(equipmentList.isEmpty()){
%>

<tr>

<td colspan="7"
    align="center">

No equipment found.

</td>

</tr>

<%
    }

    for(equipmentBean equipment
            : equipmentList){
%>

<tr>

<td>

<%=equipment.getEquipmentID()%>

</td>

<td>

<%=equipment.getEquipmentName()%>

</td>

<td>

<%=equipment.getBrandModel()%>

</td>

<td>

<%=equipment.getQuantity()%>

</td>

<td>

<%=equipment.getEquipmentStatus()%>

</td>

<td>

<img src="<%=request.getContextPath()%>/uploads/<%=equipment.getEquipmentImage()%>"
     width="120"
     height="80">

</td>

<td>

<a href="<%=request.getContextPath()%>/admin/updateEquipment.jsp?id=<%=equipment.getEquipmentID()%>">

Update

</a>

|

<a href="<%=request.getContextPath()%>/DeleteEquipmentServlet?id=<%=equipment.getEquipmentID()%>"
   onclick="return confirm('Are you sure you want to delete this equipment?');">

Delete

</a>

</td>

</tr>

<%
    }
}
%>

</table>

<br><br>

<a href="<%=request.getContextPath()%>/dashboardAdmin">

Back to Dashboard

</a>

</body>

</html>
