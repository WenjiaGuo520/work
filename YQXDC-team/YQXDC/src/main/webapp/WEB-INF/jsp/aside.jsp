<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authorize access="isFullyAuthenticated()">
						<security:authentication property="principal.username"
							var="loginuser" />
					</security:authorize>

				</p>
				<c:if test="${loginuser==null}">
					<a href="#"><i class="fa fa-circle text-warning"></i> 未登录</a>
				</c:if>
				<c:if test="${loginuser!=null}">
					<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
				</c:if>

			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/toMain"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/sys/user/list"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/sys/role/list"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/sys/fun/list"> <i
							class="fa fa-circle-o"></i> 系统功能管理
					</a></li>

					
						<li class="system-setting"><a
							href="${pageContext.request.contextPath}/sys/user/toResetPwd">
								<i class="fa fa-circle-o"></i> 重置密码
						</a></li>
					
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/web/goodstype/list">
							<i class="fa fa-circle-o"></i> 菜品类型管理
					</a></li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/web/goods/list"> <i
							class="fa fa-circle-o"></i> 菜品管理
					</a></li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/cusorder/list"> <i
							class="fa fa-circle-o"></i> 订单管理
					</a></li>
				</ul></li>

		</ul>
	</section>
</aside>