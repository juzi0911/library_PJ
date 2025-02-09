<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		 ;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>复日网上书城系统-日复日兮，日月光华</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/css/header.css" />
</head>
<body><%--猜测也是head的一部分？--%>
	<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	<!-- 导航条 -->
	<div id="daohang">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="<%=basePath%>/client/ClientServlet?op=category" target="_blank"><img style="background-size:contain;height: 20px;width: 120px"
					src="<%=basePath%>/img/sc.jpg" />
				</a>
				<c:if test="${!empty user}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
						style="color: red; font-weight: bold">${sessionScope.user.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;，复日网上书城系统欢迎您。
						<img src="<%=basePath%>/img/gerenzx.png"
						style="margin-left: 900px" />&nbsp;&nbsp;
					<a href="<%=basePath%>/personalCenter.jsp" style="color: black">个人中心</a>
				</c:if>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<c:if test="${empty user}">
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<!-- 登录 -->
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#myModal">登录</button>
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">登录</h4>
									</div>
									<form action="client/ClientServlet?op=login" method="post">
										<div class="modal-body">
											<div class="form-group">
												<label for="exampleInputName2">用户名： </label> <input
													type="text" name="username" class="form-control"
													id="exampleInputName2" placeholder="Username">
											</div>
											<br>
											<div class="form-group">
												<label for="exampleInputPassword1">密码：</label> <input
													type="password" name="password" class="form-control"
													id="exampleInputPassword1" placeholder="Password">
											</div>
											<br>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary"
												onclick="window.location='client/ClientServlet?op=login'">登录</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- 注册 -->
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#myModal1">注册</button>

						<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">注册</h4>
									</div>
									<form
										action="/client/ClientServlet?op=register"
										method="post">
										<div class="modal-body">
											<div class="form-group">
												<label for="exampleInputName2">用户名：</label> <input
													type="text" id="username" name="username"
													class="form-control" id="exampleInputName2"
													placeholder="Username">
											</div>
											<span id="s1"></span> <br>
											<div class="form-group">
												<label for="exampleInputPassword1">密码：</label> <input
													type="password" name="password" class="form-control"
													id="exampleInputPassword1" placeholder="Password">
											</div>
											<div class="form-group">
												<label for="exampleInputName2">姓名：</label> <input
													type="text" name="name" class="form-control"
													id="exampleInputName2" placeholder="Name">
											</div>
											<label for="Ssex">性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<input type="radio" name="sex" value="男">男
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
												type="radio" name="sex" value="女">女 <br>
											<div class="form-group">
												<label for="exampleInputName2">电话：</label> <input
													type="text" name="tel" class="form-control"
													id="exampleInputName2" placeholder="Telephone number">
											</div>
											<div class="form-group">
												<label for="exampleInputName2">收货地址：</label> <input
													type="text" name="address" class="form-control"
													id="exampleInputName2" placeholder="Address">
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<button type="submit" class="btn btn-primary">注册</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty user}">
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm"
							href="${pageContext.request.contextPath}/client/ClientServlet?op=layout"
							role="button">退出</a>
					</div>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" href="#" role="button">购物车</a>
					</div>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" href="#" role="button">收藏夹</a>
					</div>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" target="_parent"
						   href="/ex4-17-login.f"
						   role="button">树洞</a>
					</div>
				</c:if>
				<div style="float: right; margin-right: 7px; margin-top: 10px">
					<button type="button" class="btn btn-default btn-sm" onclick="#">管理员入口</button>
				</div>
			</div>
		</div>
		</nav>
	</div>
</body>
</html>