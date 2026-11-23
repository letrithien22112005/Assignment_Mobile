<%-- 
    Document   : user.jsp
    Created on : Jul 2, 2026, 9:54:51 PM
    Author     : Owner
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="sample.dto.MobileDTO"%>
<%@page import="sample.dto.MobileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>
            .price-filter-box {
                width: 420px;
                background: #fff;
                padding: 16px 18px;
                border-radius: 8px;
                box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
                font-family: Arial, sans-serif;
            }

            .price-title {
                display: block;
                font-size: 15px;
                font-weight: 600;
                color: #555;
                margin-bottom: 14px;
            }

            .price-input-row {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 20px;
            }

            .price-input-row input {
                width: 100%;
                height: 38px;
                border: 1px solid #d1d5db;
                border-radius: 6px;
                text-align: right;
                padding: 0 12px;
                font-size: 15px;
                color: #222;
                background: #fff;
                outline: none;
            }

            .price-input-row span {
                font-size: 18px;
                color: #333;
            }

            .range-slider {
                position: relative;
                height: 28px;
                margin-bottom: 24px;
            }

            .slider-track {
                position: absolute;
                top: 11px;
                left: 0;
                right: 0;
                height: 6px;
                border-radius: 10px;
                background: #e60012;
            }

            .range-slider input[type="range"] {
                position: absolute;
                width: 100%;
                height: 6px;
                top: 7px;
                left: 0;
                background: none;
                pointer-events: none;
                appearance: none;
                -webkit-appearance: none;
            }

            .range-slider input[type="range"]::-webkit-slider-thumb {
                width: 24px;
                height: 24px;
                border-radius: 50%;
                background: #fff;
                border: 1px solid #e5e7eb;
                box-shadow: 0 1px 5px rgba(0, 0, 0, 0.2);
                cursor: pointer;
                pointer-events: auto;
                appearance: none;
                -webkit-appearance: none;
            }

            .range-slider input[type="range"]::-moz-range-thumb {
                width: 24px;
                height: 24px;
                border-radius: 50%;
                background: #fff;
                border: 1px solid #e5e7eb;
                box-shadow: 0 1px 5px rgba(0, 0, 0, 0.2);
                cursor: pointer;
                pointer-events: auto;
            }

            .btn-result {
                width: 100%;
                height: 44px;
                border: none;
                border-radius: 7px;
                background: #e60012;
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                cursor: pointer;
            }

            .btn-result:hover {
                background: #d00010;
            }
            .btn-result2 {
                width: 25%;
                height: 44px;
                border: none;
                border-radius: 7px;
                background: green;
                color: #fff;
                font-size: 15px;
                font-weight: 700;
                cursor: pointer;
            }

            .btn-result2:hover {
                background: #d00010;
            }
        </style>
    </head>
    <body>

        <form action="MainController" method="POST">
            <button type="submit" name="action" value="ViewCart" class="btn-result2">Gio Hang</button>
        </form>

        <form action="MainController" method="POST" class="price-filter-box">
            <label class="price-title">Hãy chọn mức giá phù hợp với bạn</label>

            <div class="price-input-row">
                <input type="text" name="minPriceText" value="0" >
                <span>-</span>
                <input type="text" name="maxPriceText" value="1000000" >
            </div>

            <button type="submit" name="action" value="Search" class="btn-result">Xem kết quả</button>
        </form>

        <%
            String thongBao = (String) request.getAttribute("THONGBAO");
            if (thongBao == null) {
                thongBao = "";
            }
        %>


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
                    <th>ADD TO CART</th>
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
                    <td> 
                        <button type="submit" name="action" value="AddCart">Add to Cart</button>
                        <input type="hidden" name="idCart" value="<%= p.getMobileId()%>"/>
                    </td>
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

    <%= thongBao%>

    <form action="MainController" method="POST">
        <button type="submit" name="action" value="Logout">Đăng Xuất</button>
    </form>
</body>
</html>
