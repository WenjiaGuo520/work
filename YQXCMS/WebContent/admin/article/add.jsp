<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加文章</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.css">
<script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
<style type="text/css">
	.form-div{
		width: 300px;
		margin-left: 20px;
	}
	.form-area{
		margin-left: 20px;
		margin-right: 20px;
	}
</style>
</head>
<body>
	<ul class="breadcrumb">
		<li><a href="#">文章管理</a> <span class="divider">/</span></li>
		<li class="active">增加文章</li>
	</ul>
	<div class="alert">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>提示!</h4>
		<strong>${msg}</strong>
	</div>
	<div class="row-fluid">
		<div class="span12">
		<!-- 上传文件是上传到服务器上，而保存到数据库是文件名 -->
		<!-- 上传文件是以文件转换为二进制流的形式上传的 -->
		<!-- enctype="multipart/form-data"需要设置在form里面，否则无法提交文件 -->
		<!-- 文件上传的form表单的提交方式必须是post提交方式 -->
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/art/news?cmd=add"
				method="post" enctype="multipart/form-data">
				<div class="control-group form-div">
					<label class="control-label" for="gtname">标题</label>
					<div class="controls">
						<input id="newstitle" class="form-control" type="text" name="arttitle" />
					</div>
				</div>

				<!-- 演示初始化下拉列表的功能 -->
				<div class="control-group form-div">
					<label class="control-label" for="gtname">文章类型</label>
					<div class="controls">
						<select name="tid" class="form-control">
							<c:forEach items="${typelist}" var="item">
								<option value="${item.tid}">${item.tname}</option>
							</c:forEach>
							
							<!-- 如果是修改，如下进行初始化 -->
							<%-- <c:forEach items="${typelist}" var="item1">
								<c:choose>
									<c:when test="${item1.typeid==news.typeid}">
										<option value="${item.typeid}" selected="selected">${item.typename}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.typeid}">${item.typename}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach> --%>
						</select>
					</div>
				</div>
				<div class="control-group form-div">
					<label class="control-label" for="artabs">文章摘要</label>
					<div class="controls">
						<input id="artabs" class="form-control" type="text" name="artabs" />
					</div>
				</div>
				<!-- 演示图片上传功能 -->
				<div class="control-group form-div">
					<label class="control-label" for="newimgurl">图片</label>
					<div class="controls">
						<input type="file" class="form-control" name="artimg" />
					</div>
				</div>
				<!-- 演示富文本编辑器 -->
				<div class="control-group">
					<label class="control-label" for="newimgurl">文章内容</label>
					<div class="controls">
						<textarea rows="5"  cols="40" class="ckeditor form-area" name="arttext"></textarea>
					</div>
				</div>
				<div class="control-group form-div">
					<label class="control-label" for="pubDate">出版日期</label>
					<div class="controls">
						<input id="pubDate" class="form-control" type="date" name="pubDate" />
					</div>
				</div>
				<div class="control-group form-div">
					<label class="control-label" for="author">作者</label>
					<div class="controls">
						<input id="author" class="form-control" type="text" name="author" />
					</div>
				</div>
				<div class="control-group form-div">
					<label class="control-label" for="artsource">来源</label>
					<div class="controls">
						<input id="artsource" class="form-control" type="text" name="artsource" />
					</div>
				</div>
				
				<div class="control-group form-div">
					<div class="controls" style="margin-top: 15px;margin-bottom: 50px">
						<input type="submit" class="btn" value="提交" />
						<button class="btn btn-primary" type="button"
							onclick="javascript:window.location.href='${pageContext.request.contextPath}/art/news?cmd=list';">返回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
    var editor = CKEDITOR.replace('arttext');
    CKFinder.setupCKEditor(editor, '${pageContext.request.contextPath}/ckfinder/');
</script>
</body>
</html>