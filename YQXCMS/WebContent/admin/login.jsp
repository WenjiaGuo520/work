<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            /*border: 1px solid;*/
        }
        body{
        	background-image: url("../img/bg.jpg");
        	
           /*  background: linear-gradient(to right,#5FBE9E,#5987C3); */
        }
        .box_title{
            font-size: 18px;
            font-weight: bolder;
            color: white;
            width: 75px;
            margin:50px auto;
            margin-bottom: 20px;
        }

        .box_login{
            background: white;
            border-radius: 5px;
            width: 450px;
            height: 500px;
            margin: 0 auto;
        }
        .box_logo{
            text-align: center;
            font-weight: bolder;
            font-size: 20px;
            color: #5FBE9E;
        }

        .box_logo img{
            margin: 0px auto;
            margin-bottom: 10px;
        }

        .box_from input{
            border-radius: 6px;
            width: 80%;
            height: 50px;
            margin: 0 10% 15px 10%;
            padding-left: 10px;
            outline: none;
        }

        .box_from input[type="submit"]{
            height: 60px;
            background: #5FBE9E;
            font-weight: bolder;
            color: white;
            outline: none;
        }

        .box_tip{
            width: 450px;
            color: white;
            margin: 10px auto;

        }
        .box_tip input{
            margin-left: 0px;
        }

        .box_tip a{
            display: block;
            margin-right: 0px;
            color: white;
            float: right;
            text-decoration: none;
        }
    </style>
       <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script>
        /* $(function () {
            $("#sub").click(function () {
                location.href = "main.html"
            });
        }) */
    </script>
</head>
<body>
    <div class="layout">
        <div class="box_title">
                SIGN IN
        </div>

        <div class="box_login">
            <div class="box_logo">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo" />
            </div>

            <div class="box_from">
            	<form action="${pageContext.request.contextPath}/sys/user?cmd=toLogin" method="post">
	                <input type="text" placeholder="User ID" name="username"/>
	                <input type="password" placeholder="Password" name="userpwd"/>
	                <input type="submit" id="sub" value="√">
                </form>
            </div>
        </div>

        <div class="box_tip">
            <input type="checkbox" />&nbsp;&nbsp;&nbsp;Remember Me
            <a href="#">Forgot Password?</a>
        </div>
        
        <span style="color:red;">${login_msg}</span>
    </div>
</body>
</html>