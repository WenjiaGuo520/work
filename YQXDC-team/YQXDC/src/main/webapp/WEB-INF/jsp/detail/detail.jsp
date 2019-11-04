<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详细</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.css">
<script src="${pageContext.request.contextPath}/css/jquery-1.8.1.min.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="breadcrumb">
		<li><a href="#">订单管理</a> <span class="divider">/</span></li>
		<li class="active">订单详细</li>
	</ul>

	<div class="row-fluid">
		<form class="form-search" style="padding: 5px" method="post"></form>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>菜名</th>
					<th>数量</th>
					<th>时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.gname}</td>
						<td>${item.odcount}</td>
						<td>${item.ordertime}</td>
						<td><c:choose>
								<c:when test="${item.orderstate==1}">下单</c:when>
								<c:when test="${item.orderstate==2}">处理</c:when>
								<c:otherwise>完成</c:otherwise>
							</c:choose></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="box-tools text-center">

		<button type="button" class="btn bg-default"
			onclick="location.href='${pageContext.request.contextPath}/cusorder/list'">返回</button>
	</div>
</body>
</html>