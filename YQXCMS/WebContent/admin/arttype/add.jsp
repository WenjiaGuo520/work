<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加文章类型</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.css">
<script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	//表单检查
	function checkform(){
		var flag = false;
		var arttype = $("#typename").val();
		if(arttype.length>0){
			flag = true;
		}else{
			$("#msg").html("文章类型名称不能为空！");
			$("#typename").focus();
		}
		return flag;
	}
	
	

</script>
</head>
<body>
	<ul class="breadcrumb">
		<li><a href="#">文章类型管理</a> <span class="divider">/</span></li>
		<li class="active">增加文章类型</li>
	</ul>
	<div class="alert">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>提示!</h4>
		<strong id="msg">${msg}</strong>
	</div>
	<div class="row-fluid">
	
	<div style="width:30%;margin: auto;">
		<div class="span12">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/art/type?cmd=add"
				method="post">
				<div class="control-group">
					<label class="control-label" for="typename">文章类型名称</label>
					<div class="controls">
						<input id="typename" class="form-control" type="text" name="tname" maxlength="30"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="typedesc">文章类型说明</label>
					<div class="controls">
						<textarea class="form-control" name="tdesc" id="typedesc"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="tsort">排序</label>
					<div class="controls">
						<select class="form-control" class="" name="tsort">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="tstate">文章类型状态</label>
					<div class="controls">
						<select class="form-control" name="tstate">
							<option value="1">可用</option>
							<option value="0">不可用</option>
						</select>
					</div>
				</div>
				<div class="control-group" style="margin: 15px auto;">
					<div class="controls">
						<input type="submit" class="btn" value="提交" onclick="return checkform();" />
						<a class="btn btn-primary" style="margin-left: 10px" type="button" href="${pageContext.request.contextPath}/art/type?cmd=list">返回</a>
					</div>
				</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>