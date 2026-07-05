<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>

<%
    request.setAttribute("pageTitle", "Borrow Equipment");
    request.setAttribute("pageSubtitle", "Submit a new equipment loan request.");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Equipment</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/student.css" rel="stylesheet">
</head>

<body class="student-ui-body">

<div class="student-shell">

    <!-- SIDEBAR -->
    <jsp:include page="layout/sidebar.jsp"/>

    <div class="student-main">

        <!-- TOPBAR -->
        <jsp:include page="layout/topbar.jsp"/>

        <main class="student-content">

            <div class="row justify-content-center">
                <div class="col-lg-7">

                    <!-- ERROR HANDLING -->
                    <%
                        String error = request.getParameter("error");

                        if (error != null) {
                            String msg = "";

                            if (error.equals("pastdate")) {
                                msg = "Borrow date cannot be earlier than today.";
                            } else if (error.equals("returndate")) {
                                msg = "Return date cannot be earlier than borrow date.";
                            } else if (error.equals("quantity")) {
                                msg = "Quantity must be greater than 0.";
                            } else if (error.equals("stock")) {
                                msg = "Requested quantity exceeds available stock.";
                            } else if (error.equals("database")) {
                                msg = "Database error occurred.";
                            }

                            if (!msg.equals("")) {
                    %>

                        <div class="alert alert-danger rounded-4">
                            <i class="fa-solid fa-circle-exclamation me-2"></i>
                            <%= msg %>
                        </div>

                    <%
                            }
                        }
                    %>

                    <!-- FORM CARD -->
                    <div class="student-card card">

                        <div class="card-header">
                            <i class="fa-solid fa-dumbbell me-2 text-primary"></i>
                            Loan Details
                        </div>

                        <div class="card-body p-4">

                            <form action="${pageContext.request.contextPath}/BorrowEquipmentServlet"
                                  method="post">

                                <input type="hidden"
                                       name="equipmentID"
                                       value="${equipmentID}">

                                <div class="row g-3">

                                    <div class="col-md-4">
                                        <label class="form-label">Quantity</label>
                                        <input class="form-control"
                                               type="number"
                                               name="loanQuantity"
                                               min="1"
                                               required>
                                    </div>

                                    <div class="col-md-4">
                                        <label class="form-label">Start Date</label>
                                        <input class="form-control"
                                               type="date"
                                               name="startDate"
                                               min="<%= LocalDate.now() %>"
                                               required>
                                    </div>

                                    <div class="col-md-4">
                                        <label class="form-label">End Date</label>
                                        <input class="form-control"
                                               type="date"
                                               name="endDate"
                                               min="<%= LocalDate.now() %>"
                                               required>
                                    </div>

                                </div>

                                <!-- ACTIONS -->
                                <div class="d-flex justify-content-end gap-2 mt-4">

                                    <a class="btn btn-student-soft"
                                       href="<%= request.getContextPath() %>/equipmentList">
                                        Back To Equipment List
                                    </a>

                                    <input class="btn btn-student-primary"
                                           type="submit"
                                           value="Submit Loan">

                                </div>

                            </form>

                        </div>

                    </div>

                </div>
            </div>

        </main>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>