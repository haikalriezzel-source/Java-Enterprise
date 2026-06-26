<%-- 
    Document   : loanApproval
    Created on : Jun 24, 2026, 1:09:07 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.LoanEquipmentBean"%>

<%
ArrayList<LoanEquipmentBean> pendingList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("pendingList");

ArrayList<LoanEquipmentBean> historyList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html>

<head>
    <title>Equipment Loan Management</title>
</head>

<body>

<h1>Equipment Loan Management</h1>

<form action="<%=request.getContextPath()%>/AdminLoanListServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search Student / Equipment / Status..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/AdminLoanListServlet">
        Reset
    </a>

</form>

<br>

<hr>

<h2>Pending Loan Requests</h2>

<table border="1">

<tr>

    <th>Loan ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Equipment</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Quantity</th>
    <th>Action</th>

</tr>

<%

if(pendingList != null){

    if(pendingList.isEmpty()){
%>

<tr>

<td colspan="8"
    align="center">

No pending loan found.

</td>

</tr>

<%
    }

    for(LoanEquipmentBean loan
            : pendingList){
%>

<tr>

<td>

<%=loan.getLoanID()%>

</td>

<td>

<%=loan.getStudentID()%>

</td>

<td>

<%=loan.getStudentName()%>

</td>

<td>

<%=loan.getEquipmentName()%>

</td>

<td>

<%=loan.getStartDate()%>

</td>

<td>

<%=loan.getEndDate()%>

</td>

<td>

<%=loan.getLoanQuantity()%>

</td>

<td>

<form action="<%=request.getContextPath()%>/ApproveLoanServlet"
      method="post">

    <input type="hidden"
           name="loanID"
           value="<%=loan.getLoanID()%>">

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

<h2>Loan History</h2>

<table border="1">

<tr>

    <th>Loan ID</th>
    <th>Student ID</th>
    <th>Student Name</th>
    <th>Equipment</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Quantity</th>
    <th>Status</th>

</tr>

<%

if(historyList != null){

    if(historyList.isEmpty()){
%>

<tr>

<td colspan="8"
    align="center">

No loan history found.

</td>

</tr>

<%
    }

    for(LoanEquipmentBean loan
            : historyList){
%>

<tr>

<td>

<%=loan.getLoanID()%>

</td>

<td>

<%=loan.getStudentID()%>

</td>

<td>

<%=loan.getStudentName()%>

</td>

<td>

<%=loan.getEquipmentName()%>

</td>

<td>

<%=loan.getStartDate()%>

</td>

<td>

<%=loan.getEndDate()%>

</td>

<td>

<%=loan.getLoanQuantity()%>

</td>

<td>

<%=loan.getLoanStatus()%>

</td>

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


