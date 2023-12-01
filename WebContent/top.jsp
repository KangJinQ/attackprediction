<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link rel="stylesheet" href="styles/common.css" />
</head>

<body>
	<div class="page-header">
		<div class="header-banner">
			<br /> <img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问贝壳赛事管理系统</div>
		<!--  <div class="header-quicklink">
			<!-- 欢迎您，<strong>admin</strong> 
			<%String employeename = (String) session.getAttribute("username");%>
			欢迎您，<strong><%=employeename%></strong> <a href="#">[修改密码]</a>
		</div> -->

		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<!-- 未登录状态  -->
				<div class="header-quicklink">
					<a href="login.jsp" target="_parent">登录</a> <a href="register.jsp"
						target="main">注册</a>
				</div>
			</c:when>
			<c:otherwise>
				<!-- 登录状态  -->
				<div class="header-quicklink">
				<c:choose>
					<c:when test="${sessionScope.user.userRole eq true }">
					<span>欢迎回来，管理员
						${sessionScope.user.userName }</span> <a href="logout.do"
						target="_parent">退出</a>
					</c:when>
					
					<c:otherwise>
					<span>欢迎回来，用户
						${sessionScope.user.userName }</span> <a href="logout.do"
						target="_parent">退出</a>
					</c:otherwise>
				</c:choose>	
				</div>
			</c:otherwise>
		</c:choose>


	</div>
</body>
</html>