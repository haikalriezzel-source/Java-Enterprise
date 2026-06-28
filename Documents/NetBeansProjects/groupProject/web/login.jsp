<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sports Equipment and Facility Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/admin.css" rel="stylesheet">
</head>
<body class="admin-ui-body">

<main class="min-vh-100 d-flex align-items-center justify-content-center py-5 px-3">
    <div class="container">
        <div class="row justify-content-center align-items-center g-4">
            <div class="col-12 col-lg-5">
                <div class="mb-4 text-center text-lg-start">
                    <div class="admin-brand-badge mb-3 mx-auto mx-lg-0">
                        <i class="fa-solid fa-dumbbell"></i>
                    </div>
                    <h1 class="admin-page-title">Sports Equipment &amp; Facility Rental</h1>
                    <p class="admin-page-subtitle">
                        Access your dashboard to manage facilities, equipment loans, returns, and student services.
                    </p>
                </div>
            </div>

            <div class="col-12 col-md-8 col-lg-5">
                <div class="admin-card card">
                    <div class="card-body p-4 p-lg-5">
                        <div class="text-center mb-4">
                            <h2 class="fw-bold mb-1">Welcome Back</h2>
                            <p class="admin-muted mb-0">Sign in to continue</p>
                        </div>

                        <%
                        String error = request.getParameter("error");

                        if(error != null){
                            String errorMessage = "";

                            if(error.equals("empty")){
                                errorMessage = "Please fill in all required fields.";
                            } else if(error.equals("invalid")){
                                errorMessage = "Invalid User ID or Password.";
                            } else if(error.equals("wrongrole")){
                                errorMessage = "Incorrect user type selected. Please select the correct role.";
                            } else if(error.equals("driver")){
                                errorMessage = "Database driver not found.";
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

                        <form action="loginServlet" method="post">
                            <div class="mb-3">
                                <label class="form-label">User Type</label>
                                <select class="form-select" name="userType" required>
                                    <option value="">-- Select User Type --</option>
                                    <option value="student">Student</option>
                                    <option value="admin">Admin</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">User ID</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fa-regular fa-id-card"></i></span>
                                    <input class="form-control" type="text" name="userID" placeholder="Enter your user ID">
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Password</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                                    <input class="form-control" type="password" name="password" placeholder="Enter your password">
                                </div>
                            </div>

                            <button class="btn btn-admin-primary w-100" type="submit">
                                <i class="fa-solid fa-right-to-bracket me-2"></i>Login
                            </button>
                        </form>

                        <div class="text-center mt-4">
                            <span class="admin-muted">New student?</span>
                            <a href="register.jsp" class="fw-bold">Register Here</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
