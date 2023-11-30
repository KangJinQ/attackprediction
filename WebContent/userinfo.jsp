<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
td {
	align: center;
}
</style>
<link rel="stylesheet" type="text/css" href="styles/common02.css" />
</head>
<body>
	<fieldset>
		<legend>用户隐私信息</legend>
		<table border=3 width=500 height=300 align=center>
			<tr>
				<td>身份ID🆔</td>
				<td>${sessionScope.user.userId }</td>
				<td rowspan=1 width="100px" height="140px"><img
					src="images/kjq.jpg" alt="登记照片" width="100px" height="140px"></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td colspan="4" colspan="1">${sessionScope.user.userName }</td>
			</tr>
			<tr>
				<td>身份</td>
				<td colspan="4" colspan="1">${sessionScope.user.userIdentity }</td>
			</tr>
			<tr>
				<td>报名手机号📱</td>
				<td colspan="4" align="center">${sessionScope.user.userPhone }</td>
			</tr>
			<tr>
				<td>报名邮箱📫</td>
				<td colspan="4" align="center">${sessionScope.user.userEmail }</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>