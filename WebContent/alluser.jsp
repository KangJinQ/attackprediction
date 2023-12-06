<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台用户信息</title>
<link rel="stylesheet" type="text/css" href="styles/common03.css" />

</head>
<body>
	<input type="hidden" name="msg" id="msg" value="${requestScope.msg }">

	<%
		String msg = (String) request.getAttribute("msg");
	%>
	<!-- 如果不为空才显示,为空就什么也不显示 -->
	<%
		if (msg != null) {
	%>
	<center>
		<tr>
			<td>提示信息:</td>
			<td><font color='red'> <%=msg%>
			</font></td>
		</tr>
	</center>
	<%
		}
	%>

	<script>
		function check() {
			if (confirm("确定添加他为管理员吗？")) {
				var msg = document.getElementById("msg").value;
				//var msg = ${requestScope.msg };
				//var msg = $("input[name='msg']").val();
				if (msg == null || msg == "") {
					//alert("设置管理员成功")
					return true;
				} else {
					//alert(msg);
					//return false;
				}
			}
			return true;
		}
	</script>
	
	<fieldset>
		<legend>平台用户信息</legend>
		<table class="listtable" width="100%" border="0" cellpadding="0"
			cellspacing="1" align=center>
			<tr class="listheader">
				<td width="3%" height="19"><div align="center"></div></td>
				<td width="10%" nowrap="nowrap"><div align="center">身份ID</div></td>
				<td width="15%" nowrap="nowrap"><div align="center">用户名</div></td>
				<td width="10%" nowrap="nowrap"><div align="center">用户身份</div></td>
				<td width="20%" nowrap="nowrap"><div align="center">手机号</div></td>
				<td width="20%" nowrap="nowrap"><div align="center">邮箱</div></td>
				<td width="15%" nowrap="nowrap"><div align="center">基本操作</div></td>
			</tr>
			<c:choose>
				<c:when test="${empty requestScope.allUserList }">
					<!-- 这是没有查询到用户的显示行，在when标签为true即容器为空时执行 -->
					<tr>
						<td colspan="10" align="center" bgcolor="#FFFFFF"><div
								class="STYLE1" style="font-size: 36px; color: red;">无已注册的用户！</div></td>
					</tr>
					<!-- 这个是用户信息显示的结束 -->
				</c:when>
				<c:otherwise>
					<c:forEach items="${requestScope.allUserList }" var="user"
						varStatus="status">
						<!-- 这个是用户信息的显示行 -->
						<tr>
							<td height="20" nowrap="nowrap"><div align="center">
									🧔</div></td>
							<td height="20" nowrap="nowrap"><div align="center">${user.userId }</div></td>
							<td height="20" nowrap="nowrap"><div align="center">
									<a href="userinfo.jsp" target="_blank">${user.userName }</a>
								</div>
							<td height="20" nowrap="nowrap"><div align="center">
									<c:choose>
										<c:when test="${user.userRole eq true }">
										管理员
										</c:when>
										<c:otherwise>
											用户
										</c:otherwise>
									</c:choose>

									</div></td>
							<td height="20" nowrap="nowrap"><div align="center">${user.userPhone }</div></td>
							<td height="20" nowrap="nowrap"><div align="center">${user.userEmail }</div></td>
							<td height="20" nowrap="nowrap"><div align="center">
									<c:choose>
										<c:when test="${user.userRole eq true }">
										管理员
										</c:when>
										<c:otherwise>
											<a
												href="addmanager.do?uid=${user.userId }"
												onclick="check();return true">设为管理员💪</a>
											<br>
										</c:otherwise>
									</c:choose>
								</div>
						</tr>

						<!-- 这个是信息展示的结束 -->
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</fieldset>
	<!-- 信息显示 -->
</body>
</html>