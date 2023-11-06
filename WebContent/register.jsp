<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册账户</title>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>


	<!--注册表单-->
	<form id="registerForm" action="register.do" method="post">
		<input type="hidden" name="action" value="register">
		<fieldset>
			<legend>注册信息</legend>
			<table border="0" align="center" class="page-content">

				<tr>
					<td><label for="username">UserName:</label></td>
					<td><input type="text" id="userName" name="userName" placeholder="请输入账号"
						" onblur="checkUserName()"></td>
				</tr>
				<tr>
					<td><label for="password">PassWord:</label></td>
					<td><input type="password" id="userPassword" name="userPassword" placeholder="请输入密码"
						" onblur="checkPassword()"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="userPhone">手机号:</label></td>
					<td class="td_right"><input type="text" id="userPhone" name="userPhone"
						placeholder="请输入您的手机号"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="userEmail">邮箱:</label></td>
					<td class="td_right"><input type="text" id="userEmail" name="userEmail"
						placeholder="请输入您的邮箱"></td>
				</tr>
				<tr>
					<td>请选择注册身份：</td>
					<td><input type="radio" name="userRole" value="user">用户 <!-- 					<input --> <!-- 						type="radio" name="userIdentity" value="superadmin">超级管理员</td> -->
				</tr>
				
				<tr>
					<td><input type="submit" class="submit" value="注册" onclick="checkPassword()"></td>
					<td><button onclick="window.history.back()">返回◀</button></td>
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