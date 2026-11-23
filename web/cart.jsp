<%-- 
    Document   : cart
    Created on : Jun 21, 2026, 6:27:03 PM
    Author     : Owner
--%>

<%@page import="java.util.HashMap"%>
<%@page import="sample.dto.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HashMap<String, CartDTO> shopCart = (HashMap<String, CartDTO>) session.getAttribute("CART");
            if (shopCart == null || shopCart.isEmpty()) {%>
        Gio hang cua ban dang trong

        <%} else {%>
        <table border="1">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>TITTLE</th>
                    <th>UNIT</th>
                    <th>PRICE</th>
                    <th>TOTAL</th>

                </tr>
            </thead>
            <tbody>

                <%
                    double totalCart = 0;
                    int totalUnit = 0;
                    int count = 1;
                    for (CartDTO c : shopCart.values()) {
                        totalCart = totalCart + c.totalPrice();
                        totalUnit = totalUnit + c.getUnit();
                %>
            <form action="MainController" method="POST">
                <tr >
                    <td><%= count++%></td>
                    <!--                    <td> <input type="text" name="txtID" value="" readonly=""/></td>-->
                    <td><<%= c.getTitle()%>/>  </td>
                    <td><input type="number" name="txtUnit" value="<%= c.getUnit()%>"></td>
                    <td><%= String.format("%.2f", c.getPrice())%></td>
                    <td><%= String.format("%.2f", c.totalPrice())%></td>
                    <td> 
                        <button type="submit" name="action" value="DeleteCart">Xóa</button>
                        <input type="hidden" name="idCartDelete" value="<%= c.getIdMobile() %>"/>
                    </td>
                    <td> 
                        <button type="submit" name="action" value="UpdateCart">Sua</button>
                        <input type="hidden" name="idCartUpdate" value="<%= c.getIdMobile() %>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>

            <td> 
                <button type="submit" name="action" value="SaveCart">Luu gio hang</button>
            </td>
        </tbody>
    </table>
    Total amount :<%= String.format("%.2f", totalCart)%>
    <%}
    %>


</body>
</html>
