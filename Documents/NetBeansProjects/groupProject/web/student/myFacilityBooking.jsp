<%-- 
    Document   : myFacilityBooking
    Created on : Jun 24, 2026, 11:42:17 PM
    Author     : haikalriez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.facilityBookingBean"%>

<%
ArrayList<facilityBookingBean> activeList =
        (ArrayList<facilityBookingBean>)
        request.getAttribute("activeList");

ArrayList<facilityBookingBean> historyList =
        (ArrayList<facilityBookingBean>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html>

<head>

<title>My Facility Bookings</title>

</head>

<body>

<h2>My Facility Bookings</h2>

<form action="<%=request.getContextPath()%>/MyFacilityBookingServlet"
      method="get">

<input type="text"
       name="keyword"
       placeholder="Search booking..."
       value="<%=request.getAttribute("keyword")==null?"":request.getAttribute("keyword")%>">

<input type="submit"
       value="Search">

<a href="<%=request.getContextPath()%>/MyFacilityBookingServlet">

Reset

</a>

</form>

<br>

<h2>Current Facility Bookings</h2>

<table border="1">

<tr>

<th>Booking ID</th>
<th>Facility Name</th>
<th>Booking Date</th>
<th>Start Time</th>
<th>End Time</th>
<th>Purpose</th>
<th>Participants</th>
<th>Status</th>

</tr>

<%

if(activeList!=null){

if(activeList.isEmpty()){
%>

<tr>

<td colspan="8" align="center">

No current booking found.

</td>

</tr>

<%
}

for(facilityBookingBean booking
        : activeList){
%>

<tr>

<td><%=booking.getBookingID()%></td>
<td><%=booking.getFacilityName()%></td>
<td><%=booking.getBookingDate()%></td>
<td><%=booking.getStartTime()%></td>
<td><%=booking.getEndTime()%></td>
<td><%=booking.getPurpose()%></td>
<td><%=booking.getNumberOfParticipants()%></td>
<td><%=booking.getBookingStatus()%></td>

</tr>

<%
}
}
%>

</table>

<br><br>

<hr>

<h2>Booking History</h2>

<table border="1">

<tr>

<th>Booking ID</th>
<th>Facility Name</th>
<th>Booking Date</th>
<th>Start Time</th>
<th>End Time</th>
<th>Purpose</th>
<th>Participants</th>
<th>Status</th>

</tr>

<%

if(historyList!=null){

if(historyList.isEmpty()){
%>

<tr>

<td colspan="8" align="center">

No booking history found.

</td>

</tr>

<%
}

for(facilityBookingBean booking
        : historyList){
%>

<tr>

<td><%=booking.getBookingID()%></td>
<td><%=booking.getFacilityName()%></td>
<td><%=booking.getBookingDate()%></td>
<td><%=booking.getStartTime()%></td>
<td><%=booking.getEndTime()%></td>
<td><%=booking.getPurpose()%></td>
<td><%=booking.getNumberOfParticipants()%></td>
<td><%=booking.getBookingStatus()%></td>

</tr>

<%
}
}
%>

</table>

<br>

<a href="<%=request.getContextPath()%>/studentDashboard">

Back To Dashboard

</a>

</body>

</html>

