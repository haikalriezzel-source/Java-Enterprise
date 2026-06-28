<%--
    Document   : myLoan
    Created on : Jun 24, 2026, 12:43:41 AM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.LoanEquipmentBean"%>

<%
request.setAttribute("pageTitle", "My Equipment Loans");
request.setAttribute("pageSubtitle", "Track your active equipment loans and loan history.");

ArrayList<LoanEquipmentBean> activeList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("activeList");

ArrayList<LoanEquipmentBean> historyList =
        (ArrayList<LoanEquipmentBean>)
        request.getAttribute("historyList");
%>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Equipment Loans</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/css/student.css" rel="stylesheet">

</head>

<body class="student-ui-body">

<div class="student-shell">

    <jsp:include page="layout/sidebar.jsp" />

    <div class="student-main">

        <jsp:include page="layout/topbar.jsp" />

        <main class="student-content">

            <div class="student-card card mb-4">
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/MyLoanServlet"
                          method="get">

                        <div class="row g-2">
                            <div class="col-md-9">
                                <input class="form-control"
                                       type="text"
                                       name="keyword"
                                       placeholder="Search equipment..."
                                       value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">
                            </div>

                            <div class="col-md-3 d-flex gap-2">
                                <input class="btn btn-student-primary flex-fill"
                                       type="submit"
                                       value="Search">

                                <a class="btn btn-student-soft"
                                   href="<%=request.getContextPath()%>/MyLoanServlet">

                                    Reset

                                </a>
                            </div>
                        </div>

                    </form>
                </div>
            </div>

            <div class="student-card card mb-4">
                <div class="card-header">
                    <i class="fa-solid fa-handshake me-2 text-primary"></i>Current Equipment Loans
                </div>

                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-student table-hover">

                            <thead>
                                <tr>
                                    <th>Loan ID</th>
                                    <th>Equipment</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Quantity</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>

                            <%
                            if(activeList != null){

                                if(activeList.isEmpty()){
                            %>

                                <tr>
                                    <td colspan="7">
                                        <div class="student-empty">No current loan found.</div>
                                    </td>
                                </tr>

                            <%
                                }

                                for(LoanEquipmentBean loan : activeList){

                                    String status = loan.getLoanStatus();
                                    String badge = "Approved".equalsIgnoreCase(status)
                                            ? "badge-student-success"
                                            : "badge-student-warning";
                            %>

                                <tr>
                                    <td>
                                        <span class="badge badge-student-neutral rounded-pill">
                                            <%=loan.getLoanID()%>
                                        </span>
                                    </td>

                                    <td class="fw-bold">
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
                                        <span class="badge <%=badge%> rounded-pill">
                                            <%=loan.getLoanStatus()%>
                                        </span>
                                    </td>

                                    <td>

                                    <%
                                    if("Approved".equals(
                                            loan.getLoanStatus())){
                                    %>

                                        <form action="<%=request.getContextPath()%>/ReturnEquipmentServlet"
                                              method="post"
                                              class="m-0">

                                            <input type="hidden"
                                                   name="loanID"
                                                   value="<%=loan.getLoanID()%>">

                                            <button class="btn btn-sm btn-student-primary"
                                                    type="submit">

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

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>

            <div class="student-card card" id="loanHistory">
                <div class="card-header">
                    <i class="fa-solid fa-clock-rotate-left me-2 text-primary"></i>Loan History
                </div>

                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-student table-hover">

                            <thead>
                                <tr>
                                    <th>Loan ID</th>
                                    <th>Equipment</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Quantity</th>
                                    <th>Status</th>
                                </tr>
                            </thead>

                            <tbody>

                            <%
                            if(historyList != null){

                                if(historyList.isEmpty()){
                            %>

                                <tr>
                                    <td colspan="6">
                                        <div class="student-empty">No loan history found.</div>
                                    </td>
                                </tr>

                            <%
                                }

                                for(LoanEquipmentBean loan : historyList){

                                    String status = loan.getLoanStatus();
                                    String badge = "Rejected".equalsIgnoreCase(status)
                                            ? "badge-student-danger"
                                            : "badge-student-info";
                            %>

                                <tr>
                                    <td>
                                        <span class="badge badge-student-neutral rounded-pill">
                                            <%=loan.getLoanID()%>
                                        </span>
                                    </td>

                                    <td class="fw-bold">
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
                                        <span class="badge <%=badge%> rounded-pill">
                                            <%=loan.getLoanStatus()%>
                                        </span>
                                    </td>
                                </tr>

                            <%
                                }
                            }
                            %>

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <a class="btn btn-student-soft"
                   href="<%=request.getContextPath()%>/studentDashboard">

                    Back To Dashboard

                </a>
            </div>

        </main>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
window.onload = function () {

<%
if(request.getAttribute("scrollToHistory") != null){
%>

    document.getElementById("loanHistory").scrollIntoView({
        behavior: "smooth",
        block: "start"
    });

<%
}
%>

};
</script>

</body>

</html>
