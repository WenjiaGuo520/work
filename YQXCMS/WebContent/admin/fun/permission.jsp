<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色权限分配</title>
   <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
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

        #permission{
            margin-top: 30px;
        }
        #permission dt{
            margin-left: 30px;
            margin-bottom: 15px;
            font-size: 25px;
            font-weight: bold;
        }
        #permission dd{
            margin-left: 60px;
            margin-bottom: 15px;
            font-size: 20px;
        }
        #save-btn{
            width: 80px;
            height: 40px;
            font-size: 20px;

            color: white;
            text-align: center;
            line-height: 40px;
            background-color: #647089;
            border: 1px solid #000;
            margin-left: 60px;
            cursor: pointer;
        }
    </style>

</head>
<body>
<div id="head">
    <a href="javascript:void(0);">用户管理</a> &nbsp;&nbsp;/&nbsp;&nbsp;
    <a href="javascript:void(0);" style="color: #AAA">权限分配</a>
</div>
<div id="permission">
    <form action="${pageContext.request.contextPath}/sys/perm?cmd=edit&rid=${roleid}" method="post">
    
		<dl>
        	<!-- 遍历功能列表 -->
        	<c:forEach items="${list}" var="fun">
        		<!-- pfid==-1为根节点 -->
        		<c:if test="${fun.pfid==-1}">
        			<dt><input type="checkbox" id="gtypes" value="${fun.fid}" dataid="${fun.fid}" />&nbsp;&nbsp;${fun.fname}</dt>
        		</c:if>
        		
        		<!-- 遍历子节点 -->
	        	<c:forEach items="${list}" var="child">
	        		<c:if test="${child.pfid==fun.fid}">
	        			<c:if test="${child.isRoleFun==1 }">
	        				<dd><input type="checkbox" class="gtype" value="${child.fid}"  checked="checked" dataid="${child.fid}" name="fid" />&nbsp;&nbsp;${child.fname}</dd>
	        			</c:if>
	        			<c:if test="${child.isRoleFun==0 }">
	        				<dd><input type="checkbox" class="gtype" value="${child.fid}"  dataid="${child.fid}" name="fid" />&nbsp;&nbsp;${child.fname}</dd>
	        			</c:if>
	        		</c:if>
	        	</c:forEach>
        		
        	</c:forEach>
        	
        	
        </dl>
        <input type="text" hidden="true" id="rid" value="${roleid}">
    <input type="submit" id="save-btn" value="保存"/>
    </form>
    
    <span style="font-size: 25px;width: 100%;text-align: center;">${msg}</span>
</div>
</body>
</html>