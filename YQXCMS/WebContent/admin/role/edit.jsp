<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改角色</title>
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
        #form input[type='text'],#rdesc,#rstate{
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
		#desclable{
			line-height: 100px;
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
        	var rstate = $("#data-forstate").val();
        	if(rstate==1){
        		$("#rstate option[value=1]").prop("selected","selected");
        	}else{
        		$("#rstate option[value=0]").prop("selected","selected");
        	}
        	//绑定返回
        	$("#cancel").click(function(){
        		location.href="/YQXCMS/sys/role?cmd=toList";
        	})
        	
        	
        })
        //校验表单数据的合法性
        function checkParam() {
				var data = $("#editform").serializeArray();
				//alert(data.length);
				if(data.length==4){
					return true;
				}
				return false;
			}
    </script>
</head>
<body>
<div id="head">
    <a href="javascript:void(0);">角色管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
    <a href="javascript:void(0);" style="color: #AAA">角色修改</a>
</div>

<div id="info">
  ${msg}
</div>
<div id="form">
    <form action="${pageContext.request.contextPath}/sys/role?cmd=edit" method="post" id="editform" >
		<input type="text" hidden="true" id="rid" name="rid" value="${role.rid}">
        <label for="rname">角色名称: </label><input type="text" id="rname" name="rname" value="${role.rname}"/><br>
        <label for="rdesc" id="desclable">角色描述: </label><textarea rows="" cols="" id="rdesc" name="rdesc">${role.rdesc}</textarea><br>
        
        <input type="text" hidden="true" id="data-forstate" value="${role.rstate}">
         <label for="rstate">角色状态: </label>
         <select name="rstate" id="rstate">
         	<option value="1">可用</option>
         	<option value="0">不可用</option>
         </select><br>
                <input type="submit" id="sub" value="提交" onclick="return checkParam();"/>
                <input type="button" id="cancel" value="返回"/>

    </form>
</div>
</body>
</html>