<%-- 
    Document   : returnApproval
    Created on : Jun 24, 2026, 1:45:36 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.ReturnRecordBean"%>

<%
ArrayList<ReturnRecordBean> pendingList =
        (ArrayList<ReturnRecordBean>)
        request.getAttribute("pendingList");

ArrayList<ReturnRecordBean> historyList =
        (ArrayList<ReturnRecordBean>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html>

<head>
    <title>Return Management</title>
</head>

<body>

<h1>Return Management</h1>

<form action="<%=request.getContextPath()%>/AdminReturnListServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search Student ID / Name / Status / Condition"
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/AdminReturnListServlet">
        Reset
    </a>

</form>

<br>

<hr>

<h2>Pending Return Requests</h2>

<table border="1">

<tr>

    <th>Return ID</th>
    <th>Loan ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Equipment ID</th>
    <th>Quantity</th>
    <th>Return Date</th>
    <th>Condition</th>
    <th>Status</th>
    <th>Action</th>

</tr>

<%

if(pendingList != null){

    if(pendingList.isEmpty()){
%>

<tr>

<td colspan="10" align="center">

No pending return records found.

</td>

</tr>

<%
    }

    for(ReturnRecordBean record : pendingList){
%>

<tr>

<td><%=record.getReturnID()%></td>

<td><%=record.getLoanID()%></td>

<td><%=record.getStudentID()%></td>

<td><%=record.getStudentName()%></td>

<td><%=record.getEquipmentID()%></td>

<td><%=record.getReturnQuantity()%></td>

<td><%=record.getReturnDate()%></td>

<td><%=record.getEquipmentCondition()%></td>

<td><%=record.getReturnStatus()%></td>

<td>

<form action="<%=request.getContextPath()%>/VerifyReturnServlet"
      method="post">

    <input type="hidden"
           name="returnID"
           value="<%=record.getReturnID()%>">

    <select name="equipmentCondition">

        <option value="Good">

            Good

        </option>

        <option value="Minor Damage">

            Minor Damage

        </option>

        <option value="Major Damage">

            Major Damage

        </option>

        <option value="Lost">

            Lost

        </option>

    </select>

    <button type="submit">

        Verify Return

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

<h2>Return History</h2>

<table border="1">

<tr>

    <th>Return ID</th>
    <th>Loan ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Equipment ID</th>
    <th>Quantity</th>
    <th>Return Date</th>
    <th>Condition</th>
    <th>Status</th>

</tr>

<%

if(historyList != null){

    if(historyList.isEmpty()){
%>

<tr>

<td colspan="9" align="center">

No return history found.

</td>

</tr>

<%
    }

    for(ReturnRecordBean record : historyList){
%>

<tr>

<td><%=record.getReturnID()%></td>

<td><%=record.getLoanID()%></td>

<td><%=record.getStudentID()%></td>

<td><%=record.getStudentName()%></td>

<td><%=record.getEquipmentID()%></td>

<td><%=record.getReturnQuantity()%></td>

<td><%=record.getReturnDate()%></td>

<td><%=record.getEquipmentCondition()%></td>

<td><%=record.getReturnStatus()%></td>

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

