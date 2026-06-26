<%-- 
    Document   : facilityList
    Created on : Jun 22, 2026, 11:46:52 PM
    Author     : haikalriez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.facilityBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
ArrayList<facilityBean> facilityList =
(ArrayList<facilityBean>)
request.getAttribute("facilityList");
%>

<!DOCTYPE html>

<html>

<head>

<title>Facility List</title>

</head>

<body>

<h1>Available Facilities</h1>

<form action="<%=request.getContextPath()%>/facilityList"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search facility..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/facilityList">

        Reset

    </a>

</form>

<br>

<table border="1">

<tr>

    <th>ID</th>
    <th>Facility Name</th>
    <th>Location</th>
    <th>Capacity</th>
    <th>Open Time</th>
    <th>Close Time</th>
    <th>Status</th>
    <th>Image</th>
    <th>Action</th>

</tr>

<%

if(facilityList != null){

    if(facilityList.isEmpty()){
%>

<tr>

<td colspan="9"
    align="center">

No facility found.

</td>

</tr>

<%
    }

    for(facilityBean facility : facilityList){
%>

<tr>

<td>

<%=facility.getFacilityID()%>

</td>

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

<%=facility.getOpenTime()%>

</td>

<td>

<%=facility.getCloseTime()%>

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

<a href="<%=request.getContextPath()%>/student/bookingFacility.jsp?facilityID=<%=facility.getFacilityID()%>">

Book

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
