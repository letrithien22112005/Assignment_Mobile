<%@page import="sample.dto.MobileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    MobileDTO m = (MobileDTO) request.getAttribute("MOBILE");

    if (m == null) {
        m = new MobileDTO();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Delete Car</title>

        <style>
            body{
                font-family: Arial, sans-serif;
                margin: 20px;
            }

            .container{
                width: 700px;
            }

            h1{
                font-size: 50px;
                margin-bottom: 10px;
            }

            h2{
                font-size: 20px;
                margin-bottom: 20px;
            }

            .car-title{
                font-size: 35px;
                margin-bottom: 20px;
            }

            hr{
                border: 0;
                border-top: 1px solid #ddd;
                margin: 20px 0;
            }

            table{
                width: 100%;
                border-collapse: collapse;
            }

            td{
                padding: 10px 0;
            }

            .label{
                font-weight: bold;
                width: 180px;
            }

            .btn-delete{
                background: #dc3545;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
            }

            .btn-delete:hover{
                background: #bb2d3b;
            }

            .back{
                margin-left: 10px;
                text-decoration: none;
                color: #0d6efd;
            }
        </style>
    </head>
    <body>

        <div class="container">

            <h1>Delete</h1>

            <h2>Are you sure you want to delete this?</h2>

            <div class="car-title">Mobiles</div>

            <hr>

            <table>
                <tr>
                    <td class="label">MobileID</td>
                    <td><%= m.getMobileId()%></td>
                </tr>

                <tr>
                    <td class="label">Description</td>
                    <td><%= m.getDescription()%></td>
                </tr>

                <tr>
                    <td class="label">Price</td>
                    <td><%= String.format("%.2f", m.getPrice()) %></td>
                </tr>

                <tr>
                    <td class="label">Name</td>
                    <td><%= m.getFullName()%></td>
                </tr>

                <tr>
                    <td class="label">Year Of Production</td>
                    <td><%= m.getYearOfProduction()%></td>
                </tr>

                <tr>
                    <td class="label">Quantity</td>
                    <td><%= m.getQuantity()%></td>
                </tr>

                <tr>
                    <td class="label">notSale</td>
                    <td><%= m.isNotSale() %></td>
                </tr>
            </table>

            <hr>

            <form action="MainController" method="post">

                <input type="hidden"
                       name="idDelete"
                       value="<%= m.getMobileId()%>">


                <input type="submit"
                       name="action"
                       value="Delete"
                       class="btn-delete">  
                |
                <a href="SearchController" class="back">
                    Back to List
                </a>

            </form>

        </div>

    </body>
</html>
