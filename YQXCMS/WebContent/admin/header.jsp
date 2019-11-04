<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>头</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <style>
        body{
            background-color: #aac5e7;
            text-align: center;
        }
        div{
        	text-align: center;
        	height:60px;
        	line-height:60px;
            font-family: 华文楷体;
            font-size: 25px;
            font-style: italic;
       		margin-top:0px;
            color:white;
        }
        .left{
        	float:left;
            width:200px;
            margin-left: 25px;
        }
        .right{
        	float:right;
        	width:200px;
        	margin-right: 50px
        }
        
    </style>
    <script type="text/javascript">
    $(function(){
    	var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		$("#time").html(y+"年"+m+"月"+d+"日");
    })
  
    </script>
</head>
<body>
    
    <div class="left">欢迎,${login_user.usertruename}</div>
    <div class="right" id="time"></div>
</body>
</html>