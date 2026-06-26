<%-- 
    Document   : myLoan
    Created on : Jun 24, 2026, 12:43:41 AM
    Author     : haikalriez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.LoanEquipmentBean"%>

<%
ArrayList<LoanEquipmentBean> activeList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("activeList");

ArrayList<LoanEquipmentBean> historyList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html>

<head>

<title>My Equipment Loans</title>

</head>

<body>

<h2>My Equipment Loans</h2>

<form action="<%=request.getContextPath()%>/MyLoanServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search equipment..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/MyLoanServlet">

        Reset

    </a>

</form>

<br>

<h2>Current Equipment Loans</h2>

<table border="1">

<tr>

    <th>Loan ID</th>
    <th>Equipment</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Quantity</th>
    <th>Status</th>
    <th>Action</th>

</tr>

<%

if(activeList != null){

    if(activeList.isEmpty()){
%>

<tr>

<td colspan="7" align="center">

No current loan found.

</td>

</tr>

<%
    }

    for(LoanEquipmentBean loan : activeList){
%>

<tr>

<td>

<%=loan.getLoanID()%>

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

<td>

<%

if("Approved".equals(
        loan.getLoanStatus())){
%>

<form action="<%=request.getContextPath()%>/ReturnEquipmentServlet"
      method="post">

    <input type="hidden"
           name="loanID"
           value="<%=loan.getLoanID()%>">

    <button type="submit">

        Return

    </button>

</form>

<%
}else{
%>

-

<%
}
%>

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

<td colspan="6"
    align="center">

No loan history found.

</td>

</tr>

<%
    }

    for(LoanEquipmentBean loan : historyList){
%>

<tr>

<td>

<%=loan.getLoanID()%>

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

<br>

<a href="<%=request.getContextPath()%>/studentDashboard">

Back To Dashboard

</a>

</body>

</html>
