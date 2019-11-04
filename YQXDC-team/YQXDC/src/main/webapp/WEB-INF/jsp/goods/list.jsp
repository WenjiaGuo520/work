<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理——查看</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
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
	<!-- 内容区域 -->
		<div class="content-wrapper">
	<ul class="breadcrumb">
		<li><a href="#">菜品管理</a> <span class="divider">/</span></li>
		<li class="active">菜品信息列表</li>
	</ul>

	<div class="row-fluid">
		<form class="form-search" style="padding: 5px" action="${pageContext.request.contextPath}/web/goods/list" method="post">
			<label>菜品名称：</label> <input class="input-medium search-query"
				type="text" name="gname" value="${param.gname}" />
			<label>菜品类型：</label>
			<select name="gtid">
				<option value="">全部</option>
				<c:forEach items="${goodstypelist}" var="goodstype">
					<option value="${goodstype.gtid}" 
					<c:if test="${goodstype.gtid==param.gtid}">
						selected="selected"
					</c:if>
					>${goodstype.gtname}</option>
				</c:forEach>
			</select>
			<button type="submit" class="btn">查找</button>
			<button class="btn btn-success" type="button" 
			onclick="window.location.href='${pageContext.request.contextPath}/web/goods/toadd';">增加</button>
		</form>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>菜品种类</th>
					<th>菜品名称</th>
					<th>菜品图片</th>
					<th>菜品价格</th>
					<th>菜品详情</th>
					<th>菜品制作所需时间</th>
					<th>商品状态</th>
					<th>编辑</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${p.list}" var="item">
				<tr class="info">
					<td>${item.gtname}</td>
					<td>${item.gname}</td>
					<td>
						<div style="width:120px;height:100px;"><img alt="商品图品" src="${pageContext.request.contextPath}/file/${item.gimg}"></div>
					</td>
					<td>${item.gprice}</td>
					<td>${item.gcontent}</td>
					<td>${item.gtime} 分钟</td>
					<td>
						<c:choose>
							<c:when test="${item.gstate==1}">
									可用
							</c:when>
							<c:otherwise>
									不可用
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="${pageContext.request.contextPath}/web/goods/toedit/${item.gid}">编辑</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="/basepage/pager.jsp"%>
	</div>
	</div>
	
	<script>
			$(document).ready(function() {
				// 选择框
				$(".select2").select2();

				// WYSIHTML5编辑器
				$(".textarea").wysihtml5({
					locale : 'zh-CN'
				});
			});

			// 设置激活菜单
			function setSidebarActive(tagUri) {
				var liObj = $("#" + tagUri);
				if (liObj.length > 0) {
					liObj.parent().parent().addClass("active");
					liObj.addClass("active");
				}
			}

			$(document)
					.ready(
							function() {

								// 激活导航位置
								setSidebarActive("admin-datalist");

								// 列表按钮 
								$("#dataList td input[type='checkbox']")
										.iCheck(
												{
													checkboxClass : 'icheckbox_square-blue',
													increaseArea : '20%'
												});
								// 全选操作 
								$("#selall")
										.click(
												function() {
													var clicks = $(this).is(
															':checked');
													if (!clicks) {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck(
																		"uncheck");
													} else {
														$(
																"#dataList td input[type='checkbox']")
																.iCheck("check");
													}
													$(this).data("clicks",
															!clicks);
												});
							});
		</script>
</body>
</html>