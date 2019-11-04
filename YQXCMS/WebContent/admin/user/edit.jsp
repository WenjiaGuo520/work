<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/stylejs.js"></script>
    <title>修改用户</title>
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
      	width:40%;
      	margin: 30px auto;
      	
      }
    </style>
     <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script>
        $(function () {
        	var sex = $("#data-sex").val();
        	if(sex=="男"){
        		//填充下拉列表
            	$("#sex option[value='男']").prop("selected","selected");
        	}else{
        		//填充下拉列表
            	$("#sex option[value='女']").prop("selected","selected");
        	}
        	var ustate = $("#data-ustate").val();
        	if(ustate==1){
        		$("#ustate option[value='1']").prop("selected","selected");
        	}else{
        		$("#ustate option[value='0']").prop("selected","selected");
        	};
        	
        	
            $("#cancel").click(function () {
                location.href = "/YQXCMS/sys/user?cmd=toList";
            });
            
            $("#username").blur(function(){     
            	checkUsername();        	
            });
            $("#userpwd").blur(function(){     
            	checkUserpwd();        	
            });
            $("#usertruename").blur(function(){     
            	checkUsertruename();        	
            });
            //填充角色下拉框
            $.post("/YQXCMS/sys/role?cmd=getAll",{},function(data){
            	for(var i=0;i<data.length;i++){
  		          	$("#userRole").append(' <option value="'+data[i].rid+'">'+data[i].rname+'</option>');
            	}
            },"json")
        });
        //校验输入数据
        function checkParam(){	 
        	var state = checkUsername() && checkUserpwd() && checkUsertruename();	
        	if(!state){
        		alert("提交失败,请检验输入项是否正确!");
        	}
        	//alert(state);
        	return state;
        }
      
	    //校验用户名
	     function checkUsername(){
	    	 var username = $("#username").val();
	    	 var reg = /^[a-zA-Z0-9]{4,12}$/;
	    	 var flag = true;
	    	 if(reg.test(username)){
	    		//查询数据库中是否存在
	    	    $.post("/YQXCMS/sys/user?cmd=check",{"username":username},function(data){
	    	    	if(data!=null && data.isExists==true){
	    	    		$("#usernamechecked").css("color","red");
	    	    		$("#usernamechecked").html("用户名已存在!");
	    	    		flag =  false;
	    	    	}else{
	    	    		$("#usernamechecked").css("color","green");
	    	    		$("#usernamechecked").html("用户名可用!");	    	
	    	    	}
	    	    },"json");
	    	 }else{
	    		 $("#usernamechecked").css("color","red");
	    		 $("#usernamechecked").html("用户名不合法!");
	    	 	flag = false;
	    	 }
	    	 return flag;
	    	 
	     }
	    
	    function checkUserpwd(){
	    	var userpwd = $("#userpwd").val();
	    	if(userpwd!="" && userpwd.length>4){
	    		$("#usernpwdchecked").css("color","green");
	    		$("#usernpwdchecked").html("密码可用!");
	    		return true;
	    	}else{
	    		$("#usernpwdchecked").css("color","red");
	    		$("#usernpwdchecked").html("密码不合法!");
	    		return false;
	    	}
	    }
	    function checkUsertruename(){
	    	var usertruename=$("#usertruename").val();
	    	if(""!=usertruename){
	    		$("#usertruenamechecked").css("color","green");
	    		$("#usertruenamechecked").html("姓名可用!");
	    		return true;
	    	}else{
	    		$("#usertruenamechecked").css("color","red");
	    		$("#usertruenamechecked").html("姓名不合法!");
	    		return false;
	    	}
	    }
    </script>
</head>
<body>
<div id="head">
    <a href="javascript:void(0);">用户管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
    <a href="javascript:void(0);" style="color: #AAA">用户修改</a>
</div>

<div id="info">
    ${msg}
</div>
    <div id="form">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/sys/user?cmd=edit" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户昵称: </label>
                <div class="col-sm-10">
                	<input type="text" hidden="true" name="userid" value="${user.userid}">
                    <input type="text" class="form-control" id="username" name="username" value="${user.username}"/><span id="usernamechecked"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="userpwd" class="col-sm-2 control-label">密码: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userpwd" name="userpwd" value="${user.userpwd}" /><span id="usernpwdchecked"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="userRole" class="col-sm-2 control-label">用户角色: </label>        
                <div class="col-sm-10">
                    <select name="rid" class="form-control" id="userRole">

                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="usertruename" class="col-sm-2 control-label">真实姓名: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="usertruename" name="usertruename" value="${user.usertruename}" /><span id="usertruenamechecked"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="sex" class="col-sm-2 control-label">性别: </label>
                <div class="col-sm-10">
                	<input type="text" hidden="true" id="data-sex" value="${user.sex}">
                    <select name="sex" id="sex" class="form-control" >
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">电话: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
                </div>
            </div>
            
            
            <div class="form-group">
                <label for="useraddress" class="col-sm-2 control-label">住址: </label>
                <div class="col-sm-10">
                   <input type="text"id="useraddress" class="form-control" name="useraddress" value="${user.useraddress}" />
                </div>
            </div>
            
            
            <div class="form-group">
                <label for="carid" class="col-sm-2 control-label">身份证: </label>
                <div class="col-sm-10">
                    <input type="text"id="carid" class="form-control" name="carid" value="${user.carid}" />
                </div>
            </div>
            <div class="form-group">
                <label for="ustate" class="col-sm-2 control-label">用户状态: </label>
                <div class="col-sm-10">
                	<input type="text" hidden="true" id="data-ustate" value="${user.ustate}">
                    <select name="ustate"class="form-control" id="ustate">
                        <option value="1">可用</option>
                        <option value="0">不可用</option>
                    </select>
                </div>
            </div>
            
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit"  class="btn btn-default" id="sub" value="提交" onclick="return checkParam();"/>
                    <input type="button"  class="btn btn-default" id="cancel" value="返回"/>
                </div>
            </div>

        </form>
    </div>
</body>
</html>