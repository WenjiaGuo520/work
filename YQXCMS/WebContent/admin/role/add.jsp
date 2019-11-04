<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>增加角色</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #F5F5F5;
        }
        #head{
            margin: 10px 0px 10px 0px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #CDC1AF;
            padding-left: 15px;
        }
        a{
            text-decoration: none;
        }
        a:visited{
            color: #AAAAAA;
        }
        #info{
            background-color: #FAF4D6;
            font-weight: bolder;
            font-size: 20px;
            height: 50px;
            line-height: 50px;
            padding-left: 20px;
            border: 1px solid #F1E18B;
        }
        #form{
            /*border: 1px solid red;*/
            width: 500px;
            margin: 20px auto;
        }
        #form input[type='text'],#rdesc{
            width: 60%;
            height: 30px;
            border-radius: 3px;
            padding-left: 15px;
            font-size: 20px;
            margin-bottom: 20px;
        }
        #rdesc{
        	height: 100px;
        }
        #form label{
            width: 20%;
            height: 30px;
            font-size: 20px;
            line-height: 30px;
            margin-right: 15px;
            margin-bottom: 20px;
        }

        #sub,#cancel{
            height: 38px;
            width: 80px;
            font-size: 16px;
            line-height: 38px;
            border: 1px solid #AAAAAA;
            cursor: pointer;
        }
        #sub{
            margin-left: 20%;
        }
        #cancel{
            background-color: #647089;

        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script>
        $(function(){
        	//绑定返回
        	$("#cancel").click(function(){
        		location.href="/YQXCMS/sys/role?cmd=toList";
        	})
        	
        })
    </script>
</head>
<body>
<div id="head">
    <a href="javascript:void(0);">角色管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
    <a href="javascript:void(0);" style="color: #AAA">角色增加</a>
</div>

<div id="info">
  ${rtn.msg}
</div>
<div id="form">
    <form action="${pageContext.request.contextPath}/sys/role?cmd=add" method="post">

        <label for="rolename">角色名称: </label><input type="text" id="rolename" name="rname"/><br>
        <label for="rdesc">角色描述: </label><textarea rows="" cols="" id="rdesc" name="rdesc"></textarea>
        	
                <input type="submit" id="sub" value="提交"/>
                <input type="button" id="cancel" value="返回"/>

    </form>
</div>
</body>
</html>