<%-- 
    Document   : addEquipment
    Created on : Jun 13, 2026, 6:43:36 PM
    Author     : haikalriez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Equipment</title>
    </head>
    <body>

    <h1>Add Sports Equipment</h1>

    <%
        if(request.getParameter("success") != null){
    %>

        <p>
            Equipment added successfully.
        </p>

    <%
        }
    %>

    <form action="../AddEquipmentServlet" method="post" enctype="multipart/form-data">

        <p>
            Equipment Name
        </p>

        <input type="text"
               name="equipmentName"
               required>

        <br><br>

        <p>
            Brand / Model
        </p>

        <input type="text"
               name="brandModel">

        <br><br>

        <p>
            Quantity
        </p>

        <input type="number"
               name="quantity"
               min="0"
               required>

        <br><br>
        
        <p>Equipment Status</p>

        <select name="equipmentStatus">

            <option value="Available">
                Available
            </option>

            <option value="Damaged">
              Damaged
            </option>

            <option value="In-Maintenance">
                In-Maintenance
            </option>

        </select>

        <br><br>

        <p>
            Equipment Image
        </p>

        <input type="file"
               name="equipmentImage"
               accept="image/*"
               required>

        <br><br>

        <button type="submit">
            Add Equipment
        </button>

    </form>

    <br>

    <a href="<%= request.getContextPath() %>/dashboardAdmin">
        Back to Dashboard
    </a>

</body>
</html>
