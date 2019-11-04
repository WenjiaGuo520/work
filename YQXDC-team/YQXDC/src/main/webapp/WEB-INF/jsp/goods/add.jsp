<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理-增加</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.css">
<script src="${pageContext.request.contextPath}/css/jquery-1.8.1.min.js"
	type="text/javascript"></script>
	<script type="text/javascript">
	var checkform = function() {
		var flag = false;
		var rolename = $("#rolename").val();
		if (rolename.length > 0) {
			flag = true;
		} else {
			flag = false;
			$("#msg").html("*请输入菜品名称");
		}
		return flag;
	}
</script>
<script type="text/javascript">
	$(".upload .upload-input-file").change(function () {        
        if ($(this).parent().html().indexOf("class=\"upload-url\"") != -1) {
            var fileUrl = $(this).val();
            $(this).parent().children(".upload-url").val(fileUrl);
        }
        else {
            var fileUrl = $(this).val();
            var urlArr = fileUrl.split("\\");
            var getName = urlArr[urlArr.length - 1];//截取路径并获取文件的名字 
            $(this).parent().children(".upload-tip").text(getName).fadeIn("slow");
            //$(this).parent().children(".upload-btn").val(getName);//按钮上变成文件名称
            timeout = setTimeout(function () {                
                $(".upload-tip").fadeOut("slow");                
            }, 5000);
        }
    });
</script>
<style>
	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
	    -webkit-appearance: none;
	}
	 
	input[type="number"] {
	    -moz-appearance: textfield;
	}
	.upload{position:relative; display:inline-block; height:33px;line-height:33px; overflow:hidden;vertical-align:middle; cursor:pointer;margin-left:20px;height: 36px;}
	.upload .upload-input-file{position:absolute; right:0; top:0; font-size:100px; opacity:0; filter:alpha(opacity=0);cursor:pointer;}
	.upload .upload-btn{outline:none;border:0; padding:5px 10px;color:#fff;background:#1c84c6;cursor:pointer; margin:3px; border-radius:3px;}
	.upload .upload-url { -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; border-radius:2px;
	    border: solid 1px #ddd; width: 200px; height:30px;-webkit-line-height: 1;line-height: 30px\9;-ms-line-height: 30px; text-indent:3px;}
	.upload .upload-tip {display:none; background-color:#1c84c6; padding:2px 10px; color:#fefefe; font-size:12px !important;border-radius:3px;}
</style>
</head>
<body>
<body>
	<ul class="breadcrumb">
		<li><a href="#">菜品管理</a> <span class="divider">/</span></li>
		<li class="active">增加菜品信息</li>
	</ul>
	<div class="alert">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<h4>提示!</h4>
		<strong>${msg}</strong> 
	</div>
	<div class="row-fluid">
		<div class="span12">
			<form class="form-horizontal form-inline"
				action="${pageContext.request.contextPath}/web/goods/add"
				method="post" enctype="multipart/form-data">
				
				<div class="control-group">
					<label class="control-label" for="gtname">种类：</label>
					<div class="controls">
						<select id="gtid" name="gtid">
							<c:forEach items="${type}" var="item">
								<option value="${item.gtid}">${item.gtname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="gname">菜品名称：</label>
					<div class="controls">
						<input id="gname" type="text" name="gname" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="file">图片：</label>
					<div class="upload">
						<input type="text"  class="upload-url" />
						<input type="button"  class="upload-btn" value="浏览文件" />
						<input type="file" name="file" class="upload-input-file" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gprice">菜品价格：</label>
					<div class="controls">
						<div class="input-group">
					      <input type="number" step="0.01" class="form-control" name="gprice" id="gprice" placeholder="￥0.00">
					    </div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gcontent">菜品详情：</label>
					<div class="controls">
						<textarea class="form-control" name="gcontent" rows="5" style="width:283px;resize:none"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gtime">菜品所需时间：</label>
					<div class="controls">
						<div class="input-group">
					      <input type="number" step="0.01" class="form-control" name="gtime" id="gtime" placeholder="小时数（可精确到小数点后两位）">分钟
					    </div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gstate">菜品状态：</label>
					<div class="controls">
						<select id="gstate" name="gstate">
							<option value="1">可用</option>
							<option value="0">不可用</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input type="submit" class="btn" value="提交" 
						 onclick="return checkform();"  />
						<button class="btn btn-primary" type="button">返回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</body>
</html>