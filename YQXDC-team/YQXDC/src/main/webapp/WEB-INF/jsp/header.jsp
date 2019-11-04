<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="all-admin-index.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>数据</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>数据</b>后台管理</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
						class="user-image" alt="User Image"> <span class="hidden-xs">
						<security:authorize access="isFullyAuthenticated()">
							<security:authentication property="principal.username" var="loginuser" />
						</security:authorize>
					</span>

				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img
							src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
								<c:if test="${loginuser!=null}">
									<p>
									欢迎，${loginuser}
									</p>
								</c:if>
								<c:if test="${loginuser==null}">
									<p>
									未登录，<a href="${pageContext.request.contextPath}/sys/user/toLogin">前往登录?</a>
									</p>
								</c:if>
							</li>
							
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<input type="hidden" value="${loginuser}" id="data-user">
								
								<c:if test="${loginuser==null}">
									<a href="#" class="btn btn-default btn-flat" disabled>修改密码</a>
								</c:if>
								<c:if test="${loginuser!=null}">
									<a href="#" class="btn btn-default btn-flat" id="editPwdBtn">修改密码</a>
								</c:if>
							</div>
							<div class="pull-right">
								<c:if test="${loginuser==null}">
									<a href="#" disabled
									class="btn btn-default btn-flat">注销</a>
								</c:if>
								<c:if test="${loginuser!=null}">
									<a href="${pageContext.request.contextPath}/logout.do"
									class="btn btn-default btn-flat">注销</a>
								</c:if>
							
								
							</div>
						</li>
					</ul></li>

			</ul>
		</div>
	</nav>
</header>

<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		/**
		 * 修改密码
		 */
		$("#editPwdBtn").click(function () {
		    var nowUsername = $("#data-user").val();
		    window.location.href='${pageContext.request.contextPath}/sys/user/toEditPwd?nowUsername='+nowUsername+'';
		});
	})
	
</script>
<!-- 页面头部 -->