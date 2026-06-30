<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Add Equipment</title>

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
                Add Sports Equipment
            </h1>

            <p class="admin-page-subtitle">
                Register a new equipment item into the inventory.
            </p>

        </div>

        <a href="<%= request.getContextPath() %>/dashboardAdmin"
           class="btn btn-admin-soft">

            <i class="fa-solid fa-arrow-left me-2"></i>

            Dashboard

        </a>

    </div>

    <div class="row justify-content-center">

        <div class="col-12 col-lg-8">

            <% if(request.getParameter("success") != null){ %>

            <div class="alert alert-success rounded-4">

                <i class="fa-solid fa-circle-check me-2"></i>

                Equipment added successfully.

            </div>

            <% } %>

            <div class="admin-card card">

                <div class="card-header">

                    <i class="fa-solid fa-dumbbell me-2 text-primary"></i>

                    Equipment Details

                </div>

                <div class="card-body p-4">

                    <form action="<%= request.getContextPath() %>/AddEquipmentServlet"
                          method="post"
                          enctype="multipart/form-data">

                        <div class="row g-3">

                            <div class="col-md-6">

                                <label class="form-label">
                                    Equipment Name
                                </label>

                                <input class="form-control"
                                       type="text"
                                       name="equipmentName"
                                       required>

                            </div>

                            <div class="col-md-6">

                                <label class="form-label">
                                    Brand / Model
                                </label>

                                <input class="form-control"
                                       type="text"
                                       name="brandModel">

                            </div>

                            <div class="col-md-6">

                                <label class="form-label">
                                    Total Quantity
                                </label>

                                <input class="form-control"
                                       type="number"
                                       name="totalQuantity"
                                       min="1"
                                       required>

                            </div>

                            <div class="col-12">

                                <label class="form-label">
                                    Equipment Image
                                </label>

                                <input class="form-control"
                                       type="file"
                                       name="equipmentImage"
                                       accept="image/*"
                                       required>

                            </div>

                        </div>

                        <div class="d-flex justify-content-end gap-2 mt-4">

                            <a class="btn btn-outline-secondary"
                               href="<%= request.getContextPath() %>/ViewEquipmentServlet">

                                Cancel

                            </a>

                            <button class="btn btn-admin-primary"
                                    type="submit">

                                <i class="fa-solid fa-plus me-2"></i>

                                Add Equipment

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>