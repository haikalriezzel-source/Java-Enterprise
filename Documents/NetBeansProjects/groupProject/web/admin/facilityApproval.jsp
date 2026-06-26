<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
ArrayList<Object[]> pendingList =
        (ArrayList<Object[]>)
        request.getAttribute("pendingList");

ArrayList<Object[]> historyList =
        (ArrayList<Object[]>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html>

<head>
    <title>Facility Approval Management</title>
</head>

<body>

<h1>Facility Approval Management</h1>

<form action="<%=request.getContextPath()%>/AdminFacilityListServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search Student / Facility / Status..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/AdminFacilityListServlet">
        Reset
    </a>

</form>

<br>

<hr>

<h2>Pending Facility Requests</h2>

<table border="1">

<tr>

    <th>Booking ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Facility</th>
    <th>Date</th>
    <th>Start Time</th>
    <th>End Time</th>
    <th>Purpose</th>
    <th>Participants</th>
    <th>Action</th>

</tr>

<%

if(pendingList != null){

    if(pendingList.isEmpty()){
%>

<tr>

<td colspan="10" align="center">

No pending booking found.

</td>

</tr>

<%
    }

    for(Object[] row : pendingList){
%>

<tr>

<td><%=row[0]%></td>

<td><%=row[1]%></td>

<td><%=row[2]%></td>

<td><%=row[3]%></td>

<td><%=row[4]%></td>

<td><%=row[5]%></td>

<td><%=row[6]%></td>

<td><%=row[7]%></td>

<td><%=row[8]%></td>

<td>

<form action="<%=request.getContextPath()%>/ApproveFacilityServlet"
      method="post">

    <input type="hidden"
           name="bookingID"
           value="<%=row[0]%>">

    <button type="submit"
            name="action"
            value="Approved">

        Approve

    </button>

    <button type="submit"
            name="action"
            value="Rejected">

        Reject

    </button>

</form>

</td>

</tr>

<%
    }
}
%>

</table>

<br><br>

<hr>

<h2>Facility Booking History</h2>

<table border="1">

<tr>

    <th>Booking ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Facility</th>
    <th>Date</th>
    <th>Start Time</th>
    <th>End Time</th>
    <th>Purpose</th>
    <th>Participants</th>
    <th>Status</th>

</tr>

<%

if(historyList != null){

    if(historyList.isEmpty()){
%>

<tr>

<td colspan="10" align="center">

No booking history found.

</td>

</tr>

<%
    }

    for(Object[] row : historyList){
%>

<tr>

<td><%=row[0]%></td>

<td><%=row[1]%></td>

<td><%=row[2]%></td>

<td><%=row[3]%></td>

<td><%=row[4]%></td>

<td><%=row[5]%></td>

<td><%=row[6]%></td>

<td><%=row[7]%></td>

<td><%=row[8]%></td>

<td><%=row[9]%></td>

</tr>

<%
    }
}
%>

</table>

<br><br>

<a href="<%=request.getContextPath()%>/dashboardAdmin">

Back To Dashboard

</a>

</body>

</html>