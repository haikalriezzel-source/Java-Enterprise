<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String uri=request.getRequestURI(); String ctx=request.getContextPath(); %>
<aside class="student-sidebar" id="studentSidebar">
    <div class="student-brand"><div class="student-brand-icon"><i class="fa-solid fa-graduation-cap"></i></div><div><div class="student-brand-title">Student Portal</div><div class="student-brand-subtitle">Facility & Equipment</div></div></div>
    <nav class="student-nav">
        <div class="student-nav-label">Main</div>
        <a class="student-nav-link <%= uri.contains("studentDashboard") || uri.contains("studentDashboard") ? "active" : "" %>" href="<%=ctx%>/studentDashboard"><i class="fa-solid fa-chart-pie"></i><span>Student Dashboard</span></a>
        <a class="student-nav-link <%= uri.contains("facilityList") || uri.contains("bookingFacility") ? "active" : "" %>" href="<%=ctx%>/facilityList"><i class="fa-solid fa-calendar-check"></i><span>Facility Booking</span></a>
        <a class="student-nav-link <%= uri.contains("equipmentList") || uri.contains("borrowEquipment") ? "active" : "" %>" href="<%=ctx%>/equipmentList"><i class="fa-solid fa-dumbbell"></i><span>Borrow Equipment</span></a>
        <a class="student-nav-link <%= uri.contains("myFacilityBooking") || uri.contains("MyFacilityBookingServlet") ? "active" : "" %>" href="<%=ctx%>/MyFacilityBookingServlet"><i class="fa-solid fa-bookmark"></i><span>My Booking</span></a>
        <a class="student-nav-link <%= uri.contains("myLoan") || uri.contains("MyLoanServlet") ? "active" : "" %>" href="<%=ctx%>/MyLoanServlet"><i class="fa-solid fa-handshake"></i><span>My Loan</span></a>
        <div class="student-nav-label">Account</div>
        <a class="student-nav-link <%= uri.contains("editStudentProfile") ? "active" : "" %>" href="<%=ctx%>/EditStudentProfileServlet"><i class="fa-regular fa-user"></i><span>Profile Settings</span></a>
        <a class="student-nav-link" href="<%=ctx%>/LogoutServlet" onclick="return confirm('Are you sure you want to logout?');"><i class="fa-solid fa-right-from-bracket"></i><span>Logout</span></a>
    </nav>
</aside>
<div class="student-overlay" id="studentOverlay"></div>
