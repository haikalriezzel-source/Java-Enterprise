<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/admin.css" rel="stylesheet">
</head>
<body class="admin-ui-body">

<main class="min-vh-100 d-flex align-items-center justify-content-center py-5 px-3">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-8 col-xl-7">
                <div class="text-center mb-4">
                    <div class="admin-brand-badge mx-auto mb-3">
                        <i class="fa-solid fa-user-plus"></i>
                    </div>
                    <h1 class="admin-page-title">Student Registration</h1>
                    <p class="admin-page-subtitle">Create your student account to access equipment and facility rental services.</p>
                </div>

                <div class="admin-card card">
                    <div class="card-body p-4 p-lg-5">
                        <%
                        String error = request.getParameter("error");

                        if(error != null){
                            String errorMessage = "";

                            if(error.equals("empty")){
                                errorMessage = "Please fill in all required fields.";
                            } else if(error.equals("duplicate")){
                                errorMessage = "Student ID already exists.";
                            } else if(error.equals("date")){
                                errorMessage = "Invalid date format.";
                            } else if(error.equals("database")){
                                errorMessage = "Database connection error.";
                            } else if(error.equals("system")){
                                errorMessage = "Unexpected system error occurred.";
                            }

                            if(!errorMessage.equals("")){
                        %>
                        <div class="alert alert-danger rounded-4" role="alert">
                            <i class="fa-solid fa-circle-exclamation me-2"></i><%= errorMessage %>
                        </div>
                        <%
                            }
                        }
                        %>

                        <form action="RegisterServlet" method="post">
                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label class="form-label">Student ID</label>
                                    <input class="form-control" type="text" name="studentID" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Full Name</label>
                                    <input class="form-control" type="text" name="studentName" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Date of Birth</label>
                                    <input class="form-control" type="date" name="studentDOB" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Programme</label>
                                    <input class="form-control" type="text" name="programme" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Phone Number</label>
                                    <input class="form-control" type="text" name="phoneNumber" required>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Password</label>
                                    <input class="form-control" type="password" name="password" required>
                                </div>
                            </div>

                            <div class="d-grid gap-2 mt-4">
                                <button class="btn btn-admin-primary" type="submit">
                                    <i class="fa-solid fa-user-plus me-2"></i>Register
                                </button>
                                <a href="login.jsp" class="btn btn-admin-soft">
                                    <i class="fa-solid fa-arrow-left me-2"></i>Back to Login
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
