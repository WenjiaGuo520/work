<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>编辑菜品</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
</style>

<script type="text/javascript">
	var checkform = function() {
		var flag = false;
		var gname = $("#gname").val();
		if (gname.length > 0) {
			flag = true;
		} else {
			flag = false;
			$("#msg").html("请输入菜品名称");
		}
		return flag;
	}
</script>


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
		<!-- @@master = admin-layout.html-->
		<!-- @@block = content -->
		<body>
			<div class="row-fluid" align="center" style="background-color: #fff;">
				<ul class="breadcrumb">
					<li><a href="#">菜品管理</a> <span class="divider"></span></li>
					<li class="active">编辑菜品信息</li>
				</ul>
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<h4>提示!</h4>
					<strong>${msg}</strong>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<form class="form-horizontal form-inline"
							action="${pageContext.request.contextPath}/web/goods/edit"
							method="post" enctype="multipart/form-data">

							<div class="control-group">
								<label class="control-label" for="gtname">种类：</label>
								<div class="controls">
									<select id="gtid" style="width: 282px" name="gtid"
										class="form-control">
										<c:forEach items="${type}" var="item">
											<option value="${item.gtid}">${item.gtname}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="gname">菜品名称：</label>
								<div class="controls">
									<input id="gname" style="width: 282px" type="text"
										class="form-control" name="gname" value="${goods.gname}" />
									<input id="gid" type="hidden" name="gid" value="${goods.gid}" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="file">图片：</label>
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
										<input type="number" style="width: 282px" step="0.01"
											class="form-control" name="gprice" id="gprice"
											placeholder="￥0.00" value="${goods.gprice}">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="gcontent">菜品详情：</label>
								<div class="controls">
									<textarea class="form-control" style="width: 282px"
										name="gcontent" rows="5" style="width:283px;resize:none">${goods.gcontent}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="gtime">菜品所需时间：/分钟</label>
								<div class="controls">
									<div class="input-group">
										<input type="number" style="width: 282px" step="0.01"
											class="form-control" name="gtime" id="gtime"
											placeholder="小时数（可精确到小数点后两位）" value="${goods.gtime}">
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="gstate">菜品状态：</label>
								<div class="controls">
									<select name="gstate" class="form-control" style="width: 282px">
										<c:choose>
											<c:when test="${goods.gstate==1}">
												<option value="1" selected="selected">可用</option>
												<option value="0">不可用</option>
											</c:when>
											<c:otherwise>
												<option value="1">可用</option>
												<option value="0" selected="selected">不可用</option>
											</c:otherwise>
										</c:choose>
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
		</body>

		<!-- @@close -->
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.8
			</div>
			<strong>Copyright &copy; 2014-2017 <a
				href="http://www.baidu.com">研究院研发部</a>.
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
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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

		$(document).ready(function() {

			// 颜色选取器
			$(".my-colorpicker1").colorpicker();
			$(".my-colorpicker2").colorpicker();

		});

		$(document).ready(function() {
			// 选择框
			$(".select2").select2();
		});

		$(document).ready(
				function() {

					//Date picker
					$('#datepicker').datepicker({
						autoclose : true,
						language : 'zh-CN'
					});

					// datetime picker
					$('#dateTimePicker').datetimepicker({
						format : "mm/dd/yyyy - hh:ii",
						autoclose : true,
						todayBtn : true,
						language : 'zh-CN'
					});

					//Date range picker
					$('#reservation').daterangepicker({
						locale : {
							applyLabel : '确认',
							cancelLabel : '取消',
							fromLabel : '起始时间',
							toLabel : '结束时间',
							customRangeLabel : '自定义',
							firstDay : 1
						},
						opens : 'left', // 日期选择框的弹出位置
						separator : ' 至 '
					//showWeekNumbers : true,     // 是否显示第几周
					});

					//Date range picker with time picker
					$('#reservationtime').daterangepicker({
						timePicker : true,
						timePickerIncrement : 30,
						format : 'MM/DD/YYYY h:mm A',
						locale : {
							applyLabel : '确认',
							cancelLabel : '取消',
							fromLabel : '起始时间',
							toLabel : '结束时间',
							customRangeLabel : '自定义',
							firstDay : 1
						},
						opens : 'right', // 日期选择框的弹出位置
						separator : ' 至 '
					});

					//Date range as a button
					$('#daterange-btn').daterangepicker(
							{
								locale : {
									applyLabel : '确认',
									cancelLabel : '取消',
									fromLabel : '起始时间',
									toLabel : '结束时间',
									customRangeLabel : '自定义',
									firstDay : 1
								},
								opens : 'right', // 日期选择框的弹出位置
								separator : ' 至 ',
								ranges : {
									'今日' : [ moment(), moment() ],
									'昨日' : [ moment().subtract(1, 'days'),
											moment().subtract(1, 'days') ],
									'最近7日' : [ moment().subtract(6, 'days'),
											moment() ],
									'最近30日' : [ moment().subtract(29, 'days'),
											moment() ],
									'本月' : [ moment().startOf('month'),
											moment().endOf('month') ],
									'上个月' : [
											moment().subtract(1, 'month')
													.startOf('month'),
											moment().subtract(1, 'month')
													.endOf('month') ]
								},
								startDate : moment().subtract(29, 'days'),
								endDate : moment()
							},
							function(start, end) {
								$('#daterange-btn span').html(
										start.format('MMMM D, YYYY') + ' - '
												+ end.format('MMMM D, YYYY'));
							});

				});

		$(document).ready(function() {

			/*table tree*/
			$("#collapse-table").treetable({
				expandable : true
			});

		});

		$(document).ready(function() {

			//CKEDITOR.replace('editor1');

			// $(".textarea").wysihtml5({
			//     locale:'zh-CN'
			// });

			$("#markdown-textarea").markdown({
				language : 'zh',
				autofocus : false,
				savable : false
			});

		});

		$(document).ready(function() {

			// 激活导航位置
			setSidebarActive("admin-dataform");

		});
	</script>


	<script>
		$(function() {
			var info = $("#data-info").val();
			if (info === "1") {
				$("#info").css("color", "green");
				$("#info").html("修改成功!");
				$("#data-info").val(-1);
			} else if (info === "0") {
				$("#info").css("color", "red");
				$("#info").html("修改失败!");
				$("#data-info").val(-1);
			}
			/**
			 * 给复选框绑定change事件，改变时记录状态，根据记录的状态决定是否在后端进行权限操作
			 */
			$(".isUsedCheck").change(function() {
				$("#data-editstate").val("1");
			});
		})
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
</body>

</html>
