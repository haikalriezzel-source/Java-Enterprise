<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/css/admin.css" rel="stylesheet">
</head>
<body class="admin-ui-body">
<jsp:include page="layout/topbar.jsp" />

<main class="admin-ui-main">
    <div class="admin-page-header">
        <div>
            <h1 class="admin-page-title">Admin Dashboard</h1>
            <p class="admin-page-subtitle">Welcome, <strong><%= session.getAttribute("adminName") %></strong>. Monitor facilities, equipment, students and approvals.</p>
        </div>
        <a href="<%= request.getContextPath() %>/EditAdminProfileServlet" class="btn btn-admin-primary">
            <i class="fa-regular fa-user me-2"></i>Edit Profile
        </a>
    </div>

    <div class="row g-3 mb-4">
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Total Students</div><h3 class="fw-black mb-0"><%= request.getAttribute("totalStudents") %></h3></div><div class="admin-stat-icon" style="background:#dbeafe;color:var(--admin-primary-dark);"><i class="fa-solid fa-user-graduate"></i></div></div></div></div>
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Total Facilities</div><h3 class="fw-black mb-0"><%= request.getAttribute("totalFacilities") %></h3></div><div class="admin-stat-icon" style="background:#ccfbf1;color:var(--admin-teal);"><i class="fa-solid fa-building"></i></div></div></div></div>
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Total Equipment</div><h3 class="fw-black mb-0"><%= request.getAttribute("totalEquipment") %></h3></div><div class="admin-stat-icon" style="background:#ffedd5;color:var(--admin-orange);"><i class="fa-solid fa-dumbbell"></i></div></div></div></div>
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Pending Bookings</div><h3 class="fw-black mb-0"><%= request.getAttribute("pendingBookings") %></h3></div><div class="admin-stat-icon" style="background:#fef3c7;color:#92400e;"><i class="fa-solid fa-calendar-check"></i></div></div></div></div>
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Pending Loans</div><h3 class="fw-black mb-0"><%= request.getAttribute("pendingLoans") %></h3></div><div class="admin-stat-icon" style="background:#ede9fe;color:#7c3aed;"><i class="fa-solid fa-handshake"></i></div></div></div></div>
        <div class="col-12 col-md-6 col-xl-4"><div class="admin-card admin-stat-card card"><div class="card-body d-flex justify-content-between align-items-start"><div><div class="admin-muted small fw-bold">Pending Returns</div><h3 class="fw-black mb-0"><%= request.getAttribute("pendingReturns") %></h3></div><div class="admin-stat-icon" style="background:#fee2e2;color:#dc2626;"><i class="fa-solid fa-rotate-left"></i></div></div></div></div>
    </div>

    <div class="row g-3 mb-4">
        <div class="col-12 col-lg-4"><div class="admin-card card h-100"><div class="card-header"><i class="fa-solid fa-chart-line me-2 text-primary"></i>Peak Usage Report</div><div class="card-body"><p class="admin-muted mb-1">Most Popular Facility</p><h5 class="fw-bold"><%= request.getAttribute("facilityName") %></h5><hr><p class="mb-1"><strong>Peak Hour:</strong> <%= request.getAttribute("peakHour") %></p><p class="mb-0"><strong>Approved Bookings:</strong> <%= request.getAttribute("totalApprovedBookings") %></p></div></div></div>
        <div class="col-12 col-lg-4"><div class="admin-card card h-100"><div class="card-header"><i class="fa-solid fa-heart-pulse me-2 text-success"></i>Inventory Health</div><div class="card-body"><p><span class="badge badge-admin-success rounded-pill">Available</span> <%= request.getAttribute("availableEquipment") %></p><p><span class="badge badge-admin-danger rounded-pill">Damaged</span> <%= request.getAttribute("damagedEquipment") %></p><p class="mb-0"><span class="badge badge-admin-warning rounded-pill">In-Maintenance</span> <%= request.getAttribute("maintenanceEquipment") %></p></div></div></div>
        <div class="col-12 col-lg-4"><div class="admin-card card h-100"><div class="card-header"><i class="fa-solid fa-triangle-exclamation me-2 text-warning"></i>Low Stock Report</div><div class="card-body"><p class="admin-muted">Equipment with quantity ≤ 3</p><h2 class="fw-bold mb-0"><%= request.getAttribute("lowStockCount") %></h2></div></div></div>
    </div>

    <h3 class="admin-page-title fs-4 mb-3">Quick Actions</h3>
    <div class="row g-3">
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/ViewFacilityServlet"><i class="fa-solid fa-building me-2 text-primary"></i><strong>Manage Facility</strong><p class="admin-muted mb-0 mt-2">View, update and delete facilities.</p></a></div>
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/ViewEquipmentServlet"><i class="fa-solid fa-dumbbell me-2 text-primary"></i><strong>Manage Equipment</strong><p class="admin-muted mb-0 mt-2">Maintain equipment inventory.</p></a></div>
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/ViewStudentServlet"><i class="fa-solid fa-user-graduate me-2 text-primary"></i><strong>Manage Students</strong><p class="admin-muted mb-0 mt-2">Search and update student records.</p></a></div>
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/AdminFacilityListServlet"><i class="fa-solid fa-calendar-check me-2 text-primary"></i><strong>Facility Booking</strong><p class="admin-muted mb-0 mt-2">Approve or reject bookings.</p></a></div>
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/AdminLoanListServlet"><i class="fa-solid fa-handshake me-2 text-primary"></i><strong>Equipment Loan</strong><p class="admin-muted mb-0 mt-2">Review loan applications.</p></a></div>
        <div class="col-12 col-md-6 col-xl-4"><a class="admin-dashboard-link" href="<%= request.getContextPath() %>/AdminReturnListServlet"><i class="fa-solid fa-rotate-left me-2 text-primary"></i><strong>Return Verification</strong><p class="admin-muted mb-0 mt-2">Verify returned equipment.</p></a></div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
