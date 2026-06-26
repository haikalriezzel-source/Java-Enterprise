<%-- 
    Document   : equipmentList
    Created on : Jun 23, 2026, 12:18:51 AM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.equipmentBean"%>

<%
ArrayList<equipmentBean> equipmentList =
        (ArrayList<equipmentBean>)
        request.getAttribute("equipmentList");
%>

<!DOCTYPE html>

<html>

<head>
    <title>Equipment List</title>
</head>

<body>

<h1>Available Equipment</h1>

<form action="<%=request.getContextPath()%>/equipmentList"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search equipment..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/equipmentList">

        Reset

    </a>

</form>

<br>

<table border="1">

<tr>

    <th>ID</th>
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

<a href="<%=request.getContextPath()%>/BorrowEquipmentPageServlet?equipmentID=<%=equipment.getEquipmentID()%>">

Borrow

</a>

</td>

</tr>

<%
    }
}
%>

</table>

<br>

<a href="<%=request.getContextPath()%>/studentDashboard">

Back to Dashboard

</a>

</body>

</html>
