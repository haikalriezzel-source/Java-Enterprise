<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.student"%>

<%
    ArrayList<student> studentList =
        (ArrayList<student>) request.getAttribute("studentList");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Students</title>

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
            <h1 class="admin-page-title">Manage Students</h1>
            <p class="admin-page-subtitle">
                Search and update student profile records.
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

        <form action="<%=request.getContextPath()%>/ViewStudentServlet" method="get">

            <div class="row g-2">

                <div class="col-md-9">
                    <input class="form-control"
                           type="text"
                           name="keyword"
                           placeholder="Search student..."
                           value="<%= request.getAttribute("keyword")==null ? "" : request.getAttribute("keyword") %>">
                </div>

                <div class="col-md-3 d-flex gap-2">
                    <input class="btn btn-admin-primary flex-fill" type="submit" value="Search">

                    <a class="btn btn-admin-soft"
                       href="<%=request.getContextPath()%>/ViewStudentServlet">
                        Reset
                    </a>
                </div>

            </div>

        </form>

    </div>

    <!-- TABLE -->
    <div class="admin-card card">

        <div class="card-header">
            <i class="fa-solid fa-user-graduate me-2 text-primary"></i>
            Student Records
        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-admin table-hover align-middle">

                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Date of Birth</th>
                            <th>Programme</th>
                            <th>Phone Number</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>

                        <%
                            if (studentList != null) {

                                if (studentList.isEmpty()) {
                        %>

                            <tr>
                                <td colspan="6">
                                    <div class="admin-empty">
                                        <i class="fa-regular fa-folder-open"></i>
                                        <div>No student records found.</div>
                                    </div>
                                </td>
                            </tr>

                        <%
                                }

                                for (student s : studentList) {
                        %>

                            <tr>

                                <td>
                                    <span class="badge badge-admin-neutral rounded-pill">
                                        <%= s.getStudentID() %>
                                    </span>
                                </td>

                                <td class="fw-bold">
                                    <%= s.getStudentName() %>
                                </td>

                                <td><%= s.getStudentDOB() %></td>

                                <td><%= s.getProgramme() %></td>

                                <td><%= s.getPhoneNumber() %></td>

                                <td>
                                    <a class="btn btn-sm btn-admin-soft"
                                       href="<%=request.getContextPath()%>/admin/updateStudent.jsp?id=<%=s.getStudentID()%>">
                                        <i class="fa-solid fa-pen"></i>
                                        Update
                                    </a>
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