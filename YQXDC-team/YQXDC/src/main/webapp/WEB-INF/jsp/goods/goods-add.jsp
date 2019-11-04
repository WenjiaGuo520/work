<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>添加菜品</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
<style>
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
}

input[type="number"] {
	-moz-appearance: textfield;
}

.upload {
	position: relative;
	display: inline-block;
	height: 33px;
	line-height: 33px;
	overflow: hidden;
	vertical-align: middle;
	cursor: pointer;
	margin-left: 20px;
	height: 36px;
}

.upload .upload-input-file {
	position: absolute;
	right: 0;
	top: 0;
	font-size: 100px;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
}

.upload .upload-btn {
	outline: none;
	border: 0;
	padding: 5px 10px;
	color: #fff;
	background: #1c84c6;
	cursor: pointer;
	margin: 3px;
	border-radius: 3px;
}

.upload .upload-url {
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	border-radius: 2px;     border : solid 1px #ddd;
	width: 200px;
	height: 30px;
	-webkit-line-height: 1;
	line-height: 30px\9;
	-ms-line-height: 30px;
	text-indent: 3px;
}

.upload .upload-tip {
	display: none;
	background-color: #1c84c6;
	padding: 2px 10px;
	color: #fefefe;
	font-size: 12px !important;
	border-radius: 3px;
}

<
script

 

type

 

="
text
/javascript"
>
var

 

checkform

 

=
function

 

()
{
var

 

flag

 

=
false


;
var

 

gname

 

=
$("
#gname


")
.val


();
if

 

(
gname


.length
>
0)
{
flag

 

=
true


;
}
else {flag = false; $("#msg ").html("请输入菜品名称");
	
}

return


 


flag




;
}
</
script
>
<
script


 


type




="
text
/javascript"
>
$("
.upload


 


.upload-input-file




")
.change




(
function


 


()
{
 


 


 


 


 


 


 






 


 


 


 


 


 


 


 


if


 


($(
this




)
.parent




()
.html




()
.indexOf




("
class


=
\"upload-url\"
")


 


!=
-1)
{
 


 


 


 


 


 


 


 


 


 


 


 


var


 


fileUrl


 


=
$(
this




)
.val




();
 


 


 


 


 


 


 


 


 


 


 


 


$(
this




)
.parent




()
.children




("
.upload-url




")
.val




(
fileUrl


);
 


 


 


 


 


 


 


 


}
        else {            var fileUrl = $(this).val();            var
	urlArr = fileUrl.split("\\");            var getName =
	urlArr[urlArr.length - 1]; //截取路径并获取文件的名字             
	$(this).parent().children(".upload-tip").text(getName).fadeIn("slow"); 
	          //$(this).parent().children(".upload-btn").val(getName); //按钮上变成文件名称
	            timeout = setTimeout(function () {                        
	        $(".upload-tip").fadeOut("slow");                           
	
}
,
5000);
 


 


 


 


 


 


 


 


}
 


 


 


 


}
);
</
script
>
</style>

</head>

<body class="hold-transition skin-purple sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<jsp:include page="../header.jsp"></jsp:include>
		<!-- 页面头部 /-->
		<!-- 导航侧栏 -->
		<jsp:include page="../aside.jsp"></jsp:include>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<h1>
					菜品管理 <small>增加菜品</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/toMain"><i
							class="fa fa-dashboard"></i> 首页</a></li>
					<li><a
						href="${pageContext.request.contextPath}/web/goods/list">菜品管理</a></li>
					<li class="active">菜品表单</li>
				</ol>
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<h4>提示!</h4>
					<strong>${msg}</strong>
				</div>
			</section>
			<!-- 内容头部 /-->


			<div class="row-fluid" align="center">
				<div class="span12">
					<form class="form-horizontal form-inline"
						action="${pageContext.request.contextPath}/web/goods/add"
						method="post" enctype="multipart/form-data">

						<div class="control-group">
							<label class="control-label" for="gtname">菜品类型：</label>
							<div class="controls">
								<select id="gtid" name="gtid" class="form-control"
									style="width: 282px">
									<c:forEach items="${type}" var="item">
										<option value="${item.gtid}">${item.gtname}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="gname">菜品名称：</label>
							<div class="controls">
								<input id="gname" class="form-control" style="width: 282px"
									type="text" name="gname" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="file">图片:</label>
							<div class="upload">
								<input type="text" style="width: 282px"
									class="upload-url form-control" value="${goods.gimg}" /> <input
									type="button" class="upload-btn" value="浏览文件" /> <input
									type="file" name="file" class="upload-input-file" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="gprice">菜品价格：</label>
							<div class="controls">
								<div class="input-group">
									<input type="number" step="0.01" style="width: 282px"
										class="form-control" name="gprice" id="gprice"
										placeholder="￥0.00">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="gcontent">菜品详情：</label>
							<div class="controls">
								<textarea class="form-control" name="gcontent" rows="5"
									style="width: 282px; resize: none"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="gtime">菜品所需时间：分钟</label>
							<div class="controls">
								<div class="input-group">
									<input type="number" step="0.01" style="width: 282px"
										class="form-control" name="gtime" id="gtime"
										placeholder="小时数（可精确到小数点后两位）">
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="gstate">菜品状态：</label>
							<div class="controls">
								<select id="gstate" name="gstate" class="form-control"
									style="width: 282px">
									<option value="1">可用</option>
									<option value="0">不可用</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" class="btn" value="提交"
									onclick="return checkform();" />
								<button class="btn btn-primary" type="button"
									onclick="toMain();">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>


		</div>
		<!-- 内容区域 /-->





		<!-- 底部导航 -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.8
			</div>
			<strong>Copyright &copy; 2019-2020<a
				href="http://www.itcast.cn">研究院研发部</a>.
			</strong> All rights reserved.
		</footer>
		<!-- 底部导航 /-->

	</div>


	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

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
	</script>
	<<<<<<< .mine =======
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
		$(".upload .upload-input-file")
				.change(
						function() {
							if ($(this).parent().html().indexOf(
									"class=\"upload-url\"") != -1) {
								var fileUrl = $(this).val();
								$(this).parent().children(".upload-url").val(
										fileUrl);
							} else {
								var fileUrl = $(this).val();
								var urlArr = fileUrl.split("\\");
								var getName = urlArr[urlArr.length - 1];//截取路径并获取文件的名字 
								$(this).parent().children(".upload-tip").text(
										getName).fadeIn("slow");
								//$(this).parent().children(".upload-btn").val(getName);//按钮上变成文件名称
								timeout = setTimeout(function() {
									$(".upload-tip").fadeOut("slow");
								}, 5000);
							}
						});

		function toMain() {
			window.location.href = '${pageContext.request.contextPath}/web/goods/list';
		}
	</script>
	>>>>>>> .r11
</body>

</html>