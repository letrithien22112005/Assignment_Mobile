<%-- 
    Document   : login
    Created on : Mar 23, 2026, 8:23:09 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .e{
                color:red;
            }
            .login-container {
                background: #fff;
                padding: 40px;
                width: 100%;
                max-width: 450px;
                text-align: center;
            }

            .title {
                color: #d70018;
                font-size: 24px;
                margin-bottom: 30px;
                text-transform: uppercase;
            }

            /* Cấu trúc Form */
            .input-group {
                text-align: left;
                margin-bottom: 20px;
            }

            .input-group label {
                display: block;
                font-size: 14px;
                font-weight: 500;
                margin-bottom: 8px;
            }

            .input-group input {
                width: 100%;
                padding: 12px 15px;
                border: 1px solid #ddd;
                border-radius: 8px;
                outline: none;
            }

            .password-wrapper {
                position: relative;
            }

            .eye-icon {
                position: absolute;
                right: 15px;
                top: 50%;
                transform: translateY(-50%);
                color: #777;
                cursor: pointer;
            }

            /* Nút bấm chính */
            .btn-login {
                width: 100%;
                padding: 12px;
                background-color: #d70018;
                color: white;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                margin-top: 10px;
            }

            .forgot-password {
                display: block;
                margin-top: 15px;
                color: #4a90e2;
                text-decoration: none;
                font-size: 14px;
            }

            /* Đường kẻ ngăn cách */
            .divider {
                margin: 30px 0;
                position: relative;
                border-top: 1px solid #eee;
            }

            .divider span {
                position: absolute;
                top: -10px;
                left: 50%;
                transform: translateX(-50%);
                background: white;
                padding: 0 15px;
                color: #777;
                font-size: 13px;
            }

            /* Đăng nhập mạng xã hội */
            .social-login {
                display: flex;
                gap: 15px;
                margin-bottom: 30px;
            }

            .social-btn {
                flex: 1;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 10px;
                border: 1px solid #eee;
                border-radius: 8px;
                background: white;
                cursor: pointer;
                font-weight: 500;
            }

            .social-btn img {
                width: 20px;
                margin-right: 10px;
            }

            /* Footer */
            .signup-text {
                font-size: 14px;
            }

            .signup-text a {
                color: #d70018;
                text-decoration: none;
                font-weight: bold;
            }

            .footer-links {
                margin-top: 40px;
                font-size: 13px;
                color: #555;
            }

            .red-link {
                color: #d70018;
                text-decoration: none;
                font-weight: 500;
            }
        </style>
    </head>
    <body>
    <body>

        <% 
            String x = (String) request.getAttribute("ERORLOGIN");
            if(x == null){
                x = "";
            }
        %>
                
        
        <div class="login-container">
            <h1 class="title">Đăng nhập MOBIES</h1>
            <form action="MainController" method="POST">
                <div class="input-group">
                    <label>Tài khoản</label>
                    <input type="text" placeholder="Nhập tài khoản của bạn" name="txtID"  required="">
                </div>

                <div class="input-group">
                    <label>Mật khẩu</label>
                    <div class="password-wrapper">
                        <input type="password" placeholder="Nhập mật khẩu của bạn" name="txtPassword"  required="">
                        <i class="fa-regular fa-eye eye-icon"></i>
                    </div>
                </div>

                <button type="submit" class="btn-login" name="action" value="Login">Đăng nhập</button>
            </form>
                     
        </div>
        <div class = "e">
            <%= x %>
        </div>

        
        
        
    </body>

</html>
