<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<Object[]> pendingList =
        (ArrayList<Object[]>) request.getAttribute("pendingList");

    ArrayList<Object[]> historyList =
        (ArrayList<Object[]>) request.getAttribute("historyList");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facility Approval Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/admin.css" rel="stylesheet">
</head>

<body class="admin-ui-body">

<jsp:include page="layout/topbar.jsp" />

<main class="admin-ui-main">

    <!-- HEADER -->
    <div class="admin-page-header">
        <div>
            <h1 class="admin-page-title">Facility Approval Management</h1>
            <p class="admin-page-subtitle">
                Review pending booking requests and booking history.
            </p>
        </div>

        <a href="<%=request.getContextPath()%>/dashboardAdmin"
           class="btn btn-admin-soft">
            <i class="fa-solid fa-arrow-left me-2"></i>
            Dashboard
        </a>
    </div>

    <!-- SEARCH -->
    <div class="admin-card admin-search-card mb-4">
        <form action="<%=request.getContextPath()%>/AdminFacilityListServlet" method="get">

            <div class="row g-2">

                <div class="col-md-9">
                    <input class="form-control"
                           type="text"
                           name="keyword"
                           placeholder="Search Student / Facility / Status..."
                           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">
                </div>

                <div class="col-md-3 d-flex gap-2">
                    <input class="btn btn-admin-primary flex-fill" type="submit" value="Search">

                    <a class="btn btn-admin-soft"
                       href="<%=request.getContextPath()%>/AdminFacilityListServlet">
                        Reset
                    </a>
                </div>

            </div>

        </form>
    </div>

    <!-- PENDING -->
    <div class="admin-card card mb-4">

        <div class="card-header">
            <i class="fa-solid fa-hourglass-half me-2 text-warning"></i>
            Pending Facility Requests
        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover">

                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Facility</th>
                            <th>Date</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Purpose</th>
                            <th>Participants</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>

                        <%
                            if (pendingList != null) {
                                if (pendingList.isEmpty()) {
                        %>
                            <tr>
                                <td colspan="10">
                                    <div class="admin-empty">
                                        No pending booking found.
                                    </div>
                                </td>
                            </tr>
                        <%
                                }

                                for (Object[] row : pendingList) {
                        %>
                            <tr>
                                <td><%= row[0] %></td>
                                <td><%= row[1] %></td>
                                <td class="fw-bold"><%= row[2] %></td>
                                <td><%= row[3] %></td>
                                <td><%= row[4] %></td>
                                <td><%= row[5] %></td>
                                <td><%= row[6] %></td>
                                <td><%= row[7] %></td>
                                <td><%= row[8] %></td>

                                <td>
                                    <form class="admin-action-row"
                                          action="<%=request.getContextPath()%>/ApproveFacilityServlet"
                                          method="post">

                                        <input type="hidden" name="bookingID" value="<%=row[0]%>">

                                        <button class="btn btn-sm btn-success"
                                                type="submit"
                                                name="action"
                                                value="Approved">
                                            Approve
                                        </button>

                                        <button class="btn btn-sm btn-outline-danger"
                                                type="submit"
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

                    </tbody>

                </table>

            </div>

        </div>

    </div>

    <!-- HISTORY -->
    <div class="admin-card card">

        <div class="card-header">
            <i class="fa-solid fa-clock-rotate-left me-2 text-primary"></i>
            Facility Booking History
        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover">

                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Facility</th>
                            <th>Date</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Purpose</th>
                            <th>Participants</th>
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>

                        <%
                            if (historyList != null) {
                                if (historyList.isEmpty()) {
                        %>
                            <tr>
                                <td colspan="10">
                                    <div class="admin-empty">
                                        No booking history found.
                                    </div>
                                </td>
                            </tr>
                        <%
                                }

                                for (Object[] row : historyList) {

                                    String status = String.valueOf(row[9]);

                                    String badge =
                                            "Approved".equalsIgnoreCase(status)
                                                    ? "badge-admin-success"
                                                    : ("Rejected".equalsIgnoreCase(status)
                                                        ? "badge-admin-danger"
                                                        : "badge-admin-warning");
                        %>

                            <tr>
                                <td><%= row[0] %></td>
                                <td><%= row[1] %></td>
                                <td class="fw-bold"><%= row[2] %></td>
                                <td><%= row[3] %></td>
                                <td><%= row[4] %></td>
                                <td><%= row[5] %></td>
                                <td><%= row[6] %></td>
                                <td><%= row[7] %></td>
                                <td><%= row[8] %></td>

                                <td>
                                    <span class="badge <%= badge %> rounded-pill">
                                        <%= row[9] %>
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