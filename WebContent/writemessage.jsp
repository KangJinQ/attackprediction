<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/common03.css" />
<title>MailBox</title>
</head>
<body>
		
		


		<!--注册表单-->
		<form id="registerForm" action="mailbox.do?op=fa" method="post">
			<input type="hidden" name="action" value="register">
			<fieldset>
				<legend>写信📫</legend>
				<table border="0" align="center" class="page-content">
	
					<tr>
						<td><label for="username">收件人:</label></td>
						<td><input type="text" style="width: 200px;"id="userName" name="userName" placeholder="请输入收件人ID"
							onblur="checkUserName()"></td>
					</tr>
					<tr>
						<td><label for="password">主题:</label></td>
						<td><input type="password" style="width: 200px;"id="userPassword" name="userPassword" placeholder="请输入主题"
							onblur="checkPassword()"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="userPhone">正文:</label></td>
						<td class="td_right"><input style="width: 600px; height: 90px;" type="text" id="userPhone" name="userPhone"
							placeholder="请输入正文"></td>
					</tr>
					<tr>
					<td><input type="submit" class="submit" value="发送" onclick="checkPassword()"></td>
					</tr>
				
				</table>
			</fieldset>
		</form>
		<%
			String msg = (String) request.getAttribute("msg");
		%>
		<!-- 如果不为空才显示,为空就什么也不显示 -->
		<%
			if (msg != null && msg != "") {
		%>
	
		<style>
	div {
		text-align: center
	}
	</style>
		<div>
			<font style="color: red">提示信息:</font> <font color='red'> <%=msg%>
			</font>
		</div>
	
		<%
			}
		%>



</body>
</html>