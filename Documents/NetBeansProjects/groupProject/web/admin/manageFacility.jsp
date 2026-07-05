<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.facilityBean"%>

<%
    ArrayList<facilityBean> facilityList =
        (ArrayList<facilityBean>) request.getAttribute("facilityList");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Facility</title>

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
            <h1 class="admin-page-title">Facility List</h1>
            <p class="admin-page-subtitle">
                Search, update, and manage all facilities.
            </p>
        </div>

        <a href="<%=request.getContextPath()%>/admin/addFacility.jsp"
           class="btn btn-admin-primary">
            <i class="fa-solid fa-plus me-2"></i>
            Add Facility
        </a>

    </div>

    <!-- SEARCH -->
    <div class="admin-card admin-search-card mb-4">

        <form action="<%=request.getContextPath()%>/ViewFacilityServlet" method="get">

            <div class="row g-2">

                <div class="col-md-9">
                    <input class="form-control"
                           type="text"
                           name="keyword"
                           placeholder="Search facility..."
                           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">
                </div>

                <div class="col-md-3 d-flex gap-2">
                    <input class="btn btn-admin-primary flex-fill" type="submit" value="Search">

                    <a class="btn btn-admin-soft"
                       href="<%=request.getContextPath()%>/ViewFacilityServlet">
                        Reset
                    </a>
                </div>

            </div>

        </form>

    </div>

    <!-- TABLE -->
    <div class="admin-card card">

        <div class="card-header">
            <i class="fa-solid fa-building me-2 text-primary"></i>
            Facility Records
        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover align-middle">

                    <thead>
                        <tr>
                            <th>Facility Name</th>
                            <th>Location</th>
                            <th>Capacity</th>
                            <th>Status</th>
                            <th>Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>

                        <%
                            if (facilityList != null) {

                                if (facilityList.isEmpty()) {
                        %>

                            <tr>
                                <td colspan="6">
                                    <div class="admin-empty">
                                        <i class="fa-regular fa-folder-open"></i>
                                        <div>No facility found.</div>
                                    </div>
                                </td>
                            </tr>

                        <%
                                }

                                for (facilityBean facility : facilityList) {

                                    String st = facility.getFacilityStatus();

                                    String badge =
                                            "Available".equalsIgnoreCase(st)
                                                    ? "badge-admin-success"
                                                    : "badge-admin-warning";
                        %>

                            <tr>

                                <td class="fw-bold">
                                    <%= facility.getFacilityName() %>
                                </td>

                                <td><%= facility.getLocation() %></td>

                                <td><%= facility.getCapacity() %></td>

                                <td>
                                    <span class="badge <%= badge %> rounded-pill">
                                        <%= st %>
                                    </span>
                                </td>

                                <td>
                                    <img class="admin-thumb"
                                         src="<%=request.getContextPath()%>/uploads/<%=facility.getFacilityImage()%>">
                                </td>

                                <td>
                                    <div class="admin-action-row">

                                        <a class="btn btn-sm btn-admin-soft"
                                           href="<%=request.getContextPath()%>/admin/updateFacility.jsp?id=<%=facility.getFacilityID()%>">
                                            <i class="fa-solid fa-pen"></i>
                                            Update
                                        </a>

                                        <a class="btn btn-sm btn-outline-danger"
                                           href="<%=request.getContextPath()%>/DeleteFacilityServlet?id=<%=facility.getFacilityID()%>"
                                           onclick="return confirm('Delete this facility?');">
                                            <i class="fa-solid fa-trash"></i>
                                            Delete
                                        </a>

                                    </div>
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