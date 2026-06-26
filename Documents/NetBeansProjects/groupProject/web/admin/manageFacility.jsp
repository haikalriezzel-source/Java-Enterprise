<%-- 
    Document   : manageFacility
    Created on : Jun 15, 2026, 4:08:00 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.facilityBean"%>

<!DOCTYPE html>

<html>

<head>
    <title>Manage Facility</title>
</head>

<body>

<h1>Facility List</h1>

<form action="<%=request.getContextPath()%>/ViewFacilityServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search facility..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/ViewFacilityServlet">
        Reset
    </a>

</form>

<br>

<a href="<%=request.getContextPath()%>/admin/addFacility.jsp">
    Add Facility
</a>

<br><br>

<%
ArrayList<facilityBean> facilityList =
        (ArrayList<facilityBean>)
        request.getAttribute("facilityList");
%>

<table border="1">

<tr>

    <th>Facility Name</th>
    <th>Location</th>
    <th>Capacity</th>
    <th>Status</th>
    <th>Image</th>
    <th>Action</th>

</tr>

<%

if(facilityList != null){

    if(facilityList.isEmpty()){
%>

<tr>

<td colspan="6"
    align="center">

No facility found.

</td>

</tr>

<%
    }

    for(facilityBean facility
            : facilityList){
%>

<tr>

<td>

<%=facility.getFacilityName()%>

</td>

<td>

<%=facility.getLocation()%>

</td>

<td>

<%=facility.getCapacity()%>

</td>

<td>

<%=facility.getFacilityStatus()%>

</td>

<td>

<img src="<%=request.getContextPath()%>/uploads/<%=facility.getFacilityImage()%>"
     width="120"
     height="80">

</td>

<td>

<a href="<%=request.getContextPath()%>/admin/updateFacility.jsp?id=<%=facility.getFacilityID()%>">

Update

</a>

|

<a href="<%=request.getContextPath()%>/DeleteFacilityServlet?id=<%=facility.getFacilityID()%>"
   onclick="return confirm('Delete this facility?');">

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
