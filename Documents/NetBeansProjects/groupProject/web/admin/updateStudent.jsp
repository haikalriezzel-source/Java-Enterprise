<%@page import="java.sql.*"%>
<%@page import="util.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String studentID = request.getParameter("id");

    Connection conn = DBConnection.getConnection();

    String sql = "SELECT * FROM Student WHERE studentID=?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, studentID);

    ResultSet rs = ps.executeQuery();
    rs.next();
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Student</title>

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
            <h1 class="admin-page-title">Update Student</h1>
            <p class="admin-page-subtitle">
                Edit student profile and programme information.
            </p>
        </div>

        <a href="<%= request.getContextPath()%>/ViewStudentServlet"
           class="btn btn-admin-soft">
            <i class="fa-solid fa-arrow-left me-2"></i>
            Back
        </a>

    </div>

    <!-- FORM -->
    <div class="row justify-content-center">

        <div class="col-12 col-lg-8">

            <div class="admin-card card">

                <div class="card-header">
                    <i class="fa-solid fa-user-graduate me-2 text-primary"></i>
                    Student Details
                </div>

                <div class="card-body p-4">

                    <form action="<%= request.getContextPath()%>/UpdateStudentServlet"
                          method="post">

                        <input type="hidden"
                               name="studentID"
                               value="<%= rs.getString("studentID") %>">

                        <div class="row g-3">

                            <div class="col-md-6">
                                <label class="form-label">Student Name</label>
                                <input class="form-control"
                                       type="text"
                                       name="studentName"
                                       value="<%= rs.getString("studentName") %>"
                                       required>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Date of Birth</label>
                                <input class="form-control"
                                       type="date"
                                       name="studentDOB"
                                       value="<%= rs.getDate("studentDOB") %>"
                                       required>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Programme</label>
                                <input class="form-control"
                                       type="text"
                                       name="programme"
                                       value="<%= rs.getString("programme") %>"
                                       required>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Phone Number</label>
                                <input class="form-control"
                                       type="text"
                                       name="phoneNumber"
                                       value="<%= rs.getString("phoneNumber") %>"
                                       required>
                            </div>

                        </div>

                        <!-- ACTIONS -->
                        <div class="d-flex justify-content-end gap-2 mt-4">

                            <a class="btn btn-outline-secondary"
                               href="<%= request.getContextPath()%>/ViewStudentServlet">
                                Cancel
                            </a>

                            <button class="btn btn-admin-primary" type="submit">
                                <i class="fa-solid fa-floppy-disk me-2"></i>
                                Update Student
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

<%
    conn.close();
%>