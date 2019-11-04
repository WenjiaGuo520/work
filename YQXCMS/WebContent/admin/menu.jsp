<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单</title>
    <base target="main">
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #F0F0F0;
        }
        #left{
            float: left;
            width: 254px;
            margin-top: 30px;
        }
        #left dt{
            display: block;
            font-size: 25px;
            font-weight: bolder;
            color: #333;
            margin-left: 30px;
            margin-bottom: 15px;
        }

        #left dd{
            display: block;
            font-size: 20px;

            margin-left: 40px;
            margin-bottom: 15px;

        }
        a{
            text-decoration: none;
        }
        a:link{
            color:#333;
        }

    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script>
        $(function () {
        	
        	//加载根目录
        	
        	$("#btnGoods").click(function () {
                $("#goods").css("display")=="none"?$("#goods").css("display","block"):$("#goods").css("display","none");

            });
            $("#btnSys").click(function () {
                $("#sys").css("display")=="none"?$("#sys").css("display","block"):$("#sys").css("display","none");
            });
            
            if($("#userRid").val()){
            	
	            $.post("/YQXCMS/sys/fun?cmd=listByRid",{"rid":$("#userRid").val()},function(data){
	            	//创建子节点
	            	for (var i = 0; i < data.length; i++) {           		
	            		if(data[i].pfid==1){
							$("#menu1").after('<dd><a href="${pageContext.request.contextPath}/'+data[i].faddr+'">'+data[i].fname+'</a></dd>');	
	            		}else if(data[i].pfid==2){
	            			$("#menu2").after('<dd><a href="${pageContext.request.contextPath}/'+data[i].faddr+'">'+data[i].fname+'</a></dd>');	
	            		}	
					}
	            	
	            },"json")
            }else{
            	//alert("请登录,然后才有菜单哦.....!");
            }
        })
    </script>
</head>
<body>
	<input type="text" hidden="true" id="userRid" value="${login_user.rid }">

<div id="left">
    <dl>
        <dt><a href="javascript:void(0);" id="btnGoods">文章管理</a></dt>
    </dl>
    <div id="goods" style="display: none">
        <dl id="menu1">
            
        </dl>
    </div>

    <dl>
        <dt><a href="javascript:void(0);" id="btnSys">系统管理</a></dt>
    </dl>
    <div id="sys" style="display: none">
        <dl id="menu2">
           
        </dl>
    </div>
</div>
</body>
</html>