<%-- 
    Document   : updatePhone.jsp
    Created on : May 26, 2026, 10:18:46 AM
    Author     : Admin
--%>

<%@page import="sample.dto.MobileDTO"%>
<%@page import="sample.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update phone</title>
    </head>
    <body>
        
        <%
            UserDTO u = (UserDTO) session.getAttribute("USER_LOGIN");  //null
            if (u == null) {
                u = new UserDTO();
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <%
            MobileDTO m = (MobileDTO) request.getAttribute("MOBILE");
            if (m == null) {
                m = new MobileDTO();
            }
        %>
        
        <% 
            String err = (String) request.getAttribute("UPDATE_MESSAGE");
            if(err == null){
                err = "";
            }
        %>

        <h1>Cập nhật thông tin Phone</h1>
        <form action="MainController" method="POST">
            ID: <input type="text" name="txtID" readonly="" value="<%= m.getMobileId() %>"> <br>
            Description: <input type="text" name="txtDescription" value="<%= m.getDescription() %>"  required="" >  <br>
            Price: <input type="text" name="txtPrice" value="<%= String.format("%.2f", m.getPrice()) %>"  required="" > <br> 
            Name: <input type="text" name="txtName" readonly="" value="<%= m.getFullName() %>" > <br>
            Year Of Production: <input type="number" readonly="" name="txtYearOfProduction" value="<%= m.getYearOfProduction() %>"> <br>
            Quantity: <input type="number" name="txtQuantity" value="<%= m.getQuantity()%>"  required=""> <br>
            <input type="checkbox" name="chkNotSale" value="true" <%= m.isNotSale() ? "checked" : "" %> />notSale<br/>
            <button type="submit" name="action" value="Update">Sửa</button>
            <input type="reset" value="Reset">
        </form>

            <%= err %>
    </body>
</html>
