<%-- 
    Document   : addPhone
    Created on : May 31, 2026, 11:01:36 AM
    Author     : Admin
--%>

<%@page import="sample.dto.MobileError"%>
<%@page import="sample.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Phone Page</title>
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
            MobileError me = (MobileError) request.getAttribute("MOBILE_ERROR"); //MOBILE_ERROR
            if(me == null){
                me = new MobileError();
            }
        %>
        
        <h1>Thêm thông tin Mobile</h1>
        <form action="MainController" method="POST">
            ID: <input type="text" name="txtID"> <%= me.getMobileIdError() %><br>
            Description: <input type="text" name="txtDescription" > <br>
            Price: <input type="text" name="txtPrice" > <%= me.getPriceError() %><br>
            Name: <input type="text" name="txtName"> <%= me.getNameError() %><br> 
            Year of production: <input type="number" name="txtYear"> <br>
            Quantity: <input type="number" name="txtQuantity"> <%= me.getQuantityError()%> <br>
            <input type="checkbox" name="chkNotSale" value="true" />notSale<br/>
            <button type="submit" name="action" value="Add">Thêm</button>
            <input type="reset" value="Reset">
        </form>
            
    </body>
</html>
