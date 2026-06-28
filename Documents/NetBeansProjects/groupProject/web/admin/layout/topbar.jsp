<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg admin-topbar">
    <div class="container-fluid px-3 px-lg-4">
        <a class="navbar-brand d-flex align-items-center gap-2" href="<%=request.getContextPath()%>/dashboardAdmin">
            <span class="admin-brand-badge"><i class="fa-solid fa-dumbbell"></i></span>
            <span>LifeFitness Admin</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminTopNav" aria-controls="adminTopNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="adminTopNav">
            <ul class="navbar-nav mx-auto mb-2 mb-lg-0 gap-lg-1">
                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dashboardAdmin"><i class="fa-solid fa-chart-pie me-1"></i> Dashboard</a></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><i class="fa-solid fa-building me-1"></i> Facility</a><ul class="dropdown-menu"><li><a class="dropdown-item" href="<%=request.getContextPath()%>/ViewFacilityServlet"><i class="fa-solid fa-list"></i> Manage Facility</a></li><li><a class="dropdown-item" href="<%=request.getContextPath()%>/admin/addFacility.jsp"><i class="fa-solid fa-plus"></i> Add Facility</a></li><li><hr class="dropdown-divider"></li><li><a class="dropdown-item" href="<%=request.getContextPath()%>/AdminFacilityListServlet"><i class="fa-solid fa-calendar-check"></i> Booking Approval</a></li></ul></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><i class="fa-solid fa-dumbbell me-1"></i> Equipment</a><ul class="dropdown-menu"><li><a class="dropdown-item" href="<%=request.getContextPath()%>/ViewEquipmentServlet"><i class="fa-solid fa-list-check"></i> Manage Equipment</a></li><li><a class="dropdown-item" href="<%=request.getContextPath()%>/admin/addEquipment.jsp"><i class="fa-solid fa-plus"></i> Add Equipment</a></li></ul></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><i class="fa-solid fa-user-graduate me-1"></i> Student</a><ul class="dropdown-menu"><li><a class="dropdown-item" href="<%=request.getContextPath()%>/ViewStudentServlet"><i class="fa-solid fa-user-gear"></i> Manage Students</a></li></ul></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><i class="fa-solid fa-clipboard-list me-1"></i> Loan / Booking</a><ul class="dropdown-menu"><li><a class="dropdown-item" href="<%=request.getContextPath()%>/AdminFacilityListServlet"><i class="fa-solid fa-calendar-check"></i> Facility Booking</a></li><li><a class="dropdown-item" href="<%=request.getContextPath()%>/AdminLoanListServlet"><i class="fa-solid fa-handshake"></i> Equipment Loan</a></li><li><a class="dropdown-item" href="<%=request.getContextPath()%>/AdminReturnListServlet"><i class="fa-solid fa-rotate-left"></i> Return Verification</a></li></ul></li>
            </ul>
            <div class="d-flex align-items-center gap-2">
                <a class="btn btn-admin-soft btn-sm" href="<%=request.getContextPath()%>/EditAdminProfileServlet"><i class="fa-regular fa-user me-1"></i> Profile</a>
                <a class="btn btn-outline-danger btn-sm" href="<%=request.getContextPath()%>/LogoutServlet" onclick="return confirm('Are you sure you want to logout?');"><i class="fa-solid fa-right-from-bracket me-1"></i> Logout</a>
            </div>
        </div>
    </div>
</nav>
