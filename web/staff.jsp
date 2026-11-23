<%-- 
    Document   : staff
    Created on : Jun 30, 2026, 10:42:39 AM
    Author     : Owner
--%>

<%@page import="sample.dto.MobileDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <%
            UserDTO u = (UserDTO) session.getAttribute("USER_LOGIN");
            if (u == null) {
                u = new UserDTO();
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        Welcome to: <%= u.getFullName()%> <br>

        <%
            String key = (String) request.getAttribute("KEYWORD");
            if (key == null) {
                key = "";
            }
        %>

        <%
            String thongBao = (String) request.getAttribute("THONGBAO");
            if (thongBao == null) {
                thongBao = "";
            }
        %>
        <a href="addMobile.jsp">Insert new mobile </a>

        <form action="MainController" method="POST">
            <input type="text" name="txtSearch" placeholder="Nhập ID hoặc Name" value="<%=key%>" />  
            <button type="submit" name="action" value="Search">Tìm kiếm</button>
        </form>

        <%
            ArrayList<MobileDTO> list = (ArrayList<MobileDTO>) request.getAttribute("LIST");
            if (list != null) { %>
        <table border="1">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>DESCRIPTION</th>
                    <th>PRICE</th>
                    <th>YEAR</th>
                    <th>QUANTITY</th>
                    <th>NOT SALE</th>
                    <th>DELETE</th>
                    <th>UPDATE</th>
                </tr>
            </thead>
            <tbody>

                <%
                    int count = 1;
                    for (MobileDTO p : list) {%>
            <form action="MainController" method="POST">
                <tr >
                    <td><%= count++%></td>
                    <td><%= p.getMobileId()%></td>
                    <td><%= p.getFullName()%></td>
                    <td><%= p.getDescription()%></td>
                    <td><%= String.format("%.2f", p.getPrice())%></td>
                    <td><%= p.getYearOfProduction()%></td>
                    <td><%= p.getQuantity()%></td>
                    <td><%= p.isNotSale()%></td>
                <form action="MainController" method="POST">
                    <td> 
                        <button type="submit" name="action" value="Detail">Delete</button>
                        <input type="hidden" name="id" value="<%= p.getMobileId()%>"/>
                        <input type="hidden" name="view" value="D"/>
                    </td>
                </form>
                <form action="MainController" method="POST">
                    <td>
                        <button type="submit" name="action" value="Detail">Update</button>
                        <input type="hidden" name="id" value="<%= p.getMobileId()%>"/>
                    </td>
                </form>
                </tr>
            </form>
            <%
                }
            %>

        </tbody>
    </table>

    <%
        }
    %>

    <%
        String error = (String) request.getAttribute("LIST_EMPTY");
        if (error == null) {
            error = "";
        }
    %>
    <%=  error%>



    <form action="MainController" method="POST">
        <button type="submit" name="action" value="Logout">Đăng Xuất</button>
    </form>
</body>
</html>
