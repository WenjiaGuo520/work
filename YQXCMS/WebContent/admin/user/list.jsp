<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/stylejs.js"></script>
    <title>用户列表</title>

    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            background-color: #F5F5F5;
        }
        a{
            text-decoration: none;
            color: #2087CC;
        }

        #head{
            margin: 10px 0px 10px 0px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #CDC1AF;
            padding-left: 15px;
        }
        #btn-add{
            background-color: #6A7D7C;
        }

        #form-div{
            margin-left: 20px;
            float: left;
        }
        #table-div{
            margin-top: 15px;
        }
        .mt-hover{
            background-color: white;
        }
        #page-num{
            float: right;
            margin-right: 50px;
            margin-top: 0px;
        }
        #btn-del{
			background-color: #00B0F9;
		}
    </style>
<script type="text/javascript">
		$(function(){
			
			$("#first-cb").click(function() {
				$(".tid-cb").prop("checked",$(this).prop("checked"));
				
			});
			
			$("#btn-del").click(function() {
			    if(confirm("您确定弃用这些用户吗?")){

	                //var cbs = document.getElementsByName("uid");
	                var flag = false;
	                $(".tid-cb").each(function () {
	                    if($(this).prop("checked")){
	                        flag = true;
	                        return false;
	                    }
	                });
	                //如果有表单被选中,就提交表单
	                if(flag){
	                	//alert($("#table-form").prop("action")+":"+$("#table-form").prop("method"));
	                	$("#table-form").submit();
	                }
	            }
			});
			
			
		});
	</script>
</head>
<body>
    <div id="head">
        <a href="javascript:void(0);">用户管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
        <a href="javascript:void(0);" style="color: #AAA">用户列表</a>
    </div>

    <div>
        <div id="form-div">
            <form class="form-inline" action="${pageContext.request.contextPath }/sys/user?cmd=toList" method="post">
                <div class="form-group">
                    <label  for="searchName">姓名</label>
                    <input type="text" class="form-control" id="searchName" name="usertruename">
                </div>
				
                <button type="submit" class="btn btn-default">查找</button>
            </form>
        </div>
    </div>
    <div style="float:right;margin:0 10px 10px 0">
    <a href="javascript:void(0);" class="btn btn-default" id="btn-del">弃用选中</a>
    <a href="${pageContext.request.contextPath}/admin/user/add.jsp" class="btn btn-default" id="btn-add">增加</a>
    </div>
    <div style="float: none"></div>
    <div id="table-div">
    <form action="${pageContext.request.contextPath}/sys/user?cmd=del" id="table-form" method="post">
        <table class="table table-bordered">
            <tr>
            	<th><input type="checkbox" id="first-cb"></th>
                <th>用户名</th>
                <th>密码</th>
                <th>所属角色</th>            
                <th>真实姓名</th>
                <th>性别</th>
                <th>联系方式</th>
                <th>住址</th>
                <th>身份证号</th>
                <th>用户状态</th>
                <th>操作</th>
            </tr>
			<c:forEach items="${pb.list}" var="user">
				<tr>
					<td><input type="checkbox" class="tid-cb" name="userid" value="${user.userid}"></td>
	                <td>${user.username}</td>
	                <td>${user.userpwd}</td>
	                <td>${user.rname}</td>
	                <td>${user.usertruename}</td>
	                <td>${user.sex}</td>
	                <td>${user.phone}</td>
	                <td>${user.useraddress}</td>
	                <td>${user.carid}</td>
	                <c:if test="${user.ustate==1}">
	                	<td>可用</td>
	                </c:if>
	                 <c:if test="${user.ustate!=1}">
	                	<td>不可用</td>
	                </c:if>	         
	                <td><a href="${pageContext.request.contextPath}/sys/user?cmd=toEdit&userid=${user.userid}&username=${user.username}&userpwd=${user.userpwd}
	                &usertruename=${user.usertruename}&sex=${user.sex} &phone=${user.phone} &useraddress=${user.useraddress} &carid=${user.carid} &ustate=${user.ustate}">编辑</a></td>
	            </tr>
				
			</c:forEach>

        </table>
        </form>
    </div>
    <%@ include file="../pager.jsp" %>
</body>
</html>