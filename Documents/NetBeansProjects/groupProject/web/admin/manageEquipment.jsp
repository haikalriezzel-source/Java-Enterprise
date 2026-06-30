<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.equipmentBean"%>

<%
ArrayList<equipmentBean> equipmentList =
(ArrayList<equipmentBean>)
request.getAttribute("equipmentList");
%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Manage Equipment</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          rel="stylesheet">

    <link href="<%=request.getContextPath()%>/assets/css/admin.css"
          rel="stylesheet">

</head>

<body class="admin-ui-body">

<jsp:include page="layout/topbar.jsp" />

<main class="admin-ui-main">

    <div class="admin-page-header">

        <div>

            <h1 class="admin-page-title">
                Equipment List
            </h1>

            <p class="admin-page-subtitle">
                Search, update and monitor inventory.
            </p>

        </div>

        <a href="<%=request.getContextPath()%>/admin/addEquipment.jsp"
           class="btn btn-admin-primary">

            <i class="fa-solid fa-plus me-2"></i>

            Add New Equipment

        </a>

    </div>

    <div class="admin-card admin-search-card mb-4">

        <form action="<%=request.getContextPath()%>/ViewEquipmentServlet"
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

                    <input class="btn btn-admin-primary flex-fill"
                           type="submit"
                           value="Search">

                    <a class="btn btn-admin-soft"
                       href="<%=request.getContextPath()%>/ViewEquipmentServlet">

                        Reset

                    </a>

                </div>

            </div>

        </form>

    </div>

    <div class="admin-card card">

        <div class="card-header">

            <i class="fa-solid fa-dumbbell me-2 text-primary"></i>

            Equipment Records

        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover align-middle">

                    <thead>

                    <tr>

                        <th>ID</th>

                        <th>Equipment</th>

                        <th>Brand / Model</th>

                        <th>Total</th>

                        <th>Available</th>

                        <th>Maintenance</th>

                        <th>Damaged</th>

                        <th>Image</th>

                        <th>Action</th>

                    </tr>

                    </thead>

                    <tbody>

                    <%

                    if(equipmentList != null){

                        if(equipmentList.isEmpty()){

                    %>

                    <tr>

                        <td colspan="9">

                            <div class="admin-empty">

                                <i class="fa-regular fa-folder-open"></i>

                                <div>

                                    No equipment found.

                                </div>

                            </div>

                        </td>

                    </tr>

                    <%

                        }

                        for(equipmentBean equipment
                                : equipmentList){

                    %>

                    <tr>

                        <td>

                            <span class="badge badge-admin-neutral rounded-pill">

                                <%= equipment.getEquipmentID() %>

                            </span>

                        </td>

                        <td class="fw-bold">

                            <%= equipment.getEquipmentName() %>

                        </td>

                        <td>

                            <%= equipment.getBrandModel() %>

                        </td>

                        <td>

                            <span class="badge bg-dark">

                                <%= equipment.getTotalQuantity() %>

                            </span>

                        </td>

                        <td>

                            <span class="badge bg-success">
                                Available : <%= equipment.getAvailableQuantity() %>
                            </span>

                        </td>

                        <td>

                            <span class="badge bg-warning text-dark">

                                Maintenance : <%= equipment.getMaintenanceQuantity() %>

                            </span>

                        </td>

                        <td>

                            <span class="badge bg-danger">

                                Damaged : <%= equipment.getDamagedQuantity() %>
                                
                            </span>

                        </td>

                        <td>

                            <img class="admin-thumb"
                                 src="<%=request.getContextPath()%>/uploads/<%=equipment.getEquipmentImage()%>">

                        </td>

                        <td>

    <div class="d-grid gap-2">

        <!-- Update & Delete -->

        <div class="d-flex gap-2">

            <a class="btn btn-sm btn-admin-soft flex-fill"
               href="<%=request.getContextPath()%>/admin/updateEquipment.jsp?id=<%=equipment.getEquipmentID()%>">

                <i class="fa-solid fa-pen"></i>

                Update

            </a>

            <a class="btn btn-sm btn-outline-danger flex-fill"
               href="<%=request.getContextPath()%>/DeleteEquipmentServlet?id=<%=equipment.getEquipmentID()%>"
               onclick="return confirm('Are you sure you want to delete this equipment?');">

                <i class="fa-solid fa-trash"></i>

                Delete

            </a>

        </div>

        <!-- Repair & Dispose -->

        <div class="d-flex gap-2">

            <a class="btn btn-sm btn-warning flex-fill
                <%= equipment.getMaintenanceQuantity()==0 ? "disabled" : "" %>"
               href="<%=request.getContextPath()%>/admin/repairEquipment.jsp?id=<%=equipment.getEquipmentID()%>">

                <i class="fa-solid fa-screwdriver-wrench"></i>

                Repair

            </a>

            <a class="btn btn-sm btn-dark flex-fill
                <%= equipment.getDamagedQuantity()==0 ? "disabled" : "" %>"
               href="<%=request.getContextPath()%>/admin/disposeEquipment.jsp?id=<%=equipment.getEquipmentID()%>">

                <i class="fa-solid fa-recycle"></i>

                Dispose

            </a>

        </div>

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