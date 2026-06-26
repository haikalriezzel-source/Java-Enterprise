<%-- 
    Document   : manageStudent
    Created on : Jun 25, 2026, 12:27:40 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.student"%>

<%
ArrayList<student> studentList =
        (ArrayList<student>)
        request.getAttribute("studentList");
%>

<!DOCTYPE html>

<html>

<head>

<title>Manage Students</title>

</head>

<body>

<h1>Manage Students</h1>

<form action="<%=request.getContextPath()%>/ViewStudentServlet"
      method="get">

    <input type="text"
           name="keyword"
           placeholder="Search student..."
           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">

    <input type="submit"
           value="Search">

    <a href="<%=request.getContextPath()%>/ViewStudentServlet">
        Reset
    </a>

</form>

<br>

<table border="1">

<tr>

<th>Student ID</th>
<th>Student Name</th>
<th>Date of Birth</th>
<th>Programme</th>
<th>Phone Number</th>
<th>Action</th>

</tr>

<%

if(studentList != null){

    if(studentList.isEmpty()){
%>

<tr>

<td colspan="6"
    align="center">

No student records found.

</td>

</tr>

<%
    }

    for(student s : studentList){
%>

<tr>

<td>

<%=s.getStudentID()%>

</td>

<td>

<%=s.getStudentName()%>

</td>

<td>

<%=s.getStudentDOB()%>

</td>

<td>

<%=s.getProgramme()%>

</td>

<td>

<%=s.getPhoneNumber()%>

</td>

<td>

<a href="<%=request.getContextPath()%>/admin/updateStudent.jsp?id=<%=s.getStudentID()%>">

Update

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

Back To Dashboard

</a>

</body>

</html>
