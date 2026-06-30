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

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Return Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          rel="stylesheet">

    <link href="<%=request.getContextPath()%>/assets/css/admin.css"
          rel="stylesheet">

</head>

<body class="admin-ui-body">

<jsp:include page="layout/topbar.jsp"/>

<main class="admin-ui-main">

    <div class="admin-page-header">

        <div>

            <h1 class="admin-page-title">

                Return Management

            </h1>

            <p class="admin-page-subtitle">

                Verify returned equipment and update inventory.

            </p>

        </div>

        <a href="<%=request.getContextPath()%>/dashboardAdmin"
           class="btn btn-admin-soft">

            <i class="fa-solid fa-arrow-left me-2"></i>

            Dashboard

        </a>

    </div>

    <!-- Search -->

    <div class="admin-card admin-search-card mb-4">

        <form action="<%=request.getContextPath()%>/AdminReturnListServlet"
              method="get">

            <div class="row g-2">

                <div class="col-md-9">

                    <input
                        class="form-control"
                        type="text"
                        name="keyword"
                        placeholder="Search Student ID, Student Name, Equipment..."
                        value="<%=request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword")%>">

                </div>

                <div class="col-md-3 d-flex gap-2">

                    <input
                        class="btn btn-admin-primary flex-fill"
                        type="submit"
                        value="Search">

                    <a
                        class="btn btn-admin-soft"
                        href="<%=request.getContextPath()%>/AdminReturnListServlet">

                        Reset

                    </a>

                </div>

            </div>

        </form>

    </div>

    <!-- Pending Return -->

    <div class="admin-card card mb-4">

        <div class="card-header">

            <i class="fa-solid fa-rotate-left me-2 text-primary"></i>

            Pending Return Requests

        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover align-middle">

                    <thead>

                    <tr>

                        <th>Return ID</th>

                        <th>Loan ID</th>

                        <th>Student ID</th>

                        <th>Student Name</th>

                        <th>Equipment</th>

                        <th>Quantity</th>

                        <th>Return Date</th>

                        <th>Status</th>

                        <th>Action</th>

                    </tr>

                    </thead>

                    <tbody>

                    <%

                    if(pendingList != null){

                        if(pendingList.isEmpty()){

                    %>

                    <tr>

                        <td colspan="9">

                            <div class="admin-empty">

                                <i class="fa-regular fa-folder-open"></i>

                                <div>

                                    No pending return requests.

                                </div>

                            </div>

                        </td>

                    </tr>

                    <%

                        }

                        for(ReturnRecordBean record : pendingList){

                    %>

                    <tr>

                        <td>

                            <%=record.getReturnID()%>

                        </td>

                        <td>

                            <%=record.getLoanID()%>

                        </td>

                        <td>

                            <%=record.getStudentID()%>

                        </td>

                        <td class="fw-bold">

                            <%=record.getStudentName()%>

                        </td>

                        <td>

                            <%=record.getEquipmentName()%>

                        </td>

                        <td>

                            <%=record.getReturnQuantity()%>

                        </td>

                        <td>

                            <%=record.getReturnDate()%>

                        </td>

                        <td>

                            <span class="badge badge-admin-warning rounded-pill">

                                <%=record.getReturnStatus()%>

                            </span>

                        </td>

                        <td>

                            <form
                                action="<%=request.getContextPath()%>/VerifyReturnServlet"
                                method="post">

                                <input
                                    type="hidden"
                                    name="returnID"
                                    value="<%=record.getReturnID()%>">

                                <select
                                    class="form-select form-select-sm mb-2"
                                    name="equipmentCondition"
                                    required>

                                    <option value="" selected>

                                        -- Select Condition --

                                    </option>

                                    <option value="Good">

                                        Good (Available)

                                    </option>

                                    <option value="Minor Damage">

                                        Minor Damage (Maintenance)

                                    </option>

                                    <option value="Major Damage">

                                        Major Damage (Damaged)

                                    </option>

                                    <option value="Lost">

                                        Lost

                                    </option>

                                </select>

                                <button
                                    class="btn btn-success btn-sm w-100"
                                    type="submit">

                                    <i class="fa-solid fa-circle-check me-2"></i>

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

            </div>

        </div>

    </div>

    <!-- Return History -->

    <div class="admin-card card">

        <div class="card-header">

            <i class="fa-solid fa-clock-rotate-left me-2 text-primary"></i>

            Return History

        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover align-middle">

                    <thead>

                    <tr>

                        <th>Return ID</th>

                        <th>Loan ID</th>

                        <th>Student ID</th>

                        <th>Student Name</th>

                        <th>Equipment</th>

                        <th>Quantity</th>

                        <th>Return Date</th>

                        <th>Condition</th>

                        <th>Status</th>

                    </tr>

                    </thead>

                    <tbody>

                    <%

                    if(historyList != null){

                        if(historyList.isEmpty()){

                    %>

                    <tr>

                        <td colspan="9">

                            <div class="admin-empty">

                                <i class="fa-regular fa-folder-open"></i>

                                <div>

                                    No return history found.

                                </div>

                            </div>

                        </td>

                    </tr>

                    <%

                        }

                        for(ReturnRecordBean record : historyList){

                    %>

                    <tr>

                        <td>

                            <%=record.getReturnID()%>

                        </td>

                        <td>

                            <%=record.getLoanID()%>

                        </td>

                        <td>

                            <%=record.getStudentID()%>

                        </td>

                        <td class="fw-bold">

                            <%=record.getStudentName()%>

                        </td>

                        <td>

                            <%=record.getEquipmentName()%>

                        </td>

                        <td>

                            <%=record.getReturnQuantity()%>

                        </td>

                        <td>

                            <%=record.getReturnDate()%>

                        </td>

                        <td>

                            <%

                            String condition =
                                    record.getEquipmentCondition();

                            String badgeClass =
                                    "bg-success";

                            if("Minor Damage".equals(condition)){

                                badgeClass =
                                        "bg-warning text-dark";

                            }

                            else if("Major Damage".equals(condition)){

                                badgeClass =
                                        "bg-danger";

                            }

                            else if("Lost".equals(condition)){

                                badgeClass =
                                        "bg-dark";

                            }

                            %>

                            <span class="badge <%=badgeClass%> rounded-pill">

                                <%=condition%>

                            </span>

                        </td>

                        <td>

                            <span class="badge badge-admin-success rounded-pill">

                                <%=record.getReturnStatus()%>

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

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>