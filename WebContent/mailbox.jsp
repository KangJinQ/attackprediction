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
	<fieldset>
		<legend>近期消息📫</legend>
		<table class="listtable" style="align: center;" border="1px" width="80%">
			<tr class="listheader">
				<td width="15%" nowrap="nowrap"><div align="center">📆消息日期</div></td>
				<td width="75%" nowrap="nowrap"><div align="center">📄消息内容</div></td>
			</tr>
			<c:choose>
				<c:when test="${empty requestScope.messageList }">
					<!-- 这是没有查询到商品的显示行，在when标签为true即容器为空时执行 -->
					<tr>
						<td colspan="10" align="center" bgcolor="#FFFFFF"><div
								style="font-size: 36px; color: red;">没有历史消息</div></td>

					</tr>
					<!-- 这个是商品信息显示的结束 -->
				</c:when>
				<c:otherwise>
					<c:forEach items="${requestScope.messageList }" var="message"
						varStatus="loop">
						<!-- 这个是商品信息的显示行 -->
						<tr>
							<td nowrap="nowrap"><div align="center">${messageDateList[loop.count-1] }</div></td>
							<td nowrap="nowrap"><div align="center">${message }</div></td>
						</tr>
						<!-- 这个是商品信息展示的结束 -->
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</fieldset>

</body>
</html>